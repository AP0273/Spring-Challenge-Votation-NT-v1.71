package com.nt.votationt.ScheduleServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

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

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class GetAllSchedule {

	@InjectMocks
	private ScheduleService service;

	@MockBean
	private ScheduleRepository repository;

	@Test
	public void getAllSchedulePass() {
		Schedule schedule = new Schedule();
		List<Schedule> listschedule = new ArrayList<Schedule>();
		listschedule.add(schedule);
		Mockito.when(repository.findAll()).thenReturn(listschedule);
		assertEquals(listschedule, service.getAllSchedule());
	}

	@Test
	public void getAllPersonErrorNotFound() {
		List<Schedule> listschedule = new ArrayList<Schedule>();
		Mockito.when(repository.findAll()).thenReturn(listschedule);
		assertThrows(ResourceNotFoundExeception.class, () -> {
			service.getAllSchedule();
		});
	}
}
