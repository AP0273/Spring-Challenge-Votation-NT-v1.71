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

import com.nt.votationt.dto.ScheduleStatusDTO;
import com.nt.votationt.forms.DeletionForm;
import com.nt.votationt.forms.ScheduleFormInsert;
import com.nt.votationt.forms.ScheduleFormUpdate;
import com.nt.votationt.model.Schedule;
import com.nt.votationt.service.ScheduleService;

@RestController
@RequestMapping(value = "Schedule")
public class ScheduleController {

	@Autowired
	private ScheduleService service;

	public ScheduleController(ScheduleService service) {
		this.service = service;
	}

	@PostMapping
	public Schedule create(@RequestBody ScheduleFormInsert form) {
		return service.insertSchedule(form,null);
	}

	@PutMapping
	public Schedule update(@RequestBody ScheduleFormUpdate form) {
		return service.updateSchedule(form);
	}

	@GetMapping("/getbyId/{id}")
	public Schedule getById(@PathVariable Long id) {
		return service.findSchedule(id);
	}

	@GetMapping("/getbyName/{Name}")
	public List <Schedule> getById(@PathVariable String Name) {
		return service.findByNameIgnoreCase(Name);
	}

	@GetMapping("/getbyCategory/{Category}")
	public List <Schedule> getByCategory(@PathVariable String Category) {
		return service.findByCategoryIgnoreCase(Category);
	}

	@GetMapping("/getVotationState/{id_schedule}")
	public ScheduleStatusDTO getVotationState(@PathVariable Long id_schedule) {
		return service.checkScheduleState(id_schedule);
	}

	@GetMapping
	public List<Schedule> getAll() {
		return service.getAllSchedule();
	}
	@DeleteMapping("/deletebyid/{id}")
	public void deleteById(@RequestBody DeletionForm form,@PathVariable Long id) {
	 service.deleteSchedule(form,id);
	}

	@PatchMapping
	public Schedule patchUpdate(@RequestBody ScheduleFormUpdate schedule) {
		return service.updateSchedule(schedule);
	}

}
