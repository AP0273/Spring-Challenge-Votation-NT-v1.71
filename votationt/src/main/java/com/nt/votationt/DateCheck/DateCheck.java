package com.nt.votationt.DateCheck;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.nt.votationt.model.Schedule;

@Component
public class DateCheck {

	public boolean isUnstarted(Schedule schedule) {
		boolean result = false;
		if (LocalDateTime.now().isBefore(schedule.getStartDate()))
			result = true;
		return result;
	}

	public boolean isOnGoing(Schedule schedule) {
		boolean result = false;
		if (LocalDateTime.now().isAfter(schedule.getStartDate())
				&& LocalDateTime.now().isBefore(schedule.getEndDate()))
			result = true;
		return result;
	}

	public boolean isEnded(Schedule schedule) {
		boolean result = false;
		if (LocalDateTime.now().isAfter(schedule.getEndDate()))
			result = true;
		return result;
	}

	public String checkStatus(Schedule schedule) {
		String result = "Unstarted";
		if (isOnGoing(schedule))
			result = "Ongoing";
		if (isEnded(schedule))
			result = "Ended";
		return result;
	}

}
