package com.bancolombia.apigateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
		return productsService.getProductsByUserId(owner_id);
	}


}
