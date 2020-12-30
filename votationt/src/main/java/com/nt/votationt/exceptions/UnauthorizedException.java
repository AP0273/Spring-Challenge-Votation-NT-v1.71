package com.nt.votationt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

/**
	 * 
	 */
	private static final long serialVersionUID = 1997912306083705313L;

public UnauthorizedException(String message) {
	super(message);
}
}
