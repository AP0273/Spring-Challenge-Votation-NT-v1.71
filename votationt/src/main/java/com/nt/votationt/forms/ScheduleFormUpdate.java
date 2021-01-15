package com.nt.votationt.forms;

import java.time.LocalDateTime;

public class ScheduleFormUpdate {

	private Long idschedule;
	private String category;
	private String name;
	private String description;
	LocalDateTime start_date;
	LocalDateTime end_date;
	private String cpfProponent;
	private String password;

	public Long getIdschedule() {
		return idschedule;
	}

	public void setIdschedule(Long idschedule) {
		this.idschedule = idschedule;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDateTime start_date) {
		this.start_date = start_date;
	}

	public LocalDateTime getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDateTime end_date) {
		this.end_date = end_date;
	}

	public String getCpfProponent() {
		return cpfProponent;
	}

	public void setCpfProponent(String cpfProponent) {
		this.cpfProponent = cpfProponent;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
