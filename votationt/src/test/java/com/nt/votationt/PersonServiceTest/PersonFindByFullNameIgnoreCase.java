package com.nt.votationt.PersonServiceTest;

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
import com.nt.votationt.model.Person;
import com.nt.votationt.repository.PersonRepository;
import com.nt.votationt.service.PersonService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class PersonFindByFullNameIgnoreCase {

	@InjectMocks
	private PersonService service;

	@MockBean
	private PersonRepository repository;

	@Test
	public void findByFullNamePass() {
		String name = "Random Name";
		Person person = new Person();
		List<Person> listperson = new ArrayList<Person>();
		listperson.add(person);
		Mockito.when(repository.findByFullnameIgnoreCase(name)).thenReturn(listperson);
		assertEquals(listperson, service.findByFullNameIgnoreCase(name));
	}

	@Test
	public void findByFullNameErrorNotFound() {
		String name = "Random Name";
		List<Person> listperson = new ArrayList<Person>();
		Mockito.when(repository.findByFullnameIgnoreCase(name)).thenReturn(listperson);
		assertThrows(ResourceNotFoundExeception.class, () -> {
			service.findByFullNameIgnoreCase(name);
		});
	}
}
