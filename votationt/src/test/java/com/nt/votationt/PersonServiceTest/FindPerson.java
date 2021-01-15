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
import com.nt.votationt.model.Person;
import com.nt.votationt.repository.PersonRepository;
import com.nt.votationt.service.PersonService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class FindPerson {

	@InjectMocks
	private PersonService service;

	@MockBean
	private PersonRepository repository;

	@Test
	public void findPersonPass() {
		Person person = new Person();
		person.setCpf("85239109052");
		Mockito.when(repository.findByCpf(person.getCpf())).thenReturn(person);
		assertEquals(person, service.findPerson(person.getCpf()));
	}

	@Test
	public void findPersonErrorNotFound() {
		Person person = new Person();
		person.setCpf("85239109052");
		Mockito.when(repository.findByCpf(person.getCpf())).thenReturn(null);
		assertThrows(ResourceNotFoundExeception.class, () -> {
			service.findPerson(person.getCpf());
		});
	}
}
