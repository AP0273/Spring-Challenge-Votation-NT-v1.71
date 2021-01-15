package com.nt.votationt.VoteService;

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
import com.nt.votationt.model.Vote;
import com.nt.votationt.repository.VoteRepository;
import com.nt.votationt.service.VoteService;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class GetAllVote {

	@InjectMocks
	private VoteService service;
	
	@MockBean
	private VoteRepository repository;
	
	@Test
	public void getAllVotePass() {
		Vote vote = new Vote();
		List<Vote> votelist = new ArrayList<Vote>();
		votelist.add(vote);
		Mockito.when(repository.findAll()).thenReturn(votelist);
	    assertEquals(votelist, service.getAllVote());
	}
	@Test
	public void getAllVoteErrorNotFound() {
		List<Vote> votelist = new ArrayList<Vote>();
		Mockito.when(repository.findAll()).thenReturn(votelist);
	    assertThrows(ResourceNotFoundExeception.class, () -> {service.getAllVote();});
	}
}
