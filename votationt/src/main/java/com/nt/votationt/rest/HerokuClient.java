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

	public HerokuAnswer getCpfState(String Cpf) {
		System.out.println();
		final RestTemplate template = new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(5000)).build();
		HerokuAnswer result = new HerokuAnswer();
		try {
			result = template.getForObject(herokuUrl + Cpf, HerokuAnswer.class);
		} catch (HttpClientErrorException.NotFound e) {
			System.out.println("catch");
			result.setStatus("INVALID_CPF");
			return result;
		}
		return result;
	}
}
