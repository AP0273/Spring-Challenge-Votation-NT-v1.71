package com.nt.votationt.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

	public Object insertVote(Vote vote) {
		// Check Auth
		Person PDB = personrepository.AuthPerson(vote.getCpf_person(), vote.getPassword());
		if (PDB != null) {
			if (PDB.iscanVote()) {
				// Schedule Existence
				Schedule SDB = schedulerepository.FindScheduleID(vote.getId_schedule());
				if (SDB != null) {
					// Check Date
					if (LocalDateTime.now().isBefore(SDB.getEnd_date())
							&& LocalDateTime.now().isAfter(SDB.getStart_date())) {
						// Check if Already Voted
						Vote VDB = schedulerepository.xAlreadyVoted(vote.getCpf_person(), vote.getId_schedule());
						if (VDB == null) {
							boolean voteap = vote.isAprovation();
							Schedule s = schedulerepository.getOne(vote.getId_schedule());
							if (voteap == true) {
								s.setN_votes_p(s.getN_votes_p() + 1);
							} else {
								s.setN_votes_n(s.getN_votes_n() + 1);
							}
							return voterepository.save(vote);
						} else {
							return "Error, You Already Voted";
						}
					} else {
						return "Error, this voting is closed";
					}
				} else {
					return "Inexistent Schedule";
				}
			} else {
				return "CPF Unable to Vote";
			}
		} else {
			return "CPF or Password are Wrong or Unregistred";
		}

	}

	public Object FindVote(Long id) {
		Vote vote = voterepository.getOne(id);
		if (vote != null) {
			return voterepository.getOne(id);
		} else {
			return "Vote not Found";
		}
	}

	public Vote findByCpfPerson(Long Cpf) {
		return voterepository.findByCpfPerson(Cpf);
	}

	public String DeleteVote(Long id) {
		Vote vote = voterepository.getOne(id);
		if (vote != null) {
			voterepository.delete(vote);
			return "Vote Deleted!";
		} else {
			return "Vote Not Found";
		}
	}

	public List<Vote> getAllVote() {

		List<Vote> list = voterepository.findAll();

		return list;
	}

	public List<Vote> findByIdAndAprovation(Long Cpf, boolean Aprovation) {
		return voterepository.findByIdAndAprovation(Cpf, Aprovation);
	}

}
