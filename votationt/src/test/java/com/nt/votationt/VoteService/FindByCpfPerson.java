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
public class FindByCpfPerson {

	@InjectMocks
	private VoteService service;
	
	@MockBean
	private VoteRepository repository;
	
	@Test
	public void findByCpfPersonPass() {
		String cpf = "85239109052";
		List<Vote> votelist = new ArrayList<Vote>();
 		Vote vote = new Vote();
 		votelist.add(vote);
		Mockito.when(repository.findByCpfPerson(cpf)).thenReturn(votelist);
		assertEquals(votelist, service.findByCpfPerson(cpf));
	}
	@Test
	public void findByCpfPersonErrorNotFound() {
		String cpf = "85239109052";
		List<Vote> votelist = new ArrayList<Vote>();
		Mockito.when(repository.findByCpfPerson(cpf)).thenReturn(votelist);
		assertThrows(ResourceNotFoundExeception.class, () -> {service.findByCpfPerson(cpf);});
	}
}
