package com.bancolombia.creditcard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.creditcard.domain.Creditcard;
import com.bancolombia.creditcard.domain.Movement;
import com.bancolombia.creditcard.domain.Payment;
import com.bancolombia.creditcard.service.CreditcardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/creditcard")
@Api(value = "creditcard", description = "Operations pertaining to creditcard ")
public class CreditcardController {

	
	
	@Autowired
	CreditcardService creditcardService;

	@ApiOperation(value = "View a list of available creditcards", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(method = RequestMethod.GET, produces = "application/vnd.api+json")
	public List<Creditcard> list() {
		
		return creditcardService.getAll();
	}

	@ApiOperation(value = "Search a Creditcard by number", response = Creditcard.class)
	@ApiParam(name = "number", value = "creditcard number", required = true)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved creditcard"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/{number}", method = RequestMethod.GET, produces = "application/vnd.api+json")
	public ResponseEntity<Creditcard> showCreditcard(@PathVariable String number) {

		return creditcardService.get(number);
	}

	@ApiOperation(value = "Get movements for a Creditcard by number", response = Creditcard.class)
	@ApiParam(name = "number", value = "creditcard number", required = true)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved movements"),
			@ApiResponse(code = 404, message = "No movements avalilable") })
	@RequestMapping(value = "/{number}/movements", method = RequestMethod.GET, produces = "application/vnd.api+json")
	public ResponseEntity<List<Movement>> showCreditcardMovements(@PathVariable String number) {

		return creditcardService.getMovements(number);
	}

	@ApiOperation(value = "Add a Creditcard")
	@ApiParam(name = "creditcard", value = "creditcard data", required = true)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created creditcard") })
	@RequestMapping(method = RequestMethod.POST, produces = "application/vnd.api+json")
	public ResponseEntity<String> saveCreditcard(@RequestBody(required = true) Creditcard creditcard) {
		
		return creditcardService.save(creditcard);
	}

	@ApiOperation(value = "Update a Creditcard")
	@ApiParam(name = "number", value = "creditcard number", required = true)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated creditcard") })
	@RequestMapping(value = "/{number}", method = RequestMethod.PUT, produces = "application/vnd.api+json")
	public ResponseEntity<String> updateCreditcard(@PathVariable String number, @RequestBody(required = true) Creditcard creditcard) {

		return creditcardService.updateCreditcard(number, creditcard);
	}

	@ApiOperation(value = "Delete a Creditcard")
	@ApiParam(name = "number", value = "creditcard number", required = true)
	@ApiResponse(code = 200, message = "Successfully deleted creditcard")
	@RequestMapping(value = "/{number}", method = RequestMethod.DELETE, produces = "application/vnd.api+json")
	public ResponseEntity<String> delete(@PathVariable String number) {
		return creditcardService.delete(number);

	}
	
	@ApiOperation(value = "Pay Creditcard")
	@ApiParam(name = "Payment", value = "creditcard data", required = true)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created payment") })
	@RequestMapping(value = "/pay",method = RequestMethod.POST, produces = "application/vnd.api+json")
	public ResponseEntity<String> payCreditcard(@RequestBody Payment payment) {
		return creditcardService.pay(payment);
	}

}
