package com.nt.votationt.VoteService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nt.votationt.DateCheck.DateCheck;
import com.nt.votationt.exceptions.ResourceNotFoundExeception;
import com.nt.votationt.exceptions.UnauthorizedException;
import com.nt.votationt.forms.VoteFormInsert;
import com.nt.votationt.model.Person;
import com.nt.votationt.model.Schedule;
import com.nt.votationt.model.Vote;
import com.nt.votationt.repository.PersonRepository;
import com.nt.votationt.repository.ScheduleRepository;
import com.nt.votationt.repository.VoteRepository;
import com.nt.votationt.service.PersonService;
import com.nt.votationt.service.ScheduleService;
import com.nt.votationt.service.VoteService;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class InsertVote {

	@InjectMocks
	private VoteService formservice;

	@MockBean
	private VoteRepository formrepository;

	@MockBean
	private ScheduleRepository schedulerepository;

	@MockBean
	private ScheduleService scheduleservice;

	@MockBean
	private PersonService personservice;

	@MockBean
	private PersonRepository personrepository;

	@MockBean
	private DateCheck datecheck;

	@Test
	public void insertVotePass() {
		VoteFormInsert form = new VoteFormInsert();
		form.setIdschedule(1L);
		form.setCpfPerson("85239109052");
		form.setPassword("Password Desu");
		Schedule schedule = new Schedule();
		schedule.setIdschedule(1L);
		Person person = new Person();
		person.setCanVote(true);
		Mockito.when(scheduleservice.isScheduleExist(schedule.getIdschedule())).thenReturn(true);
		Mockito.when(schedulerepository.findByIdschedule(form.getIdschedule())).thenReturn(schedule);
		Mockito.when(personservice.login(form.getCpfPerson(), form.getPassword())).thenReturn(true);
		Mockito.when(personrepository.findByCpf(form.getCpfPerson())).thenReturn(person);
		Mockito.when(datecheck.isOnGoing(schedule)).thenReturn(true);
		Mockito.when(scheduleservice.isAlreadyVoted(form)).thenReturn(false);
		assertEquals(formrepository.save(new Vote(form)), formservice.insertVote(form));
	}

	@Test
	public void insertVoteErrorScheduleNotFound() {
		VoteFormInsert form = new VoteFormInsert();
		form.setIdschedule(1L);
		form.setCpfPerson("85239109052");
		form.setPassword("Password Desu");
		Schedule schedule = new Schedule();
		schedule.setIdschedule(1L);
		Person person = new Person();
		person.setCanVote(true);
		Mockito.when(scheduleservice.isScheduleExist(schedule.getIdschedule())).thenReturn(false);
		var Exception = assertThrows(ResourceNotFoundExeception.class, () -> {
			formservice.insertVote(form);
		});
		assertEquals("Error Schedule Not Found", Exception.getMessage());
	}

	@Test
	public void insertVoteErrorUnauthorizedLogin() {
		VoteFormInsert form = new VoteFormInsert();
		form.setIdschedule(1L);
		form.setCpfPerson("85239109052");
		form.setPassword("Password Desu");
		Schedule schedule = new Schedule();
		schedule.setIdschedule(1L);
		Person person = new Person();
		person.setCanVote(true);
		Mockito.when(scheduleservice.isScheduleExist(schedule.getIdschedule())).thenReturn(true);
		Mockito.when(schedulerepository.findByIdschedule(form.getIdschedule())).thenReturn(schedule);
		Mockito.when(personservice.login(form.getCpfPerson(), form.getPassword())).thenReturn(false);
		var Exception = assertThrows(UnauthorizedException.class, () -> {
			formservice.insertVote(form);
		});
		assertEquals("CPF or Password are Wrong or Unregistred", Exception.getMessage());
	}

	@Test
	public void insertVoteErrorPersonUnableToVote() {
		VoteFormInsert form = new VoteFormInsert();
		form.setIdschedule(1L);
		form.setCpfPerson("85239109052");
		form.setPassword("Password Desu");
		Schedule schedule = new Schedule();
		schedule.setIdschedule(1L);
		Person person = new Person();
		person.setCanVote(false);
		Mockito.when(scheduleservice.isScheduleExist(schedule.getIdschedule())).thenReturn(true);
		Mockito.when(schedulerepository.findByIdschedule(form.getIdschedule())).thenReturn(schedule);
		Mockito.when(personservice.login(form.getCpfPerson(), form.getPassword())).thenReturn(true);
		Mockito.when(personrepository.findByCpf(form.getCpfPerson())).thenReturn(person);
		var Exception = assertThrows(UnauthorizedException.class, () -> {
			formservice.insertVote(form);
		});
		assertEquals("CPF Unable to Vote", Exception.getMessage());
	}

	@Test
	public void insertVoteErrorVotingClosed() {
		VoteFormInsert form = new VoteFormInsert();
		form.setIdschedule(1L);
		form.setCpfPerson("85239109052");
		form.setPassword("Password Desu");
		Schedule schedule = new Schedule();
		schedule.setIdschedule(1L);
		Person person = new Person();
		person.setCanVote(true);
		Mockito.when(scheduleservice.isScheduleExist(schedule.getIdschedule())).thenReturn(true);
		Mockito.when(schedulerepository.findByIdschedule(form.getIdschedule())).thenReturn(schedule);
		Mockito.when(personservice.login(form.getCpfPerson(), form.getPassword())).thenReturn(true);
		Mockito.when(personrepository.findByCpf(form.getCpfPerson())).thenReturn(person);
		Mockito.when(datecheck.isOnGoing(schedule)).thenReturn(false);
		var Exception = assertThrows(UnauthorizedException.class, () -> {
			formservice.insertVote(form);
		});
		assertEquals("Error, this voting is closed", Exception.getMessage());
	}

	@Test
	public void insertVoteErrorAlreadyVoted() {
		VoteFormInsert form = new VoteFormInsert();
		form.setIdschedule(1L);
		form.setCpfPerson("85239109052");
		form.setPassword("Password Desu");
		Schedule schedule = new Schedule();
		schedule.setIdschedule(1L);
		Person person = new Person();
		person.setCanVote(true);
		Mockito.when(scheduleservice.isScheduleExist(schedule.getIdschedule())).thenReturn(true);
		Mockito.when(schedulerepository.findByIdschedule(form.getIdschedule())).thenReturn(schedule);
		Mockito.when(personservice.login(form.getCpfPerson(), form.getPassword())).thenReturn(true);
		Mockito.when(personrepository.findByCpf(form.getCpfPerson())).thenReturn(person);
		Mockito.when(datecheck.isOnGoing(schedule)).thenReturn(true);
		Mockito.when(scheduleservice.isAlreadyVoted(form)).thenReturn(true);
		var exception = assertThrows(UnauthorizedException.class, () -> {
			formservice.insertVote(form);
		});
		assertEquals("Error, You Already Voted", exception.getMessage());
	}
}
