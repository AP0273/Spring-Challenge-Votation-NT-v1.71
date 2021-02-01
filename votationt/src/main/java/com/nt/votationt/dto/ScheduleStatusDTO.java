package com.nt.votationt.dto;

import java.time.LocalDateTime;

public class ScheduleStatusDTO {

	LocalDateTime startDate;
	LocalDateTime endDate;
	String votationStatus;
	Long positiveVotes;
	Long negativeVotes;
	String approvalPercent;

	public ScheduleStatusDTO(LocalDateTime startDate, LocalDateTime endDate, String votationStatus,
			Long positiveVotes, Long negativeVotes, String approvalPercent) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.votationStatus = votationStatus;
		this.positiveVotes = positiveVotes;
		this.negativeVotes = negativeVotes;
		this.approvalPercent = approvalPercent;
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

	public String getVotationStatus() {
		return votationStatus;
	}

	public void setVotationStatus(String votationStatus) {
		this.votationStatus = votationStatus;
	}

	public Long getPositiveVotes() {
		return positiveVotes;
	}

	public void setPositiveVotes(Long positiveVotes) {
		this.positiveVotes = positiveVotes;
	}

	public Long getNegativeVotes() {
		return negativeVotes;
	}

	public void setNegativeVotes(Long negativeVotes) {
		this.negativeVotes = negativeVotes;
	}

	public String getApprovalPercent() {
		return approvalPercent;
	}

	public void setApprovalPercent(String approvalPercent) {
		this.approvalPercent = approvalPercent;
	}

}
