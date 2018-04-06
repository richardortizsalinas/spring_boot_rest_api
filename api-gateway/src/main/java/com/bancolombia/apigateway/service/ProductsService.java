package com.bancolombia.apigateway.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bancolombia.apigateway.client.TransactionApi;
import com.bancolombia.apigateway.client.model.AccountResponseArray;
import com.bancolombia.apigateway.domain.Producto;

@Service
public class ProductsService {

	private static final Logger LOG = LoggerFactory.getLogger(ProductsService.class);

	@Value("${service.deposits.endpoint}")
	String depositsServiceEndpoint;

	@Value("${service.creditcard.endpoint}")
	String creditcardServiceEndpoint;

	public ResponseEntity<List<Producto>> getProductsByUserId(String owner_id) {

		ResponseEntity<List<Producto>> responseEntity;

		try {

			String[] data = owner_id.split("_");

			String type = data[0];
			String number = data[1];

			TransactionApi cliente = new TransactionApi(depositsServiceEndpoint);

			TransactionApi tarjetas = new TransactionApi(creditcardServiceEndpoint);

			AccountResponseArray cuentas = cliente.getAccounts(type, number);

			Object tcs = tarjetas.getCreditcardsByOwner(type, number);

			List<Producto> list = new ArrayList<Producto>();

			if (cuentas != null) {

				Producto pCuentas = new Producto();
				pCuentas.setType("Account");
				pCuentas.setData(cuentas.getData());
				list.add(pCuentas);
			}

			if (cuentas != null) {

				Producto pTarjetas = new Producto();
				pTarjetas.setType("Creditcard");
				pTarjetas.setData(tcs);
				list.add(pTarjetas);
			}

			if (list == null || list.isEmpty()) {
				responseEntity = new ResponseEntity<List<Producto>>(list, HttpStatus.NOT_FOUND);

			} else {
				responseEntity = new ResponseEntity<List<Producto>>(list, HttpStatus.OK);
			}

			return responseEntity;

		} catch (Exception e) {
			return responseEntity = new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}