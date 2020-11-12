package com.nt.votationt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



@Table(name = "vote")
@Entity
public class Vote {

	public Vote() {
		
	}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
    private Long id;
    @Column(name = "id_schedule_voted")
    private Long id_schedule;
    private boolean aprovation;
    //Authentication
    private Long cpfPerson;
    @Transient
    private String password;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCpf_person() {
		return cpfPerson;
	}
	public void setCpf_person(Long cpfPerson) {
		this.cpfPerson = cpfPerson;
	}
	public Long getId_schedule() {
		return id_schedule;
	}
	public void setId_schedule(Long id_schedule) {
		this.id_schedule = id_schedule;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
