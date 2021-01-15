package com.nt.votationt.forms;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.annotations.ApiModelProperty;

public class PersonFormUpdate {

	private String cpf;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String nowpassword; // (WIP)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String newpassword; // (WIP)
	private String fullname;
	@ApiModelProperty(example = "+5540028922")
	private String phone;
	private String adress;
	private String email;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(dataType = "java.sql.Date", notes = "Birthday", example = "1999-01-13")
	private Date birthday;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNowpassword() {
		return nowpassword;
	}

	public void setNowpassword(String nowpassword) {
		this.nowpassword = nowpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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

}
