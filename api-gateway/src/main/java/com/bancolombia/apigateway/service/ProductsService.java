package com.bancolombia.apigateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancolombia.apigateway.client.TransactionApi;
import com.bancolombia.apigateway.client.model.AccountResponseArray;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ProductsService {

	private static final Logger LOG = LoggerFactory.getLogger(ProductsService.class);

	// @Value("${service.deposits.endpoint}")
	// String depositsServiceEndpoint;
	//
	// @Value("${service.creditcard.endpoint}")
	// String creditcardServiceEndpoint;
	@Autowired
	TransactionApi transactionApi;

	String depositsServiceEndpoint = "http://deposits-service-poc-msa-aee.sbmdegos01v.ambientesbc.lab";

	String creditcardServiceEndpoint = "http://creditcard-service-poc-msa-aee.sbmdegos01v.ambientesbc.lab";

	@HystrixCommand(fallbackMethod = "getAccountsDefault")
	public AccountResponseArray getAccounts(String type, String number) {

		AccountResponseArray cuentas = transactionApi.getAccounts(type, number, depositsServiceEndpoint);
		return cuentas;

	}

	public AccountResponseArray getAccountsDefault(String type, String number) {
		return null;
	}

	@HystrixCommand(fallbackMethod = "getCreditcardDefault")
	public Object getCreditcard(String type, String number) {

		Object cc = transactionApi.getCreditcardsByOwner(type, number, creditcardServiceEndpoint);
		return cc;

	}

	public Object getCreditcardDefault(String type, String number) {
		return null;
	}

}