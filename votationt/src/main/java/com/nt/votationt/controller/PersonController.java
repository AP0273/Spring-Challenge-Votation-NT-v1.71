package com.nt.votationt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.votationt.forms.DeletionForm;
import com.nt.votationt.forms.PersonFormUpdate;
import com.nt.votationt.model.Person;
import com.nt.votationt.service.PersonService;

@RestController
@RequestMapping(value = "Person")
public class PersonController {

	@Autowired
	private PersonService service;

	public PersonController(PersonService service) {
		this.service = service;
	}

	@PostMapping
	public Person create(@RequestBody Person person) {
		return service.insertPerson(person, "insert");
	}

	@PutMapping
	public void update(@RequestBody PersonFormUpdate form) {
		service.updatePerson(form);
	}

	@GetMapping("/getbyCpf/{cpf}")
	public Person getByCpf(@PathVariable String cpf) {
		return service.findPerson(cpf);
	}

	@GetMapping("/getbyName/{Name}")
	public List<Person> getById(@PathVariable String Name) {
		return service.findByFullNameIgnoreCase(Name);
	}

	@GetMapping("/getbycanVote/{canVote}")
	public List<Person> getByCpf(@PathVariable boolean canVote) {
		return service.findBycanVote(canVote);
	}

	@GetMapping
	public List<Person> getAll() {
		return service.getAllPerson();
	}

	@DeleteMapping("/deletebyid/{id}")
	public void deleteById(@RequestBody DeletionForm form) {
		service.deletePerson(form);
	}

	@PatchMapping
	public void patchUpdate(@RequestBody PersonFormUpdate form) {
		service.updatePerson(form);
	}
}
