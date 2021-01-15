package com.nt.votationt.ScheduleServiceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nt.votationt.DateCheck.DateCheck;
import com.nt.votationt.dto.ScheduleStatusDTO;
import com.nt.votationt.exceptions.ResourceNotFoundExeception;
import com.nt.votationt.model.Schedule;
import com.nt.votationt.repository.ScheduleRepository;
import com.nt.votationt.service.ScheduleService;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)

public class CheckScheduleState {

	@InjectMocks
	private ScheduleService service;

	@MockBean
	private ScheduleRepository repository;
	@MockBean
	DateCheck datecheck;

	@Test
	public void checkScheduleStatePass() {
		Long scheduleid = 1L;
		Schedule schedule = new Schedule();
		schedule.setN_votes_n(3L);
		schedule.setN_votes_p(5L);
		schedule.setStart_date(LocalDateTime.of(2020, 11, 12, 10, 05));
		schedule.setEnd_date(LocalDateTime.of(2021, 11, 12, 10, 05));
		Long vp = schedule.getN_votes_p();
		Long vn = schedule.getN_votes_n();
		Mockito.when(repository.findByIdschedule(scheduleid)).thenReturn(schedule);
		Mockito.when(datecheck.checkStatus(schedule)).thenReturn("Ongoing");
		String percent = Double.toString((100 * vp) / (vp + vn)) + "%";
		ScheduleStatusDTO statusdto = new ScheduleStatusDTO(schedule.getStart_date(), schedule.getEnd_date(), "Ongoing",
				vp, vn, percent);
		assertThat(statusdto.equals(service.checkScheduleState(scheduleid)));
	}

	@Test
	public void checkScheduleStateErrorNotFound() {
		Long scheduleid = 1L;
		Mockito.when(repository.findByIdschedule(scheduleid)).thenReturn(null);
		assertThrows(ResourceNotFoundExeception.class, () -> {
			service.checkScheduleState(scheduleid);
		});
	}
}
