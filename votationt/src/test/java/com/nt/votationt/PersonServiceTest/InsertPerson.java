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

import com.nt.votationt.exceptions.AlreadyExistException;
import com.nt.votationt.exceptions.BadRequestException;

import com.nt.votationt.input_validations.Verify;
import com.nt.votationt.model.Person;
import com.nt.votationt.repository.PersonRepository;
import com.nt.votationt.rest.HerokuAnswer;
import com.nt.votationt.rest.HerokuClient;
import com.nt.votationt.service.PersonService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class InsertPerson {

	@MockBean
	private PersonRepository repository;
	@MockBean
	private HerokuClient herokuclient;
	@MockBean
	private Verify verify;
	@InjectMocks
	private PersonService service;

	@Test
	public void insertPersonErrorInvalidCpf() {
		String type = "insert";
		Person person = new Person();
		person.setAdress("St Something some Number");
		// person.setBirthday(Date.valueOf("1991/11/11"));
		person.setCpf("85239109052");
		person.setCanVote(false);
		Mockito.when(verify.verifyPhone(person.getPhone())).thenReturn(true);
		Mockito.when(verify.verifyEmail(person.getEmail())).thenReturn(true);
		Mockito.when(verify.verifyPassword(person.getPassword())).thenReturn(true);
		assertThrows(NullPointerException.class, () -> {
			service.insertPerson(person, type);
		});
	}

	@Test
	public void insertPersonErrorAlreadyExist() {
		String type = "insert";
		Person person = new Person();
		person.setAdress("St Something some Number");
		// person.setBirthday(Date.valueOf("1991/11/11"));
		person.setCpf("85239109052");
		person.setCanVote(false);
		Mockito.when(verify.verifyPhone(person.getPhone())).thenReturn(true);
		Mockito.when(verify.verifyEmail(person.getEmail())).thenReturn(true);
		Mockito.when(verify.verifyPassword(person.getPassword())).thenReturn(true);
		// Mockito.when(service.cpfIsValid(person)).thenReturn(null);
		HerokuAnswer herokuanswer = new HerokuAnswer();
		herokuanswer.setStatus("ABLE_TO_VOTE");
		Mockito.when(herokuclient.getCpfState(person.getCpf())).thenReturn(herokuanswer);
		Mockito.when(repository.findByCpf(person.getCpf())).thenReturn(person);
		var exception = assertThrows(AlreadyExistException.class, () -> {
			service.insertPerson(person, type);
		});
		assertEquals("Cpf Already Registred", exception.getMessage());
	}

	@Test
	public void insertPersonErrorInvalidPhone() {
		String type = "insert";
		Person person = new Person();
		person.setAdress("St Something some Number");
		// person.setBirthday(Date.valueOf("1991/11/11"));
		person.setCpf("85239109052");
		person.setCanVote(false);
		Mockito.when(verify.verifyPhone(person.getPhone())).thenReturn(false);
		var exception = assertThrows(BadRequestException.class, () -> {
			service.insertPerson(person, type);
		});
		assertEquals("Invalid Phone Number", exception.getMessage());
	}

	@Test
	public void insertPersonErrorInvalidEmail() {
		String type = "insert";
		Person person = new Person();
		person.setAdress("St Something some Number");
		// person.setBirthday(Date.valueOf("1991/11/11"));
		person.setCpf("85239109052");
		person.setCanVote(false);
		Mockito.when(verify.verifyPhone(person.getPhone())).thenReturn(true);
		Mockito.when(verify.verifyEmail(person.getEmail())).thenReturn(false);
		var exception = assertThrows(BadRequestException.class, () -> {
			service.insertPerson(person, type);
		});
		assertEquals("Invalid Email Syntax, Example yourname@domain.com", exception.getMessage());
	}

	@Test
	public void insertPersonErrorWeakPassword() {
		String type = "insert";
		Person person = new Person();
		person.setAdress("St Something some Number");
		// person.setBirthday(Date.valueOf("1991/11/11"));
		person.setCpf("85239109052");
		person.setCanVote(false);
		Mockito.when(verify.verifyPhone(person.getPhone())).thenReturn(true);
		Mockito.when(verify.verifyEmail(person.getEmail())).thenReturn(true);
		Mockito.when(verify.verifyPassword(person.getPassword())).thenReturn(false);
		var exception = assertThrows(BadRequestException.class, () -> {
			service.insertPerson(person, type);
		});
		assertEquals(
				"Weak Password, should be at least 8-character long containing upper and lower case letters,numbers and symbols.",
				exception.getMessage());
	}

	@Test
	public void insertPersonErrorPhoneAlreadyExist() {
		String type = "insert";
		Person person = new Person();
		person.setAdress("St Something some Number");
		person.setCpf("85239109052");
		person.setPhone("+5540028922");
		person.setCanVote(false);
		Mockito.when(verify.verifyPhone(person.getPhone())).thenReturn(true);
		Mockito.when(repository.findByPhone(person.getPhone())).thenReturn(person);
		var exception = assertThrows(AlreadyExistException.class, () -> {
			service.insertPerson(person, type);
		});
		assertEquals("This Phone is Already Used by Another User", exception.getMessage());
	}

	@Test
	public void insertPersonErrorEmailAlreadyExist() {
		String type = "insert";
		Person person = new Person();
		person.setAdress("St Something some Number");
		person.setCpf("85239109052");
		person.setEmail("email@domain.com");
		person.setCanVote(false);
		Mockito.when(verify.verifyPhone(person.getPhone())).thenReturn(true);
		Mockito.when(verify.verifyEmail(person.getEmail())).thenReturn(true);
		Mockito.when(repository.findByEmail(person.getEmail())).thenReturn(person);
		var exception = assertThrows(AlreadyExistException.class, () -> {
			service.insertPerson(person, type);
		});
		assertEquals("This Email is Already in Use by Another User", exception.getMessage());
	}

	@Test
	public void insertPersonPass() {
		String type = "insert";
		Person person = new Person();
		person.setAdress("St Something some Number");
		// person.setBirthday(Date.valueOf("1991/11/11"));
		person.setCpf("85239109052");
		person.setPassword("Password+123");
		person.setCanVote(false);
		Mockito.when(verify.verifyPhone(person.getPhone())).thenReturn(true);
		Mockito.when(verify.verifyEmail(person.getEmail())).thenReturn(true);
		Mockito.when(verify.verifyPassword(person.getPassword())).thenReturn(true);
		HerokuAnswer herokuanswer = new HerokuAnswer();
		herokuanswer.setStatus("ABLE_TO_VOTE");
		Mockito.when(herokuclient.getCpfState(person.getCpf())).thenReturn(herokuanswer);
		Mockito.when(repository.findByCpf(person.getCpf())).thenReturn(null);
		assertEquals(repository.save(person), service.insertPerson(person, type));
	}
}
