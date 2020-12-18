package com.nt.votationt.util;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.nt.votationt.util.Config;

public class RestTemplateGet {
    	
	@Value("${heroku.url}")
	private static String heroku_url;
	
	public static String GetCpfState(Long Cpf) {
		System.out.println();
		final RestTemplate template = new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(5000)).build();
		String result;
		
		try {
			result = template.getForObject(heroku_url + Long.toString(Cpf), String.class);

		} catch (HttpClientErrorException.NotFound e) {
			return "Invalid";
		}
		return result;

	}

}
