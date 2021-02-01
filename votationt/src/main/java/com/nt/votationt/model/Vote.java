package com.nt.votationt.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import com.nt.votationt.forms.VoteFormInsert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Vote {

	public Vote() {

	}

	public Vote(VoteFormInsert form) {
		this.idschedule = form.getIdschedule();
		this.aprovation = form.isAprovation();
		this.cpfPerson = form.getCpfPerson();

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long idschedule;
	private boolean aprovation;
	private String cpfPerson;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isAprovation() {
		return aprovation;
	}

	public void setAprovation(boolean aprovation) {
		this.aprovation = aprovation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Vote other = (Vote) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getCpfPerson() {
		return cpfPerson;
	}

	public void setCpfPerson(String cpfPerson) {
		this.cpfPerson = cpfPerson;
	}

	public Long getIdschedule() {
		return idschedule;
	}

	public void setIdschedule(Long idSchedule) {
		this.idschedule = idSchedule;
	}

	@Override
	public String toString() {
		return "Vote [id=" + id + ", idschedule=" + idschedule + ", aprovation=" + aprovation + ", cpfPerson="
				+ cpfPerson + "]";
	}

}
