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

import com.nt.votationt.model.Person;
import com.nt.votationt.service.PersonService;





@RestController
@RequestMapping(value="Person")
public class PersonController {

	@Autowired
	private PersonService service;
	
	public PersonController (PersonService service) {
		this.service = service;
	}
	
	@PostMapping
	public Object create(@RequestBody Person person) {
		if(person instanceof Person) {
		return service.insertPerson(person);
		}else
		return "CPF Invalido";
		}
	@PutMapping
	public Object update(@RequestBody Person person) {
		if(person instanceof Person) {
		return service.insertPerson(person);
		}else
		return "CPF Invalido";
		}
	@GetMapping("/getbyCpf/{cpf}")
	public Person getByCpf(@PathVariable Long cpf) {
		return service.FindPerson(cpf);
	}
	@GetMapping("/getbyName/{Name}")
	public Person getById(@PathVariable String Name) {
		return service.findByFullNameIgnoreCase(Name);
	}
	@GetMapping("/getbycanVote/{canVote}")
	public List <Person> getByCpf(@PathVariable boolean canVote) {
		return service.findBycanVote(canVote);
	}
	@GetMapping
	public List<Person> getAll() {
		return service.getAllPerson();
	}
	
	@DeleteMapping("/deletebyid/{id}")
	public String deleteById(@PathVariable Long id) {
		return service.DeletePerson(id);
	}
	
	@PatchMapping
	public Object patchUpdate(@RequestBody Person person) {
		if(person instanceof Person) {
		return service.insertPerson(person);
		}else
		return "CPF Invalido";
		}
	}


