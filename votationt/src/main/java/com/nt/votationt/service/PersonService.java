package com.nt.votationt.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.votationt.exceptions.AlreadyExistException;
import com.nt.votationt.exceptions.ResourceNotFoundExeception;
import com.nt.votationt.exceptions.UnauthorizedException;
import com.nt.votationt.model.Person;
import com.nt.votationt.repository.PersonRepository;
import com.nt.votationt.rest.HerokuAnswer;
import com.nt.votationt.rest.HerokuClient;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private HerokuClient herokuClient;

	public Person insertPerson(Person person,String type) {
		if (cpfIsValid(person) == false && type != "update") throw new UnauthorizedException("Invalid CPF"); 
			if (personAlreadyExist(person.getCpf()) == true) throw new AlreadyExistException("Cpf Already Registred");
				return repository.save(person);
	}
	public boolean personAlreadyExist(Long cpf) {
	Person person = repository.findByCpf(cpf);
	boolean result= true;
	if (person == null) result = false; 
	System.out.println("Result " + result);
	return result;
	}

	public void deletePerson(Long cpf) {
		if(personAlreadyExist(cpf)==false) throw new ResourceNotFoundExeception("Cpf '" + cpf + "' not found.");
		Person person = repository.getOne(cpf);
			repository.delete(person);
	}

	public Person findPerson(Long cpf) {
		Person person = repository.findByCpf(cpf);
		if(person==null) throw new ResourceNotFoundExeception("Cpf '" + cpf + "' not found.");
		return person;
	}

	public Person findByFullNameIgnoreCase(String Name) {
		return repository.findByFullNameIgnoreCase(Name);
	}

	public List<Person> getAllPerson() {
		List<Person> list = repository.findAll();
		return list;
	}

	public List<Person> findBycanVote(Boolean canVote) {

		List<Person> list = repository.findByCanVote(canVote);
		return list;
	}


	public boolean cpfIsValid(Person person) {
		HerokuAnswer statusCpf = herokuClient.getCpfState(person.getCpf());
		System.out.println("CPF: " + person.getCpf() + " --> " + statusCpf.getStatus());
		boolean result;
		if (statusCpf.getStatus().equals("ABLE_TO_VOTE")) {
			System.out.println("a");
			person.setCanVote(true);
			result = true;
		}else if(statusCpf.getStatus().equals("UNABLE_TO_VOTE")) {
			System.out.println("u");
			person.setCanVote(false);
			result = true;
		}else {
			result=false;
		}
			return result;
		}
	public boolean Login(Long cpf,String password) {
	Person PDB = repository.findByCpfAndPassword(cpf, password);
	boolean result= false;
	if (PDB != null) result=true;
	return result;
	}
	}
	


