package com.nt.votationt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nt.votationt.forms.ScheduleFormInsert;
import java.time.LocalDateTime;

@Entity
public class Schedule {

	public Schedule() {

	}
	public Schedule(ScheduleFormInsert form) {
	this.category = form.getCategory();
	this.name = form.getName();
	this.description = form.getDescription();
	this.start_date = form.getStart_date();
	this.end_date = form.getEnd_date();
	this.cpfProponent = form.getCpfProponent();

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idschedule;
	@Column(length = 40)
	private String category;
	@Column(length = 80)
	private String name;
	@Column(length = 1000)
	private String description;
	LocalDateTime start_date;
	LocalDateTime end_date;
	@JsonIgnore
	private Long n_votes_p = 0L;
	@JsonIgnore
	private Long n_votes_n = 0L;
//Auth
	private String cpfProponent;


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
	public Long getN_votes_p() {
		return n_votes_p;
	}

	public void setN_votes_p(Long n_votes_p) {
		this.n_votes_p = n_votes_p;
	}

	public Long getN_votes_n() {
		return n_votes_n;
	}

	public void setN_votes_n(Long n_votes_n) {
		this.n_votes_n = n_votes_n;
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

	public Long getIdschedule() {
		return idschedule;
	}

	public void setIdschedule(Long idschedule) {
		this.idschedule = idschedule;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idschedule == null) ? 0 : idschedule.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		if (idschedule == null) {
			if (other.idschedule != null)
				return false;
		} else if (!idschedule.equals(other.idschedule))
			return false;
		return true;
	}

	
}
