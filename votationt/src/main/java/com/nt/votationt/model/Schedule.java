package com.nt.votationt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedule")
public class Schedule {

	public Schedule() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_schedule")
	@ApiModelProperty(hidden = true)
	private Long id;
	private String category;
	private String name;
	private String description;
	LocalDateTime Start_date;
	LocalDateTime End_date;
	@JsonIgnore
	private Long n_votes_p = 0L;
	@JsonIgnore
	private Long n_votes_n = 0L;
//Auth
	private Long cpf_proponent;
	@Transient
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getCpf_proponent() {
		return cpf_proponent;
	}

	public void setCpf_proponent(Long cpf_proponent) {
		this.cpf_proponent = cpf_proponent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public LocalDateTime getStart_date() {
		return Start_date;
	}

	public void setStart_date(LocalDateTime start_date) {
		Start_date = start_date;
	}

	public LocalDateTime getEnd_date() {
		return End_date;
	}

	public void setEnd_date(LocalDateTime end_date) {
		End_date = end_date;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
}
