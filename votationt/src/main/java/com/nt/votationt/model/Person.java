package com.nt.votationt.model;

import javax.persistence.Entity;
import java.sql.Date;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Person {


	
	public Person() {
		
	}
	@Id
	private Long cpf;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password; // (WIP)
	private String fullName;
	private Long phone;
	private String adress;
	@Email(message = "Email must be valid.")
	private String email;
	@JsonFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(dataType = "java.sql.Date",notes = "Birthday", example= "1999-01-13")
	private Date birthday;
	@JsonIgnore
	private boolean canVote;
	

	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getfullName() {
		return fullName;
	}
	public void setfullName(String fullName) {
		this.fullName = fullName;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean iscanVote() {
		return canVote;
	}
	public void setcanVote(boolean canVote) {
		this.canVote = canVote;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	
}
