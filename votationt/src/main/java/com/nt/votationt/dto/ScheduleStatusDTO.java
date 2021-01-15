package com.nt.votationt.dto;

import java.time.LocalDateTime;

public class ScheduleStatusDTO {

	LocalDateTime start_date;
	LocalDateTime end_date;
	String votation_status;
	Long positive_votes;
	Long negative_votes;
	String approval_percent;
	
	public ScheduleStatusDTO(LocalDateTime start_date,LocalDateTime end_date,String votation_status, Long positive_votes, Long negative_votes, String approval_percent) {
		super();
		this.start_date = start_date;
		this.end_date = end_date;
		this.votation_status = votation_status;
		this.positive_votes = positive_votes;
		this.negative_votes = negative_votes;
		this.approval_percent = approval_percent;
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
	public String getVotation_status() {
		return votation_status;
	}
	public void setVotation_status(String votation_status) {
		this.votation_status = votation_status;
	}
	public Long getPositive_votes() {
		return positive_votes;
	}
	public void setPositive_votes(Long positive_votes) {
		this.positive_votes = positive_votes;
	}
	public Long getNegative_votes() {
		return negative_votes;
	}
	public void setNegative_votes(Long negative_votes) {
		this.negative_votes = negative_votes;
	}
	public String getApproval_percent() {
		return approval_percent;
	}
	public void setApproval_percent(String approval_percent) {
		this.approval_percent = approval_percent;
	}
	 
}
