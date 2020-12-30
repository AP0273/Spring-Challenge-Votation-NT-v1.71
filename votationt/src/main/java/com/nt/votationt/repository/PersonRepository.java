package com.nt.votationt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.votationt.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {


	Person findByCpf(long cpf);

	Person findByFullNameIgnoreCase(String Name);


	List<Person>findByCanVote(boolean can_vote);


	Person findByCpfAndPassword(Long cpf, String password);
}
