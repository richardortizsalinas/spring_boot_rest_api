package com.bancolombia.apigateway.service;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bancolombia.apigateway.client.TransactionApi;
import com.bancolombia.apigateway.client.model.AccountResponseArray;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;

@Service
public class ProductsService {

	private static final Logger LOG = LoggerFactory.getLogger(ProductsService.class);

	@Value("${service.deposits.endpoint}")
	String depositsServiceEndpoint;

	@Value("${service.creditcard.endpoint}")
	String creditcardServiceEndpoint;

	@Autowired
	TransactionApi transactionApi;

	@HystrixCommand(fallbackMethod = "getAccountsDefault")
	public Future<AccountResponseArray> getAccounts(String type, String number) {

		return new AsyncResult<AccountResponseArray>() {

			@Override
			public AccountResponseArray invoke() {
				return transactionApi.getAccounts(type, number, depositsServiceEndpoint);
			}
		};

	}

	public AccountResponseArray getAccountsDefault(String type, String number) {
		return null;
	}

	@HystrixCommand(fallbackMethod = "getCreditcardDefault")
	public Future<Object> getCreditcard(String type, String number) {

		return new AsyncResult<Object>() {

			@Override
			public Object invoke() {
				Object cc = transactionApi.getCreditcardsByOwner(type, number, creditcardServiceEndpoint);
				return cc;
			}
		};

	}

	public Object getCreditcardDefault(String type, String number) {
		return null;
	}

}