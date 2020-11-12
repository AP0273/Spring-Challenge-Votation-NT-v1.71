package com.nt.votationt.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TimeZone;

public class HttpGet {
	
	
	public static String GetCpfState (Long Cpf) throws IOException {
		String Base = "https://user-info.herokuapp.com/users/";
		URL url = new URL(Base+Long.toString(Cpf));
		System.out.println(Base+Long.toString(Cpf));
		try {
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setRequestMethod("GET"); // PUT is another valid option
		http.setDoOutput(true);
		http.setConnectTimeout(5000);
		BufferedReader in = new BufferedReader(
				  new InputStreamReader(http.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
				    content.append(inputLine);

				}
				return content.toString();     //  Outputs {"status":"ABLE_TO_VOTE"} or {"status":"UNABLE_TO_VOTE"}
		}catch (java.io.FileNotFoundException e) {
			return "Invalid";
		}
	


	}
	
}
