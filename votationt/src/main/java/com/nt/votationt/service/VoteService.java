package com.nt.votationt.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nt.votationt.DateCheck.DateCheck;
import com.nt.votationt.exceptions.ResourceNotFoundExeception;
import com.nt.votationt.exceptions.UnauthorizedException;
import com.nt.votationt.model.Person;
import com.nt.votationt.model.Schedule;
import com.nt.votationt.model.Vote;
import com.nt.votationt.repository.PersonRepository;
import com.nt.votationt.repository.ScheduleRepository;
import com.nt.votationt.repository.VoteRepository;

@Service
public class VoteService {

	@Autowired
	private VoteRepository voterepository;
	@Autowired
	private PersonRepository personrepository;
	@Autowired
	private ScheduleRepository schedulerepository;
	@Autowired
	private PersonService personservice;
	@Autowired
	private ScheduleService scheduleservice;
	@Autowired
	private DateCheck datecheck;

	public Vote insertVote(Vote vote) {

		final Person PDB = personrepository.findByCpfAndPassword(vote.getCpfPerson(), vote.getPassword());
		final Schedule SDB = schedulerepository.FindById(vote.getIdSchedule());
		if (scheduleservice.isScheduleExist(vote.getIdSchedule()) == false)
			throw new ResourceNotFoundExeception("Schedule Not Found");
		if (personservice.Login(vote.getCpfPerson(), vote.getPassword()) == false)
			throw new UnauthorizedException("CPF or Password are Wrong or Unregistred");
		if (PDB.isCanVote() == false)
			throw new UnauthorizedException("CPF Unable to Vote");
		if (datecheck.isOnGoing(SDB) == false)
			throw new UnauthorizedException("Error, this voting is closed");
		if (isAlreadyVoted(vote) == true)
			throw new UnauthorizedException("Error, You Already Voted");
		boolean voteap = vote.isAprovation();
		if (voteap == true) {
			SDB.setN_votes_p(SDB.getN_votes_p() + 1);
		} else {
			SDB.setN_votes_n(SDB.getN_votes_n() + 1);
		}
		return voterepository.save(vote);
	}

	public boolean isAlreadyVoted(Vote vote) {
		final Vote VDB = schedulerepository.findByCpfPersonAndIdSchedule(vote.getCpfPerson(), vote.getIdSchedule());
		boolean result = true;
		if (VDB == null)
			result = false;
		return result;
	}

	public Vote FindVote(Long id) {
		final Vote vote = voterepository.FindByIdVote(id);
		if (vote == null)
			throw new ResourceNotFoundExeception("Vote Not Found");
		return vote;
	}

	public List<Vote> findByCpfPerson(Long Cpf) {
		
		return voterepository.findByCpfPerson(Cpf);
	}

	public void DeleteVote(Long id) {
		final Vote vote = voterepository.getOne(id);
		if (vote == null)
			throw new ResourceNotFoundExeception("Vote Not Found");
		voterepository.delete(vote);
	}

	public List<Vote> getAllVote() {

		List<Vote> list = voterepository.findAll();

		return list;
	}

	public List<Vote> findByIdScheduleAndAprovation(Long IdSchedule, boolean Aprovation) {
		return voterepository.findByIdScheduleAndAprovation(IdSchedule, Aprovation);
	}

}
