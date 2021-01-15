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
import com.nt.votationt.exceptions.UnauthorizedException;
import com.nt.votationt.forms.ScheduleFormInsert;
import com.nt.votationt.forms.ScheduleFormUpdate;
import com.nt.votationt.model.Schedule;
import com.nt.votationt.repository.ScheduleRepository;
import com.nt.votationt.service.PersonService;
import com.nt.votationt.service.ScheduleService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ScheduleUpdate {

	@InjectMocks
	private ScheduleService scheduleservice;

	@MockBean
	private ScheduleRepository repository;

	@MockBean
	PersonService personservice;

	@Test
	public void scheduleUpdatePass() {
		ScheduleFormUpdate form = new ScheduleFormUpdate();
		form.setIdschedule(1L);
		form.setCpfProponent("85239109052");
		form.setPassword("SomePassword");
		Schedule schedule = new Schedule();
		schedule.setCpfProponent("85239109052");
		Mockito.when(repository.findByIdschedule(form.getIdschedule())).thenReturn(schedule);
		Mockito.when(personservice.login(form.getCpfProponent(), form.getPassword())).thenReturn(true);
		assertEquals(scheduleservice.insertSchedule(new ScheduleFormInsert(form), form.getIdschedule()),
				scheduleservice.updateSchedule(form));
	}

	@Test
	public void scheduleUpdateErrorNotFound() {
		ScheduleFormUpdate form = new ScheduleFormUpdate();
		form.setIdschedule(1L);
		Mockito.when(repository.findByIdschedule(form.getIdschedule())).thenReturn(null);
		assertThrows(ResourceNotFoundExeception.class, () -> {
			scheduleservice.updateSchedule(form);
		});
	}

	@Test
	public void scheduleUpdateErrorNotTheAuthor() {
		ScheduleFormUpdate form = new ScheduleFormUpdate();
		form.setIdschedule(1L);
		form.setCpfProponent("85239109052");
		form.setPassword("SomePassword");
		Schedule schedule = new Schedule();
		schedule.setCpfProponent("85239109052");
		Mockito.when(repository.findByIdschedule(form.getIdschedule())).thenReturn(schedule);
		Mockito.when(personservice.login(form.getCpfProponent(), form.getPassword())).thenReturn(false);
		assertThrows(UnauthorizedException.class, () -> {
			scheduleservice.updateSchedule(form);
		});
	}
}
