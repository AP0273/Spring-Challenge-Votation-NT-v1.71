package com.nt.votationt.input_validations;

import org.springframework.stereotype.Component;

@Component
public class Verify {

	public boolean verifyPhone (String phone) {
		String regexinternationalphone  = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
		boolean result = false;
	    if(phone.matches(regexinternationalphone))result = true;
	    return result;
	}
	public boolean verifyEmail (String phone) {
		String regexemail  = "^([\\p{javaUnicodeIdentifierPart}-_\\.]+){1,64}@([\\p{L}-_\\.]+){2,255}([.])[a-z]{2,}$";
		boolean result = false;
	    if(phone.matches(regexemail))result = true;
	    return result;
	}
	public boolean verifyPassword (String phone) {
		String regexinternationalphone  = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,100}$";
		boolean result = false;
	    if(phone.matches(regexinternationalphone))result = true;
	    return result;
	}
}
