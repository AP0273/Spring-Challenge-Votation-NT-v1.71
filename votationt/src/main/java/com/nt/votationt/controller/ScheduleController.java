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
		return service.insertSchedule(form, null);
	}

	@PutMapping
	public Schedule update(@RequestBody ScheduleFormUpdate form) {
		return service.updateSchedule(form);
	}

	@GetMapping("/getbyId/{id}")
	public Schedule getById(@PathVariable Long id) {
		return service.findSchedule(id);
	}

	@GetMapping("/getbyName/{name}")
	public List<Schedule> getById(@PathVariable String name) {
		return service.findByNameIgnoreCase(name);
	}

	@GetMapping("/getbyCategory/{category}")
	public List<Schedule> getByCategory(@PathVariable String category) {
		return service.findByCategoryIgnoreCase(category);
	}

	@GetMapping("/getVotationState/{idSchedule}")
	public ScheduleStatusDTO getVotationState(@PathVariable Long idSchedule) {
		return service.checkScheduleState(idSchedule);
	}

	@GetMapping
	public List<Schedule> getAll() {
		return service.getAllSchedule();
	}

	@DeleteMapping("/deletebyid/{idSchedule}")
	public void deleteById(@RequestBody DeletionForm form, @PathVariable Long idSchedule) {
		service.deleteSchedule(form, idSchedule);
	}

	@PatchMapping
	public Schedule patchUpdate(@RequestBody ScheduleFormUpdate schedule) {
		return service.updateSchedule(schedule);
	}

}
