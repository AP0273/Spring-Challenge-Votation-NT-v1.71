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

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class FindByNameIgnoreCase {

	@InjectMocks
	private ScheduleService service;

	@MockBean
	private ScheduleRepository repository;

	@Test
	public void findByNamePass() {
		String Name = "Random Name";
		Schedule schedule = new Schedule();
		List<Schedule> listschedule = new ArrayList<Schedule>();
		listschedule.add(schedule);
		Mockito.when(repository.findByNameIgnoreCase(Name)).thenReturn(listschedule);
		assertEquals(listschedule, service.findByNameIgnoreCase(Name));
	}

	@Test
	public void findByFullNameErrorNotFound() {
		String Name = "Random Name";
		List<Schedule> listschedule = new ArrayList<Schedule>();
		Mockito.when(repository.findByNameIgnoreCase(Name)).thenReturn(listschedule);
		assertThrows(ResourceNotFoundExeception.class, () -> {
			service.findByNameIgnoreCase(Name);
		});
	}
}
