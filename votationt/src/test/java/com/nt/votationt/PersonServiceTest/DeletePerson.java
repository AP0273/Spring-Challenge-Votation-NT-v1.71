package com.nt.votationt.PersonServiceTest;

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
import com.nt.votationt.repository.PersonRepository;
import com.nt.votationt.service.PersonService;
import com.nt.votationt.model.Person;
import com.nt.votationt.model.Schedule;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class DeletePerson {

	@InjectMocks
	private PersonService service;

	@MockBean
	private PersonRepository repository;

	@Test
	public void deletePersonErrorNotFound() {
		DeletionForm form = new DeletionForm();
		form.setCpf("85239109052");
		form.setPassword("q123Q788a-");
		Mockito.when(repository.findByCpf(form.getCpf())).thenReturn(null);
		assertThrows(ResourceNotFoundExeception.class, () -> {
			service.deletePerson(form);
		});
	}

	@Test
	public void deleteScheduleErrorWrongPassword() {
		DeletionForm form = new DeletionForm();
		form.setCpf("85239109052");
		Schedule schedule = new Schedule();
		schedule.setCpfProponent(form.getCpf());
		Mockito.when(repository.findByCpf(form.getCpf())).thenReturn(new Person());
		Mockito.when(repository.findByCpfAndPassword(form.getCpf(), form.getPassword())).thenReturn(null);
		var exception = assertThrows(UnauthorizedException.class, () -> {
			service.deletePerson(form);
		});
		assertEquals("Unauthorized Wrong Password", exception.getMessage());
	}

}
