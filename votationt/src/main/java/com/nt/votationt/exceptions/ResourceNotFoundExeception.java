package com.nt.votationt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundExeception extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8164565635203603747L;

	public ResourceNotFoundExeception(String message) {
		super(message);
	}
	
}
