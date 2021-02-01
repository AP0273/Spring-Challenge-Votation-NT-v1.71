package com.nt.votationt.forms;

import java.time.LocalDateTime;

public class ScheduleFormUpdate {

	private Long idschedule;
	private String category;
	private String name;
	private String description;
	LocalDateTime startDate;
	LocalDateTime endDate;
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

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

}
