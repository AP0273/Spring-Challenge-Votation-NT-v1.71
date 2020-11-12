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


@Service
public class ScheduleService {

	@Autowired
	private ScheduleRepository schedulerepository;
	@Autowired
	private PersonRepository personrepository;

	public Object insertSchedule(Schedule schedule) {
	    Person PDB = personrepository.AuthPerson(schedule.getCpf_proponent(), schedule.getPassword());
			if(PDB!= null) {
		return schedulerepository.save(schedule);
			}else {
		return "CPF or Password are Wrong or Unregistred";
			}
	}
	public Schedule FindSchedule(Long id) {
		return schedulerepository.getOne(id);
	}
	public Schedule findByNameIgnoreCase(String Name) {
		return schedulerepository.findByNameIgnoreCase(Name);
	}
	public Schedule findByCategoryIgnoreCase(String Category) {
		return schedulerepository.findByNameIgnoreCase(Category);
	}
	public String DeleteSchedule(Long id) {
		Schedule schedule = schedulerepository.getOne(id);
		if (schedule != null) {
			schedulerepository.delete(schedule);
			return "Schedule Deleted!";
		} else {
			return "Schedule Not Found";
		}
	}
	public String GetNumberOfVotes_Schedule (Long id,boolean aprovation) {
		List <Vote> vt = schedulerepository.findVotes_Schedule(id, aprovation);
		int votes = vt.size();
		if(aprovation == true) {
		return "Number of Positive Votes = " + Integer.toString(votes) +"\n" + vt ;	
		}else {
		return "Number of Negative Votes = " + Integer.toString(votes) +"\n" + vt ;
		}
		
	}
	public List<Schedule> getAllSchedule(){
		
		List<Schedule> list = schedulerepository.findAll();
		
		return list;
	}
	//The percent calculation is better to do from the front-end to save resources from the server, i just did it here to test
	public String CheckScheduleState(Long id_schedule) {
		Schedule SDB = schedulerepository.FindScheduleID(id_schedule);
		if(SDB != null) {
		Long vp =SDB.getN_votes_p();
		Long vn =SDB.getN_votes_n();
		if(LocalDateTime.now().isAfter(SDB.getEnd_date()) && (vp+vn <= 0)) {
		return "Ended Votation" + "\n" + "Positive Votes: " +vp +
				"\n" + "Negative Votes: " +vn;
		}
		if(LocalDateTime.now().isBefore(SDB.getEnd_date()) && LocalDateTime.now().isAfter(SDB.getStart_date())  && (vp+vn <= 0)) {
		return "Ongoing Votation" + "\n" + "Positive Votes: " +vp +
				"\n" + "Negative Votes: " +vn;
	}
			if(LocalDateTime.now().isAfter(SDB.getEnd_date())) {
			return "Ended Votation" + "\n" + "Positive Votes: " +vp +
					"\n" + "Negative Votes: " +vn + "\n" + "Approval Percent: " + (100*vp)/(vp+vn)+"%";
			}
			if(LocalDateTime.now().isBefore(SDB.getEnd_date()) && LocalDateTime.now().isAfter(SDB.getStart_date()) ) {
			return "Ongoing Votation" + "\n" + "Positive Votes: " +vp +
					"\n" + "Negative Votes: " +vn + "\n" + "Approval Percent: " + (100*vp)/(vp+vn)+"%";
		}
			if(LocalDateTime.now().isAfter(SDB.getEnd_date())) {
			return "Unstarted Votation" + "\n" + "Start Date: " + SDB.getStart_date(); 
			}
		
	}
		return"Schedule Not Found";	
	}
}
