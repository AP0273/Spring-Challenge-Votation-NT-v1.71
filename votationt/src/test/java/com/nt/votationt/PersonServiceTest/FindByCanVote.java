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

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)

public class FindByCanVote {

	@InjectMocks
	private PersonService service;

	@MockBean
	private PersonRepository repository;

	@Test
	public void findByCanVotePass() {
		Boolean canvote = true;
		Person person = new Person();
		List<Person> listperson = new ArrayList<Person>();
		listperson.add(person);
		Mockito.when(repository.findByCanVote(canvote)).thenReturn(listperson);
		assertEquals(listperson, service.findBycanVote(canvote));
	}

	@Test
	public void findByCanVoteNotFound() {
		Boolean canvote = true;
		List<Person> listperson = new ArrayList<Person>();
		Mockito.when(repository.findByCanVote(canvote)).thenReturn(listperson);
		assertThrows(ResourceNotFoundExeception.class, () -> {
			service.findBycanVote(canvote);
		});
	}
}
