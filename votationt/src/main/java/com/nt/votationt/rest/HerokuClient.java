package com.nt.votationt.rest;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
@Component
public class HerokuClient {
    	
	@Value("${heroku.url}")
	private String herokuUrl;
	
	public String getCpfState(Long Cpf) {
		System.out.println();
		final RestTemplate template = new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(5000)).build();
		String result;
		
		try {
			result = template.getForObject(herokuUrl + Long.toString(Cpf), String.class);

		} catch (HttpClientErrorException.NotFound e) {
			return "Invalid";
		}
		return result;

	}

}
