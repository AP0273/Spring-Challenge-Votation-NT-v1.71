package com.nt.votationt.forms;

public class VoteFormInsert {

	private Long idschedule;
	private boolean aprovation;
	private String cpfPerson;
	private String password;

	public VoteFormInsert() {
	}

	public boolean isAprovation() {
		return aprovation;
	}

	public void setAprovation(boolean aprovation) {
		this.aprovation = aprovation;
	}

	public String getCpfPerson() {
		return cpfPerson;
	}

	public void setCpfPerson(String cpfPerson) {
		this.cpfPerson = cpfPerson;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getIdschedule() {
		return idschedule;
	}

	public void setIdschedule(Long idschedule) {
		this.idschedule = idschedule;
	}
}
