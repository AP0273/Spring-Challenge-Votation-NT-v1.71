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

import com.nt.votationt.model.Schedule;
import com.nt.votationt.service.ScheduleService;

@RestController
@RequestMapping(value="Schedule")
public class ScheduleController {

	@Autowired
	private ScheduleService service;
	
	public ScheduleController (ScheduleService service) {
		this.service = service;
	}
	@PostMapping
	public Object create(@RequestBody Schedule schedule) {
		return service.insertSchedule(schedule);
	}
	@PutMapping
	public Object update(@RequestBody Schedule schedule) {
		return service.insertSchedule(schedule);
	}
	@GetMapping("/getbyId/{id}")
	public Schedule getById(@PathVariable Long id) {
		return service.FindSchedule(id);
	}
	@GetMapping("/getbyName/{Name}")
	public Schedule getById(@PathVariable String Name) {
		return service.findByNameIgnoreCase(Name);
	}
	@GetMapping("/getbyCategory/{Category}")
	public Schedule getByCategory(@PathVariable String Category) {
		return service.findByCategoryIgnoreCase(Category);
	}
	@GetMapping("/getVotationState/{id_schedule}")
	public String getVotationState(@PathVariable Long id_schedule) {
		return service.CheckScheduleState(id_schedule);
	}
	@GetMapping
	public List<Schedule> getAll() {
		return service.getAllSchedule();
	}
	@GetMapping("getNumberOfVotes_Schedule/{id}/{aprovation}")
	public String getNumberOfVotes_Schedule(@PathVariable Long id,boolean aprovation) {
		return service.GetNumberOfVotes_Schedule(id, aprovation);
	}
	@DeleteMapping("/deletebyid/{id}")
	public String deleteById(@PathVariable Long id) {
		return service.DeleteSchedule(id);
	}
	
	@PatchMapping
	public Object patchUpdate(@RequestBody Schedule schedule) {
		return service.insertSchedule(schedule);
	}
	
}
