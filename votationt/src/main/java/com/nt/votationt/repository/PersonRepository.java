package com.nt.votationt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.votationt.model.Person;

public interface PersonRepository extends JpaRepository<Person, String> {

	Person findByCpf(String cpf);

	List<Person> findByFullnameIgnoreCase(String Name);

	List<Person> findByCanVote(boolean can_vote);

	Person findByCpfAndPassword(String cpf, String password);

	Person findByPhone(String phone);

	Person findByEmail(String email);

}
