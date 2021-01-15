package com.nt.votationt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.nt.votationt.forms.PersonFormUpdate;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Person {

	public Person() {

	}
	
	public Person(PersonFormUpdate form) {
		this.cpf = form.getCpf();
        this.password = form.getNewpassword();
        this.fullname = form.getFullname();
		this.phone = form.getPhone();
		this.adress = form.getAdress();
		this.email = form.getEmail();
		this.birthday = form.getBirthday();
	}

	@Id
	@Column(length = 11)
	private String cpf;
	@Column(length = 100)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password; // (WIP)
	@Column(length = 80)
	private String fullname;
	@ApiModelProperty(example = "+5540028922")
	@Column(length = 25)
	private String phone;
	@Column(length = 200)
	private String adress;
	@Column(length = 320)
	private String email;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(dataType = "java.sql.Date", notes = "Birthday", example = "1999-01-13")
	private Date birthday;
	@JsonIgnore
	private boolean canVote;
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
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
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public boolean isCanVote() {
		return canVote;
	}

	public void setCanVote(boolean canVote) {
		this.canVote = canVote;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

}
