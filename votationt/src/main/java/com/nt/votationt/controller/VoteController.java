package com.nt.votationt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.votationt.model.Vote;
import com.nt.votationt.service.VoteService;

@RestController
@RequestMapping(value = "Vote")
public class VoteController {

	private VoteService service;

	public VoteController(VoteService service) {
		this.service = service;
	}

	@PostMapping
	public Object create(@RequestBody Vote vote) {
		return service.insertVote(vote);
	}

	@PutMapping
	public Object update(@RequestBody Vote vote) {
		return service.insertVote(vote);
	}

	@GetMapping("/getbyId/{id}")
	public Object getById(@PathVariable Long id) {
		return service.FindVote(id);
	}

	@GetMapping("/getbyCpfPerson/{CpfPerson}")
	public Object getByCpfPerson(@PathVariable Long CpfPerson) {
		return service.FindVote(CpfPerson);
	}

	@GetMapping
	public List<Vote> getAll() {
		return service.getAllVote();
	}

	@DeleteMapping("/deletebyid/{id}")
	public String deleteById(@PathVariable Long id) {
		return service.DeleteVote(id);
	}

	@PatchMapping
	public Object patchUpdate(@RequestBody Vote vote) {
		return service.insertVote(vote);
	}
}
