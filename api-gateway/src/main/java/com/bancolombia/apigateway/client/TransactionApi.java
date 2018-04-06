
package com.bancolombia.apigateway.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.bancolombia.apigateway.client.model.AccountResponseArray;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransactionApi {

	String url;
	static final String PATH_TRANSACTION = "/api/transactions";
	static final String PATH_CLIENTS = "/api/clients";
	static final String PATH_ACCOUNTS = "/accounts";

	static final String PATH_CREDITCARD_OWNER = "/creditcard/owner";

	private static final Logger LOG = LoggerFactory.getLogger(TransactionApi.class);

	RestTemplate restTemplate = new RestTemplate();

	public TransactionApi(String url) {
		this.url = url;
	}
	
	public AccountResponseArray getAccounts(String tipoDOc, String numDoc) throws Exception {

		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.valueOf("application/vnd.api+json"));

			String pathUser = "/" + tipoDOc + numDoc;
			ResponseEntity<AccountResponseArray> result = restTemplate
					.getForEntity(url + PATH_CLIENTS + pathUser + PATH_ACCOUNTS, AccountResponseArray.class);

			if (result.getStatusCode() == HttpStatus.OK) {
				AccountResponseArray transaction = result.getBody();

				String json = new ObjectMapper().writeValueAsString(transaction);
				return transaction;
			} else {
				return null;
			}
		} catch (Exception e) {
			LOG.error("ERROR ", e);
			return null;
		}

	}

	public Object getCreditcardsByOwner(String tipoDOc, String numDoc) throws Exception {

		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.valueOf("application/vnd.api+json"));

			String pathUser = "/" + tipoDOc + "_" + numDoc;
			ResponseEntity<Object> result = restTemplate.getForEntity(url + PATH_CREDITCARD_OWNER + pathUser,
					Object.class);

			if (result.getStatusCode() == HttpStatus.OK) {

				return result.getBody();
			} else {
				return null;
			}
		} catch (Exception e) {
			LOG.error("ERROR ", e);
			return null;
		}

	}

}
