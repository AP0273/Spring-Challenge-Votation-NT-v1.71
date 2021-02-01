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
import com.nt.votationt.forms.DeletionForm;
import com.nt.votationt.model.Schedule;
import com.nt.votationt.repository.ScheduleRepository;
import com.nt.votationt.service.PersonService;
import com.nt.votationt.service.ScheduleService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class DeleteSchedule {

	@InjectMocks
	private ScheduleService service;

	@MockBean
	private ScheduleRepository repository;

	@MockBean
	private PersonService personservice;

	@Test
	public void deleteScheduleErrorNotFound() {
		Long id = 1L;
		DeletionForm form = new DeletionForm();
		form.setCpf("85239109052");
		Mockito.when(repository.findByIdschedule(id)).thenReturn(null);
		assertThrows(ResourceNotFoundExeception.class, () -> {
			service.deleteSchedule(form, id);
		});

	}

	@Test
	public void deleteScheduleErrorOnlyTheAuthorCanDelete() {
		Long id = 1L;
		DeletionForm form = new DeletionForm();
		form.setCpf("85239109052");
		Schedule schedule = new Schedule();
		schedule.setCpfProponent("025939109053");
		Mockito.when(repository.findByIdschedule(id)).thenReturn(schedule);
		var exception = assertThrows(UnauthorizedException.class, () -> {
			service.deleteSchedule(form, id);
		});
		assertEquals("Unauthorized, Only the Author can delete the schedule", exception.getMessage());
	}

	@Test
	public void deleteScheduleErrorWrongPassword() {
		Long id = 1L;
		DeletionForm form = new DeletionForm();
		form.setCpf("85239109052");
		Schedule schedule = new Schedule();
		schedule.setCpfProponent(form.getCpf());
		Mockito.when(repository.findByIdschedule(id)).thenReturn(schedule);
		Mockito.when(personservice.login(form.getCpf(), form.getPassword())).thenReturn(false);
		var exception = assertThrows(UnauthorizedException.class, () -> {
			service.deleteSchedule(form, id);
		});
		assertEquals("Unauthorized Wrong Password", exception.getMessage());
	}

}
