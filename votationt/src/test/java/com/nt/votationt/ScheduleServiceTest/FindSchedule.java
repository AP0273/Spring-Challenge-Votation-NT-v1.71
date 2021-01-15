package com.nt.votationt.ScheduleServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nt.votationt.exceptions.ResourceNotFoundExeception;
import com.nt.votationt.model.Schedule;
import com.nt.votationt.repository.ScheduleRepository;
import com.nt.votationt.service.ScheduleService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class FindSchedule {

	@InjectMocks
	private ScheduleService service;
	
	@MockBean
	private ScheduleRepository repository;

	
@Test
	public void findSchedulePass() {
	    Schedule schedule = new Schedule();
		schedule.setIdschedule(1L);
		Mockito.when(repository.findByIdschedule(schedule.getIdschedule())).thenReturn(schedule);
		assertEquals(schedule, service.findSchedule(schedule.getIdschedule()));
	}
	@Test
	public void findPersonErrorNotFound() {
		Schedule schedule = new Schedule();
		schedule.setIdschedule(1L);
		Mockito.when(repository.findByIdschedule(schedule.getIdschedule())).thenReturn(null);
		assertThrows(ResourceNotFoundExeception.class, () -> {service.findSchedule(schedule.getIdschedule());});
	}
}


