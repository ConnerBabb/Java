package com.techelevator.tenmo.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.Account;

public class AccountService {

	private String jwt;
	private String baseUrl;
	private RestTemplate restTemplate = new RestTemplate();
	
	public AccountService(String jwt, String baseUrl) {
		this.jwt = jwt;
		this.baseUrl = baseUrl;
	}
	
	public Account getAccount() {

		String url = baseUrl + "accounts";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth( jwt );
		HttpEntity request = new HttpEntity<>(headers);
		
		Account account = null;
		try {
			account = restTemplate.exchange(url, HttpMethod.GET, request, Account.class).getBody();
		} catch (RestClientResponseException ex) {
			// Do something with this exception
			throw ex;
		}
		
		return account;
		
	}
	
	
}
