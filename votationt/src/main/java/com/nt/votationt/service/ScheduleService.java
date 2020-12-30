package com.nt.votationt.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nt.votationt.DateCheck.DateCheck;
import com.nt.votationt.exceptions.ResourceNotFoundExeception;
import com.nt.votationt.exceptions.UnauthorizedException;
import com.nt.votationt.model.Schedule;
import com.nt.votationt.model.Vote;
import com.nt.votationt.repository.ScheduleRepository;

@Service
public class ScheduleService {

	@Autowired
	private ScheduleRepository schedulerepository;
	@Autowired
	private PersonService personservice;
	@Autowired
	private DateCheck datecheck;

	public Schedule insertSchedule(Schedule schedule) {
		if (personservice.Login(schedule.getCpfProponent(), schedule.getPassword()) == false)
			throw new UnauthorizedException("CPF or Password are Wrong or Unregistred");
		return schedulerepository.save(schedule);
	}

	public Schedule findSchedule(Long id) {
		return schedulerepository.getOne(id);
	}

	public Schedule findByNameIgnoreCase(String Name) {
		return schedulerepository.findByNameIgnoreCase(Name);
	}

	public List<Schedule> findByCategoryIgnoreCase(String Category) {
		List <Schedule> lschedule = schedulerepository.findByCategoryIgnoreCase(Category);
		return lschedule;
	}

	public void deleteSchedule(Long id_schedule) {
		if (isScheduleExist(id_schedule) == false)
			throw new ResourceNotFoundExeception("Schedule Not Found");
		Schedule schedule = schedulerepository.FindById(id_schedule);
			 schedulerepository.delete(schedule);

		}
	

	public String getNumberOfVotes_Schedule(Long id_schedule, boolean aprovation) {
		if (isScheduleExist(id_schedule) == false)
		throw new ResourceNotFoundExeception("Schedule Not Found");
		List<Vote> vt = schedulerepository.findByIdScheduleAndAprovation(id_schedule, aprovation);
		int votes = vt.size();
		String result;
		if (aprovation == true) {
			result = "Number of Positive Votes = " + votes + "\n" + vt;
		} else {
			result = "Number of Negative Votes = " + votes + "\n" + vt;
		}
		return result;

	}

	public boolean isScheduleExist(Long idSchedule) {
		Schedule SDB = schedulerepository.FindById(idSchedule);
		boolean result = true;
		if (SDB == null)
			result = false;
		return result;
	}

	public List<Schedule> getAllSchedule() {

		List<Schedule> list = schedulerepository.findAll();

		return list;
	}

	// The percent calculation is better to do from the front-end to save resources
	// from the server, i just did it here to test
	public String checkScheduleState(Long id_schedule) {
		if (isScheduleExist(id_schedule) == false)
			throw new ResourceNotFoundExeception("Schedule Not Found");
		Schedule SDB = schedulerepository.FindById(id_schedule);
		Long vp = SDB.getN_votes_p();
		Long vn = SDB.getN_votes_n();
		String result = datecheck.checkStatus(SDB) + " Votation " + "\n" + "Positive Votes: " + vp + "\n" + "Negative Votes: " + vn + "\n"
				+ "Approval Percent: " + (100 * vp) / (vp + vn) + "%";
		return result;
	}
}