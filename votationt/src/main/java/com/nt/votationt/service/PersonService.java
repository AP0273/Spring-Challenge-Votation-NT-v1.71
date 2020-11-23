package com.nt.votationt.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nt.votationt.model.Person;
import com.nt.votationt.repository.PersonRepository;
import com.nt.votationt.util.HttpGet;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;

	public Object insertPerson(Person person) {
		person.setcanVote(false);
		String GetVote = null;
		try {
			GetVote = HttpGet.GetCpfState(person.getCpf());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String Able = "{\"status\":\"ABLE_TO_VOTE\"}";
		String Unable = "{\"status\":\"UNABLE_TO_VOTE\"}";
		System.out.println("CPF: " + person.getCpf() + " --> " + GetVote);
		Person PDB = repository.PersonExist(person.getCpf());
		if (PDB == null) {
			if (GetVote.equals(Able)) {
				person.setcanVote(true);
				return repository.save(person);
			}
			if (GetVote.equals(Unable)) {
				return repository.save(person);
			} else {
				return "Invalid CPF";
			}
		} else {
			return "CPF Already Registred";
		}

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

	/*
	 * public Object FindByFname(String fname) { Person person =
	 * repository.FindByFname(fname); if(person != null) { return person; }else {
	 * return "Pessoa não Encontrada"; } }
	 */
	public Person findByFullNameIgnoreCase(String Name) {
		return repository.findByFullNameIgnoreCase(Name);
	}

	public List<Person> getAllPerson() {
		List<Person> list = repository.findAll();
		return list;
	}

	public List<Person> findBycanVote(Boolean canVote) {

		List<Person> list = repository.findBycanVote(canVote);
		/*
		 * for (Person e : list) { if(e.iscanVote() != canVote) { list.remove(e); } }
		 */
		return list;
	}

}
