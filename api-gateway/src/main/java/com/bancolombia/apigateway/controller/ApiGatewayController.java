package com.bancolombia.apigateway.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bancolombia.apigateway.client.model.AccountResponseArray;
import com.bancolombia.apigateway.client.model.Creditcard;
import com.bancolombia.apigateway.domain.Producto;
import com.bancolombia.apigateway.service.ProductsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/products")
@Api(value = "products", description = "Operations pertaining to products ")
public class ApiGatewayController {

	private static final String OK = "OK";
	private static final String ERROR = "ERROR";

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

	@Autowired
	ProductsService productsService;

	@ApiOperation(value = "Search  products by user id", response = Creditcard.class)
	@ApiParam(name = "owner_id", value = "owner id CC_111111", required = true)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved products"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/{owner_id}", method = RequestMethod.GET, produces = "application/vnd.api+json")
	public ResponseEntity<List<Producto>> getProductsByUserid(@PathVariable String owner_id) {

		ResponseEntity<List<Producto>> responseEntity;

		try {

			String[] data = owner_id.split("_");

			String type = data[0];
			String number = data[1];

			AccountResponseArray cuentas = productsService.getAccounts(type, number);

			Object tcs = productsService.getCreditcard(type, number);

			List<Producto> list = new ArrayList<Producto>();

			Producto pCuentas = new Producto();
			pCuentas.setType("Account");

			if (cuentas != null) {

				pCuentas.setStatus(OK);
				pCuentas.setData(cuentas.getData());
				list.add(pCuentas);
			} else {
				pCuentas.setStatus(ERROR);
				pCuentas.setData(null);
				list.add(pCuentas);
			}

			Producto pTarjetas = new Producto();
			pTarjetas.setType("Creditcard");

			if (tcs != null) {

				pTarjetas.setStatus(OK);
				pTarjetas.setData(tcs);
				list.add(pTarjetas);
			} else {
				pTarjetas.setStatus(ERROR);
				pTarjetas.setData(null);
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
