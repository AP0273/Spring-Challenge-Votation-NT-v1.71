package com.nt.votationt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.votationt.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	@Query("FROM Person WHERE cpf = ?1")
	//Person PersonExist(Long cpf);

	Person findByCpf(long cpf);

	Person findByFullNameIgnoreCase(String Name);

	@Query("FROM Person WHERE can_vote = ?1")
	List<Person>findByCan_vote(boolean can_vote);

	@Query("FROM Person WHERE cpf = ?1 AND password = ?2")
	Person findByCpfAndPassword(Long cpf, String password);
}
