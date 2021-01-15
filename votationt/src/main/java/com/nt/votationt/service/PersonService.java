package com.nt.votationt.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.votationt.exceptions.AlreadyExistException;
import com.nt.votationt.exceptions.BadRequestException;
import com.nt.votationt.exceptions.ResourceNotFoundExeception;
import com.nt.votationt.exceptions.UnauthorizedException;
import com.nt.votationt.forms.DeletionForm;
import com.nt.votationt.forms.PersonFormUpdate;
import com.nt.votationt.input_validations.Verify;
import com.nt.votationt.model.Person;
import com.nt.votationt.repository.PersonRepository;
import com.nt.votationt.rest.HerokuAnswer;
import com.nt.votationt.rest.HerokuClient;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;

	@Autowired
	private HerokuClient herokuclient;

	@Autowired
	private Verify verify;

	public Person insertPerson(Person person, String type) {
		if (verify.verifyPhone(person.getPhone()) == false)	throw new BadRequestException("Invalid Phone Number");
		if (phoneAlreadyExist(person.getPhone()) == true) throw new AlreadyExistException("This Phone is Already Used by Another User");
		if (verify.verifyEmail(person.getEmail()) == false) throw new BadRequestException("Invalid Email Syntax, Example yourname@domain.com");
		if (emailAlreadyExist(person.getEmail()) == true) throw new AlreadyExistException("This Email is Already in Use by Another User");
		if (verify.verifyPassword(person.getPassword()) == false)throw new BadRequestException("Weak Password, should be at least 8-character long containing upper and lower case letters,numbers and symbols.");
		if (type == "insert" && cpfIsValid(person) == false)throw new UnauthorizedException("Invalid CPF");
		if (personAlreadyExist(person.getCpf()) == true && (type != "update") == true) throw new AlreadyExistException("Cpf Already Registred");
		return repository.save(person);
	}

	public void updatePerson(PersonFormUpdate form) {
		Person person = repository.findByCpf(form.getCpf());
		if (person == null)throw new ResourceNotFoundExeception("User Not Found Can't update");
		if (!form.getNowpassword().equals(person.getPassword()))throw new UnauthorizedException("Unauthorized Wrong Password");
		insertPerson(new Person(form), "update");
	}

	public boolean personAlreadyExist(String cpf) {
		Person person = repository.findByCpf(cpf);
		boolean result = true;
		if (person == null)
			result = false;
		return result;
	}

	public boolean phoneAlreadyExist(String phone) {
		Person person = repository.findByPhone(phone);
		boolean result = true;
		if (person == null)
			result = false;
		return result;
	}

	public boolean emailAlreadyExist(String email) {
		Person person = repository.findByEmail(email);
		boolean result = true;
		if (person == null)
			result = false;
		return result;
	}

	public Person findPerson(String cpf) {
		Person person = repository.findByCpf(cpf);
		if (person == null)	throw new ResourceNotFoundExeception("Cpf '" + cpf + "' not found.");
		return person;
	}

	public void deletePerson(DeletionForm form) {
		if (personAlreadyExist(form.getCpf()) == false)	throw new ResourceNotFoundExeception("Cpf '" + form.getCpf() + "' not found.");
		Person person = repository.findByCpf(form.getCpf());
		if (login(form.getCpf(), form.getPassword()) == false) throw new UnauthorizedException("Unauthorized Wrong Password");
		repository.delete(person);
	}

	public List<Person> findByFullNameIgnoreCase(String Name) {
		List<Person> personlist = repository.findByFullnameIgnoreCase(Name);
		if (personlist.isEmpty()) throw new ResourceNotFoundExeception("None results found for the name " + Name);
		return personlist;
	}

	public List<Person> getAllPerson() {
		List<Person> personlist = repository.findAll();
		if (personlist.isEmpty()) throw new ResourceNotFoundExeception("None results found");
		return personlist;
	}

	public List<Person> findBycanVote(Boolean canVote) {

		List<Person> personlist = repository.findByCanVote(canVote);
		if (personlist.isEmpty())throw new ResourceNotFoundExeception("None results found");
		return personlist;
	}

	public boolean cpfIsValid(Person person) {
		HerokuAnswer statusCpf = herokuclient.getCpfState(person.getCpf());
		System.out.println("CPF: " + person.getCpf() + " --> " + statusCpf.getStatus());
		boolean result;
		if (statusCpf.getStatus().equals("ABLE_TO_VOTE")) {
			System.out.println("a");
			person.setCanVote(true);
			result = true;
		} else if (statusCpf.getStatus().equals("UNABLE_TO_VOTE")) {
			System.out.println("u");
			person.setCanVote(false);
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	public boolean login(String cpf, String password) {
		Person pdb = repository.findByCpfAndPassword(cpf, password);
		boolean result = false;
		if (pdb != null)
			result = true;
		return result;
	}
}
