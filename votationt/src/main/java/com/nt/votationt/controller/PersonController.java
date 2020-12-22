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
@RequestMapping(value = "Person")
public class PersonController {

	@Autowired
	private PersonService service;

	public PersonController(PersonService service) {
		this.service = service;
	}

	@PostMapping
	public Object create(@RequestBody Person person) {
		return service.insertPerson(person);
	}

	@PutMapping
	public Object update(@RequestBody Person person) {
		return service.insertPerson(person);
	}
	
	@GetMapping("/getbyCpf/{cpf}")
	public Person getByCpf(@PathVariable Long cpf)  {
		Person p = service.FindPerson(cpf);
		if(p==null) throw new ResourceNotFoundExeception("Cpf '" + cpf + "' not found.");
		return service.FindPerson(cpf);
	}

	@GetMapping("/getbyName/{Name}")
	public Person getById(@PathVariable String Name) {
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
	public String deleteById(@PathVariable Long id) {
		return service.DeletePerson(id);
	}

	@PatchMapping
	public Object patchUpdate(@RequestBody Person person) {
		return service.insertPerson(person);
	}
}

/* - VoteService
 - reduzir os ifs internos, talvez usando um pattern tipo chain of responsability
 - cuidar métodos não usados... se não são usados, remover
- ScheduleService
 - toString não necessário em alguns momentos onde strings são concatenadas
 - variáveis sem necessidade, usadas só em retornos
 - métodos retornando Object... não usar neste formato, pois fica bem complicado de testar... manter a tipagem e, se necessário parar o fluxo por erros, notificar o spring... mesmo que seja com exceções e usando um exception mapper para capturar e formatar falhas: https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
 - cuidar nomenclatura de métodos, respeitando camelCase
 - @Autowired em field não é aconselhado: https://blog.marcnuri.com/field-injection-is-not-recommended/
- PersonService
 - tentar usar mais um ponto único de retorno no método, pois com vários return espalhados é mais complicado para ler e testar
- PersonRepository
 - @Query não é necessário para buscas simples... o Spring é capaz de gerar os nomes de métodos. Ex.: AuthPerson pode ter @Query removida se escrito como: findByCpfAndPassword(Long cpf, String password)
- Schedule
 - atributos usando snake_case ao invés de camelCase
- RestTemplateGet
 - url do heroku pode ser configurada via application.properties e annotation @Value
 - Objeto de retorno pode ser tipado ao invés de retornar uma string para comparar, pois esta comparação pode falhar
- Estudar um pouco de design patterns;
- Adicionar/estudar testes unitários;
- Estudar um pouco de SpringData/JPA;
- Revisar como o RestTemplate do Spring funciona, e como o Jackson se integra com ele com com o Spring MVC; */
