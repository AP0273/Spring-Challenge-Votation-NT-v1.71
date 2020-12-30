package com.nt.votationt.DateCheck;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.nt.votationt.model.Schedule;

@Component
public class DateCheck {

public boolean isUnstarted(Schedule schedule) {
	boolean result=false;
	if(LocalDateTime.now().isBefore(schedule.getStart_date())) result=true;
	return result;
}
public boolean isOnGoing(Schedule schedule) {
	boolean result=false;
	if(LocalDateTime.now().isAfter(schedule.getStart_date()) && LocalDateTime.now().isBefore(schedule.getEnd_date())) result=true;
	return result;
}
public boolean isEnded(Schedule schedule) {
	boolean result=false;
	if(LocalDateTime.now().isAfter(schedule.getEnd_date())) result=true;
	return result;
}
public String checkStatus(Schedule schedule) {
	String result = "Unstarted";
	if(isOnGoing(schedule)) result = "Ongoing";
	if(isEnded(schedule)) result = "Ended";
	return result;
}

}
