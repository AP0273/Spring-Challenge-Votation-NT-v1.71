package com.nt.votationt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nt.votationt.DateCheck.DateCheck;
import com.nt.votationt.exceptions.ResourceNotFoundExeception;
import com.nt.votationt.exceptions.UnauthorizedException;
import com.nt.votationt.forms.DeletionForm;
import com.nt.votationt.forms.VoteFormInsert;
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

	public Vote insertVote(VoteFormInsert form) {
		System.out.println("idschedule" + form.getIdschedule());
		System.out.println("Cpfperson" + form.getCpfPerson());
		final Person pdb = personrepository.findByCpf(form.getCpfPerson());
		final Schedule sdb = schedulerepository.findByIdschedule(form.getIdschedule());
		if (scheduleservice.isScheduleExist(form.getIdschedule()) == false) throw new ResourceNotFoundExeception("Error Schedule Not Found");
		if (personservice.login(form.getCpfPerson(), form.getPassword()) == false) throw new UnauthorizedException("CPF or Password are Wrong or Unregistred");
		if (pdb.isCanVote() == false) throw new UnauthorizedException("CPF Unable to Vote");
		if (datecheck.isOnGoing(sdb) == false) throw new UnauthorizedException("Error, this voting is closed");
		if (scheduleservice.isAlreadyVoted(form) == true) throw new UnauthorizedException("Error, You Already Voted");
		boolean voteap = form.isAprovation();
		if (voteap == true) {
			sdb.setN_votes_p(sdb.getN_votes_p() + 1);
		} else {
			sdb.setN_votes_n(sdb.getN_votes_n() + 1);
		}
		return voterepository.save(new Vote(form));
	}

	public Vote findVote(Long id) {
		final Vote vote = voterepository.FindById(id);
		if (vote == null) throw new ResourceNotFoundExeception("Vote Not Found");
		return vote;
	}

	public List<Vote> findByCpfPerson(String Cpf) {
		List<Vote> votelist = voterepository.findByCpfPerson(Cpf);
		if (votelist.isEmpty()) throw new ResourceNotFoundExeception("None Results Found");
		return votelist;
	}

	public void deleteVote(DeletionForm form, Long id) {
		final Vote vote = voterepository.FindById(id);
		if (vote == null) throw new ResourceNotFoundExeception("Vote Not Found");
		if (!vote.getCpfPerson().equals(form.getCpf())) throw new UnauthorizedException("Unauthorized, Only the Author can delete the vote");
		if (personservice.login(form.getCpf(), form.getPassword()) == false) throw new UnauthorizedException("Unauthorized Wrong Password");
		Schedule schedule = schedulerepository.findByIdschedule(vote.getIdschedule());
		if (datecheck.isOnGoing(schedule) == false) throw new UnauthorizedException("Error, is not possible delete a vote on a ended votation");

		voterepository.delete(vote);
		schedulerepository.save(scheduleservice.updateDeletedVote(schedule, vote.isAprovation()));
	}

	public List<Vote> getAllVote() {
		List<Vote> votelist = voterepository.findAll();
		if (votelist.isEmpty()) throw new ResourceNotFoundExeception("None Results Found");
		return votelist;
	}

	public List<Vote> findByIdScheduleAndAprovation(Long IdSchedule, boolean Aprovation) {
		return voterepository.findByIdscheduleAndAprovation(IdSchedule, Aprovation);
	}

}
