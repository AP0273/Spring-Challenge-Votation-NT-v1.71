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
		this.startDate = form.getStartDate();
		this.endDate = form.getEndDate();
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
	LocalDateTime startDate;
	LocalDateTime endDate;
	@JsonIgnore
	private Long nVotesP = 0L;
	@JsonIgnore
	private Long nVotesN = 0L;
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

	public Long getnVotesP() {
		return nVotesP;
	}

	public void setnVotesP(Long nVotesP) {
		this.nVotesP = nVotesP;
	}

	public Long getnVotesN() {
		return nVotesN;
	}

	public void setnVotesN(Long nVotesN) {
		this.nVotesN = nVotesN;
	}

	@Override
	public String toString() {
		return "Schedule [idschedule=" + idschedule + ", category=" + category + ", name=" + name + ", description="
				+ description + ", startDate=" + startDate + ", endDate=" + endDate + ", nVotesP=" + nVotesP
				+ ", nVotesN=" + nVotesN + ", cpfProponent=" + cpfProponent + "]";
	}

}
