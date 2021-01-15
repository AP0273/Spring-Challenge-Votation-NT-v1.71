package com.nt.votationt.PersonServiceTest;

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
import com.nt.votationt.forms.PersonFormUpdate;
import com.nt.votationt.model.Person;
import com.nt.votationt.repository.PersonRepository;
import com.nt.votationt.service.PersonService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UpdatePerson {

	@InjectMocks
	private PersonService service;

	@MockBean
	private PersonRepository repository;

	@Test
	public void updatePersonErrorNotFound() {
		PersonFormUpdate form = new PersonFormUpdate();
		form.setCpf("85239109052");
		Mockito.when(repository.findByCpf(form.getCpf())).thenReturn(null);
		assertThrows(ResourceNotFoundExeception.class, () -> {
			service.updatePerson(form);
		});
	}

	@Test
	public void updatePersonErrorWrongPassword() {
		PersonFormUpdate form = new PersonFormUpdate();
		form.setCpf("85239109052");
		form.setNowpassword("adsadQ*212");
		Person person = new Person();
		person.setPassword("ASddad*66");
		Mockito.when(repository.findByCpf(form.getCpf())).thenReturn(person);
		assertThrows(UnauthorizedException.class, () -> {
			service.updatePerson(form);
		});
	}

}
