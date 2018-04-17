package com.bancolombia.creditcard.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bancolombia.creditcard.domain.Creditcard;
import com.bancolombia.creditcard.domain.Payment;
import com.bancolombia.creditcard.kafka.Sender;
import com.bancolombia.creditcard.service.CreditcardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import rx.Single;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/creditcard")
@Api(value = "creditcard", description = "Operations pertaining to creditcard ")
public class CreditcardController {

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
	CreditcardService creditcardService;

	@Autowired
	private Sender sender;

//	@Autowired
//	MessageStorage storage;
//	@Autowired
//	private Receiver receiver;

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("data") String data) {
		sender.send(data);
		return "Done";
	}

//	@GetMapping(value = "/consumer")
//	public String getAllRecievedMessage() {
//		String messages = storage.toString();
//		storage.clear();
//		return messages;
//	}

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
	public ResponseEntity<List<Payment>> showCreditcardMovements(@PathVariable String number) {

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
	public ResponseEntity<String> updateCreditcard(@PathVariable String number,
			@RequestBody(required = true) Creditcard creditcard) {

		return creditcardService.updateCreditcard(number, creditcard);
	}

	@ApiOperation(value = "Delete a Creditcard")
	@ApiParam(name = "number", value = "creditcard number", required = true)
	@ApiResponse(code = 200, message = "Successfully deleted creditcard")
	@RequestMapping(value = "/{number}", method = RequestMethod.DELETE, produces = "application/vnd.api+json")
	public ResponseEntity<String> delete(@PathVariable String number) {
		return creditcardService.delete(number);

	}

	@ApiOperation(value = "Search a Creditcard by user id", response = Creditcard.class)
	@ApiParam(name = "owner_id", value = "owner id CC_111111", required = true)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved creditcard"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/owner/{owner_id}", method = RequestMethod.GET, produces = "application/vnd.api+json")
	public ResponseEntity<List<Creditcard>> getCreditcardByUserid(@PathVariable String owner_id) {

		return creditcardService.getByUserId(owner_id);
	}

	@ApiOperation(value = "Pay Creditcard")
	@ApiParam(name = "Payment", value = "creditcard data", required = true)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created payment") })
	@RequestMapping(value = "/pay", method = RequestMethod.POST, produces = "application/vnd.api+json")
	public ResponseEntity<String> payCreditcard(@RequestBody(required = true) Payment payment) {
		return creditcardService.pay(payment);
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST, produces = "application/vnd.api+json")
	 public Single<ResponseEntity<String>> save(@RequestBody(required = true) Creditcard creditcard) {
		 return Single.just(creditcardService.save(creditcard));
	}


}
