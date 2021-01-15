package com.nt.votationt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.votationt.forms.DeletionForm;
import com.nt.votationt.forms.VoteFormInsert;
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
	public Vote create(@RequestBody VoteFormInsert form) {
		return service.insertVote(form);
	}

	@GetMapping("/getbyId/{id}")
	public Vote getById(@PathVariable Long id) {
		return service.findVote(id);
	}

	@GetMapping("/getbyCpfPerson/{CpfPerson}")
	public List<Vote> getByCpfPerson(@PathVariable String CpfPerson) {
		return service.findByCpfPerson(CpfPerson);
	}

	@GetMapping
	public List<Vote> getAll() {
		return service.getAllVote();
	}

	@DeleteMapping("/deletebyid/{id}")
	public void deleteById(@RequestBody DeletionForm form, @PathVariable Long id) {
		service.deleteVote(form, id);
	}

}
