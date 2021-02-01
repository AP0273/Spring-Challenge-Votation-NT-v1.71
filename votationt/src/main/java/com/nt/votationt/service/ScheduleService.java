package com.nt.votationt.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nt.votationt.DateCheck.DateCheck;
import com.nt.votationt.dto.ScheduleStatusDTO;
import com.nt.votationt.exceptions.ResourceNotFoundExeception;
import com.nt.votationt.exceptions.UnauthorizedException;
import com.nt.votationt.forms.DeletionForm;
import com.nt.votationt.forms.ScheduleFormInsert;
import com.nt.votationt.forms.ScheduleFormUpdate;
import com.nt.votationt.forms.VoteFormInsert;
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
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ScheduleService.class);

	public Schedule insertSchedule(ScheduleFormInsert form, Long id) {
		if (personservice.login(form.getCpfProponent(), form.getPassword()) == false) throw new UnauthorizedException("CPF or Password are Wrong or Unregistred");
		Schedule schedule = new Schedule(form);
		if (id != null) {
			schedule.setIdschedule(id);
		}
		if (form.getEndDate() == null || form.getStartDate() == null) {
			schedule.setStartDate(LocalDateTime.now());
			schedule.setEndDate(LocalDateTime.now().plusMinutes(1L));
		}
		LOGGER.info("(Schedule) "+ schedule.toString()  +" Inserted Successfully");
		return schedulerepository.save(schedule);
	}

	public Schedule updateSchedule(ScheduleFormUpdate form) {
		Schedule scheduleDb = schedulerepository.findByIdschedule(form.getIdschedule());
		if (isScheduleExist(form.getIdschedule()) == false) throw new ResourceNotFoundExeception("Schedule not Found");
		if (isAuthor(form) == false) throw new UnauthorizedException("Error only the author is authorized to modify");
		Schedule uSchedule = new Schedule(new ScheduleFormInsert(form)); 
		LOGGER.info("(Schedule) "+ scheduleDb.toString()  +" Updated to " + uSchedule.toString() );
		return insertSchedule(new ScheduleFormInsert(form), form.getIdschedule());
	}

	public boolean isAuthor(ScheduleFormUpdate form) {
		Schedule schedule = schedulerepository.findByIdschedule(form.getIdschedule());
		boolean result = false;
		if (schedule.getCpfProponent().equals(form.getCpfProponent())) {
			result = true;
		}
		return result;
	}

	public Schedule findSchedule(Long id) {
		Schedule schedule = schedulerepository.findByIdschedule(id);
		if (schedule == null) throw new ResourceNotFoundExeception("Schedule with id: " + id + " Not Found");
		return schedule;
	}

	public List<Schedule> findByNameIgnoreCase(String name) {
		List<Schedule> schedulelist = schedulerepository.findByNameIgnoreCase(name);
		if (schedulelist.isEmpty())	throw new ResourceNotFoundExeception("None results found for the name " + name);
		return schedulelist;
	}

	public List<Schedule> findByCategoryIgnoreCase(String category) {
		List<Schedule> schedulelist = schedulerepository.findByCategoryIgnoreCase(category);
		if (schedulelist.isEmpty()) throw new ResourceNotFoundExeception("None results found for the category " + category);
		return schedulelist;
	}

	public void deleteSchedule(DeletionForm form, Long idSchedule) {
		if (isScheduleExist(idSchedule) == false) throw new ResourceNotFoundExeception("Schedule Not Found");
		Schedule schedule = schedulerepository.findByIdschedule(idSchedule);
		if (!schedule.getCpfProponent().equals(form.getCpf())) throw new UnauthorizedException("Unauthorized, Only the Author can delete the schedule");
		if (personservice.login(form.getCpf(), form.getPassword()) == false) throw new UnauthorizedException("Unauthorized Wrong Password");
		LOGGER.info("(Schedule) "+ schedule.toString()  +" Deleted Successfully");
		schedulerepository.delete(schedule);
	}

	public Schedule updateDeletedVote(Schedule schedule, boolean vote) {
		if (vote = true) {
			schedule.setnVotesP(schedule.getnVotesP() - 1);
		} else {
			schedule.setnVotesN(schedule.getnVotesN() - 1);
		}
		return schedule;
	}

	public boolean isScheduleExist(Long idSchedule) {
		Schedule sdb = schedulerepository.findByIdschedule(idSchedule);
		boolean result = true;
		if (sdb == null)
			result = false;
		return result;
	}

	public boolean isAlreadyVoted(VoteFormInsert vote) {
		final Vote vdb = schedulerepository.findByCpfPersonAndIdSchedule(vote.getCpfPerson(), vote.getIdschedule());
		boolean result = true;
		if (vdb == null)
			result = false;
		return result;
	}

	public List<Schedule> getAllSchedule() {
		List<Schedule> schedulelist = schedulerepository.findAll();
		if (schedulelist.isEmpty()) throw new ResourceNotFoundExeception("None results found");
		return schedulelist;
	}

	// The percent calculation is better to do from the front-end to save resources
	// from the server, i just did it here to test
	public ScheduleStatusDTO checkScheduleState(Long idSchedule) {
		if (isScheduleExist(idSchedule) == false) throw new ResourceNotFoundExeception("Schedule Not Found");
		Schedule sdb = schedulerepository.findByIdschedule(idSchedule);
		String state = datecheck.checkStatus(sdb);
		Long vp = sdb.getnVotesP();
		Long vn = sdb.getnVotesN();
		String percent;
		try {
			percent = Double.toString((100 * vp) / (vp + vn)) + "%";
		} catch (ArithmeticException e) {
			percent = "0%";
		}

		final ScheduleStatusDTO statusdto = new ScheduleStatusDTO(sdb.getStartDate(), sdb.getEndDate(), state, vp, vn,
				percent);
		return statusdto;
	}
}