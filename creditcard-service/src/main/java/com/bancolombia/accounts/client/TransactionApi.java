
package com.bancolombia.accounts.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.bancolombia.accounts.client.model.AccountResponseArray;
import com.bancolombia.accounts.client.model.TransactionResponseSingle;

public class TransactionApi {

	String url;
	static final String PATH_TRANSACTION = "/api/transactions";
	static final String PATH_CLIENTS = "/api/clients";
	static final String PATH_ACCOUNTS = "/accounts";
	
	static final String PATH_CREDITCARD_OWNER = "/creditcard/owner";
	
	private static final Logger LOG = LoggerFactory.getLogger(TransactionApi.class);
	private static final String PATH = "/";

	RestTemplate restTemplate = new RestTemplate();

	public TransactionApi(String url) {
		this.url = url;
	}

	public TransactionResponseSingle create(TransactionResponseSingle requestBody) {

		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.valueOf("application/vnd.api+json"));

			HttpEntity<TransactionResponseSingle> entity = new HttpEntity<>(requestBody,
					headers);

			ResponseEntity<TransactionResponseSingle> result = restTemplate.postForEntity(url + PATH_TRANSACTION,
					entity, TransactionResponseSingle.class);

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

	public AccountResponseArray getAccounts(String tipoDOc, String numDoc) {

		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.valueOf("application/vnd.api+json"));

			String pathUser=PATH+tipoDOc+numDoc;
			ResponseEntity<AccountResponseArray> result = restTemplate.getForEntity(url + PATH_CLIENTS + pathUser + PATH_ACCOUNTS,AccountResponseArray.class);

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
