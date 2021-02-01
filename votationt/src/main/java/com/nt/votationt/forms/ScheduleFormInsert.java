package com.nt.votationt.forms;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScheduleFormInsert {

	private String category;
	private String name;
	private String description;
	@JsonProperty(required = false)
	private LocalDateTime startDate;
	@JsonProperty(required = false)
	private LocalDateTime endDate;
	private String cpfProponent;
	private String password;

	public ScheduleFormInsert() {

	}

	public ScheduleFormInsert(ScheduleFormUpdate formu) {
		this.category = formu.getCategory();
		this.name = formu.getName();
		this.description = formu.getDescription();
		this.startDate = formu.getStartDate();
		this.endDate = formu.getEndDate();
		this.cpfProponent = formu.getCpfProponent();
		this.password = formu.getPassword();
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
