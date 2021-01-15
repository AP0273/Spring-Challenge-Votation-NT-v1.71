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
import com.nt.votationt.forms.DeletionForm;
import com.nt.votationt.model.Schedule;
import com.nt.votationt.model.Vote;
import com.nt.votationt.repository.ScheduleRepository;
import com.nt.votationt.repository.VoteRepository;
import com.nt.votationt.service.PersonService;
import com.nt.votationt.service.VoteService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class DeleteVote {

	@InjectMocks
	private VoteService voteservice;
	@MockBean
	private VoteRepository voterepository;
	@MockBean
	private ScheduleRepository schedulerepository;
	@MockBean
	private PersonService personservice;
	@MockBean
	private DateCheck datecheck;

	@Test
	public void deleteVoteErrorNotFound() {
		Long id = 1L;
		DeletionForm form = new DeletionForm();
		form.setCpf("85239109052");
		Mockito.when(voterepository.FindById(id)).thenReturn(null);
		assertThrows(ResourceNotFoundExeception.class, () -> {
			voteservice.deleteVote(form, id);
		});
	}

	@Test
	public void deleteVoteErrorOnlyTheAuthorCanDelete() {
		Long id = 1L;
		DeletionForm form = new DeletionForm();
		form.setCpf("85239109052");
		Vote vote = new Vote();
		vote.setCpfPerson("45673578901");
		Mockito.when(voterepository.FindById(id)).thenReturn(vote);
		var exception = assertThrows(UnauthorizedException.class, () -> {
			voteservice.deleteVote(form, id);
		});
		assertEquals("Unauthorized, Only the Author can delete the vote", exception.getMessage());
	}

	@Test
	public void deleteVoteErrorWrongPassword() {
		Long id = 1L;
		DeletionForm form = new DeletionForm();
		form.setCpf("85239109052");
		Vote vote = new Vote();
		vote.setCpfPerson(form.getCpf());
		Mockito.when(voterepository.FindById(id)).thenReturn(vote);
		Mockito.when(personservice.login(form.getCpf(), form.getPassword())).thenReturn(false);
		var exception = assertThrows(UnauthorizedException.class, () -> {
			voteservice.deleteVote(form, id);
		});
		assertEquals("Unauthorized Wrong Password", exception.getMessage());
	}

	@Test
	public void deleteVoteErrorAlreadyEndedVotation() {
		Long id = 1L;
		DeletionForm form = new DeletionForm();
		form.setCpf("85239109052");
		Vote vote = new Vote();
		vote.setIdschedule(1L);
		vote.setCpfPerson(form.getCpf());
		Mockito.when(voterepository.FindById(id)).thenReturn(vote);
		Mockito.when(personservice.login(form.getCpf(), form.getPassword())).thenReturn(true);
		Mockito.when(schedulerepository.findByIdschedule(vote.getIdschedule())).thenReturn(new Schedule());
		Mockito.when(datecheck.isOnGoing(new Schedule())).thenReturn(false);
		var exception = assertThrows(UnauthorizedException.class, () -> {
			voteservice.deleteVote(form, id);
		});
		assertEquals("Error, is not possible delete a vote on a ended votation", exception.getMessage());
	}
}
