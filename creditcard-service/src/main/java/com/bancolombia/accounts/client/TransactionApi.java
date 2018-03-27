
package com.bancolombia.accounts.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.bancolombia.accounts.client.model.TransactionResponseSingle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransactionApi {

	String url;
	static final String PATH_TRANSACTION = "/api/transactions";

	RestTemplate restTemplate = new RestTemplate();

	public TransactionApi(String url) {
		this.url = url;
	}

	public TransactionResponseSingle create(TransactionResponseSingle requestBody) throws JsonProcessingException {
		
		String json = new ObjectMapper().writeValueAsString(requestBody);

		 //  and simple print it

		    System.out.println(json);
		    
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
//		    ContentType(MediaType.valueOf("application/vnd.api+json"));

		    HttpEntity<TransactionResponseSingle> entity = new HttpEntity<TransactionResponseSingle>(requestBody ,headers);
		    
		    
//		    HttpHeaders headers = new HttpHeaders();
//		     headers.add("Accept", MediaType.APPLICATION_JSON);
		    
		ResponseEntity<TransactionResponseSingle> result = restTemplate.postForEntity(url + PATH_TRANSACTION,
				entity, TransactionResponseSingle.class);
		
		

		// Code = 200.
		if (result.getStatusCode() == HttpStatus.OK) {
			TransactionResponseSingle transaction = result.getBody();
			return transaction;
		}
		else {
			return null;
		}
	}

}
