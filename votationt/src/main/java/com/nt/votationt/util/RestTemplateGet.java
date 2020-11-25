package com.nt.votationt.util;

import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class RestTemplateGet {

	public static String GetCpfState(Long Cpf) {

		final RestTemplate template = new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(5000)).build();

		String result;
		try {
			result = template.getForObject("https://user-info.herokuapp.com/users/" + Long.toString(Cpf), String.class);

		} catch (HttpClientErrorException.NotFound e) {
			return "Invalid";
		}
		return result;

	}

}
