package com.bancolombia.accounts.client;

import java.math.BigDecimal;

import com.bancolombia.accounts.client.model.Transaction;
import com.bancolombia.accounts.client.model.TransactionResponseSingle;
import com.bancolombia.accounts.client.model.TransactionSingle;
import com.fasterxml.jackson.core.JsonProcessingException;

public class TestApi {

	public static void main(String[] args) throws Exception {
		TransactionApi cliente= new TransactionApi("http://deposits-service-poc-msa-aee.sbmdegos01v.ambientesbc.lab");
		TransactionResponseSingle requestBody = new TransactionResponseSingle();
		TransactionSingle data= new TransactionSingle();
		data.setId("");
		data.setType(TransactionSingle.TypeEnum.Transactions);
		
		Transaction attributes= new Transaction();
		attributes.setId("");
		attributes.setAmount(new BigDecimal("100000"));
		attributes.setOrigin("1002000001");
		attributes.setDestination("1001234001");	
		data.setAttributes(attributes);
		requestBody.setData(data);
		
		System.out.println(cliente.create(requestBody));
		
	}

}
