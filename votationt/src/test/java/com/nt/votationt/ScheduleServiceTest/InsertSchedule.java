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

import com.nt.votationt.exceptions.UnauthorizedException;
import com.nt.votationt.forms.ScheduleFormInsert;
import com.nt.votationt.model.Schedule;
import com.nt.votationt.repository.ScheduleRepository;
import com.nt.votationt.service.PersonService;
import com.nt.votationt.service.ScheduleService;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class InsertSchedule {

	@InjectMocks
	private ScheduleService formservice;

	@MockBean
	private ScheduleRepository repository;
	@MockBean
	private PersonService personservice;

	@Test
	public void insertSchedulePass() {
		ScheduleFormInsert form = new ScheduleFormInsert();
		form.setCpfProponent("85239109052");
		form.setPassword("password_desu");
		Mockito.when(personservice.login(form.getCpfProponent(), form.getPassword())).thenReturn(true);
		assertEquals(repository.save(new Schedule(form)), formservice.insertSchedule(form, null));
	}

	@Test
	public void insertErrorUnauthorizedLogin() {
		ScheduleFormInsert form = new ScheduleFormInsert();
		form.setCpfProponent("85239109052");
		form.setPassword("password_desu");
		Mockito.when(personservice.login(form.getCpfProponent(), "WrongPassword")).thenReturn(true);
		assertThrows(UnauthorizedException.class, () -> {
			formservice.insertSchedule(form, null);
		});
	}
}
