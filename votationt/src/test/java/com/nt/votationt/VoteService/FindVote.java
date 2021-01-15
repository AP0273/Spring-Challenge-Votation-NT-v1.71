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

import com.nt.votationt.exceptions.ResourceNotFoundExeception;
import com.nt.votationt.model.Vote;
import com.nt.votationt.repository.VoteRepository;
import com.nt.votationt.service.VoteService;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class FindVote {
	@InjectMocks
	private VoteService service;
	
	@MockBean
	private VoteRepository repository;

	@Test
	public void findVotePass() {
		Long id=1L;
		Vote vote = new Vote();
		Mockito.when(repository.FindById(id)).thenReturn(vote);
		assertEquals(vote, service.findVote(id));
	}
	@Test
	public void findVoteErrorNotFound() {
		Long id=1L;
		Mockito.when(repository.FindById(id)).thenReturn(null);
		assertThrows(ResourceNotFoundExeception.class, () -> {service.findVote(id);});
	}
}
