
package com.bancolombia.accounts.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.bancolombia.accounts.client.model.TransactionResponseSingle;
import com.bancolombia.creditcard.service.CreditcardService;

public class TransactionApi {

	String url;
	static final String PATH_TRANSACTION = "/api/transactions";
	
	private static final Logger LOG = LoggerFactory.getLogger(TransactionApi.class);

	RestTemplate restTemplate = new RestTemplate();

	public TransactionApi(String url) {
		this.url = url;
	}

	public TransactionResponseSingle create(TransactionResponseSingle requestBody) throws Exception {

		try {
			// String json = new ObjectMapper().writeValueAsString(requestBody);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.valueOf("application/vnd.api+json"));

			HttpEntity<TransactionResponseSingle> entity = new HttpEntity<TransactionResponseSingle>(requestBody,
					headers);

			ResponseEntity<TransactionResponseSingle> result = restTemplate.postForEntity(url + PATH_TRANSACTION,
					entity, TransactionResponseSingle.class);

			if (result.getStatusCode() == HttpStatus.OK) {
				TransactionResponseSingle transaction = result.getBody();
				return transaction;
			} else {
				return null;
			}
		} catch (Exception e) {
			LOG.error("ERROR ",e);
			return null;
		}

	}

}
