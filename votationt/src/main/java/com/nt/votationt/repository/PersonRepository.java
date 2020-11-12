package com.nt.votationt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.nt.votationt.model.Person;

public interface PersonRepository extends JpaRepository<Person,Long> {
    
	@Query("FROM Person WHERE cpf = ?1")
	Person PersonExist(Long cpf);
	Person findByCpf(long cpf);
	Person findByFullNameIgnoreCase(String Name);
	List <Person> findBycanVote(boolean canVote);
	@Query("FROM Person WHERE cpf = ?1 AND password = ?2")
	Person AuthPerson(Long cpf,String password);
}
	

