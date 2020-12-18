package com.nt.votationt.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nt.votationt.model.Person;
import com.nt.votationt.repository.PersonRepository;
import com.nt.votationt.util.RestTemplateGet;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;

	public Object insertPerson(Person person) {
		if (CpfisAbleToVote(person) != null) {
			if (CpfAlreadyExist(person) == false) {
				repository.save(person);
			} else {
				return "CPF Already Registred";
			}
		} else {
			return "Invalid CPF";
		}
		return person;
	}

	public String DeletePerson(Long cpf) {
		Person person = repository.getOne(cpf);
		if (person != null) {
			repository.delete(person);
			return "Pessoa Deletada Com Sucesso!";
		} else {
			return "Grupo Deletado Com Sucesso!";
		}
	}

	public Person FindPerson(Long cpf) {
		return repository.findByCpf(cpf);
	}

	public Person findByFullNameIgnoreCase(String Name) {
		return repository.findByFullNameIgnoreCase(Name);
	}

	public List<Person> getAllPerson() {
		List<Person> list = repository.findAll();
		return list;
	}

	public List<Person> findBycanVote(Boolean canVote) {

		List<Person> list = repository.findByCan_vote(canVote);
		return list;
	}

	// checkings

	public Boolean CpfAlreadyExist(Person person) {
		Person PDB = repository.findByCpf(person.getCpf());
		if (PDB != null) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean CpfisAbleToVote(Person person) {
		person.setcanVote(false);
		String GetVote = null;
		GetVote = RestTemplateGet.GetCpfState(person.getCpf());
		String Able = "{\"status\":\"ABLE_TO_VOTE\"}";
		String Unable = "{\"status\":\"UNABLE_TO_VOTE\"}";
		System.out.println("CPF: " + person.getCpf() + " --> " + GetVote);
		if (GetVote.equals(Able)) {
			person.setcanVote(true);
			return true;
		} else if (GetVote.equals(Unable)) {
			return false;
		} else {
			return null;
		}
	}

}
