package com.bancolombia.creditcard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.bancolombia.creditcard.domain.Creditcard;
import com.bancolombia.creditcard.domain.Movement;
import com.bancolombia.creditcard.domain.Payment;
import com.bancolombia.creditcard.repository.CreditcardRepository;
import com.bancolombia.creditcard.repository.MovementRepository;
import com.bancolombia.creditcard.repository.PaymentRepository;

@Service
public class CreditcardService{
	
	@Autowired
	CreditcardRepository creditcardRepository;

	@Autowired
	MovementRepository movementRepository;
	
	@Autowired
	PaymentRepository paymentRepository;

	
	public List<Creditcard> getAll() {
		List<Creditcard> productList = creditcardRepository.findAll();
		return productList;
	}
	
	public ResponseEntity<Creditcard> get(String number) {

		Creditcard data = creditcardRepository.findByNumber(number);

		ResponseEntity<Creditcard> responseEntity;

		if (data == null) {
			responseEntity = new ResponseEntity<Creditcard>(data, HttpStatus.NOT_FOUND);

		} else {
			responseEntity = new ResponseEntity<Creditcard>(data, HttpStatus.OK);
		}

		return responseEntity;
	}

	public ResponseEntity<List<Movement>> getMovements(String number) {

		Creditcard data = creditcardRepository.findByNumber(number);

		List<Movement> movementList = movementRepository.findByCreditcard(data);

		ResponseEntity<List<Movement>> responseEntity;

		if (data == null) {
			responseEntity = new ResponseEntity<List<Movement>>(movementList, HttpStatus.NOT_FOUND);

		} else {
			responseEntity = new ResponseEntity<List<Movement>>(movementList, HttpStatus.OK);
		}

		return responseEntity;
	}

	public ResponseEntity<String> save(Creditcard creditcard) {
		try {

			Creditcard newCard = new Creditcard();
			newCard.setExpiration_date(creditcard.getExpiration_date());
			newCard.setCredit_limit(creditcard.getCredit_limit());
			newCard.setNumber(creditcard.getNumber());
			newCard.setOwner_id(creditcard.getOwner_id());
			newCard.setOwner_id_type(creditcard.getOwner_id_type());
			creditcardRepository.save(newCard);
			return new ResponseEntity<String>("Creditcard saved successfully", HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public ResponseEntity<String> updateCreditcard(@PathVariable String number, @RequestBody Creditcard creditcard) {

		Creditcard data = creditcardRepository.findByNumber(number);
		data.setNumber(creditcard.getNumber());
		data.setExpiration_date(creditcard.getExpiration_date());
		data.setCredit_limit(creditcard.getCredit_limit());
		data.setNumber(creditcard.getNumber());
		data.setOwner_id(creditcard.getOwner_id());
		data.setOwner_id_type(creditcard.getOwner_id_type());
		creditcardRepository.save(data);

		ResponseEntity<String> responseEntity = new ResponseEntity<String>("Creditcard updated successfully",
				HttpStatus.OK);

		return responseEntity;
	}

	public ResponseEntity<String> delete(@PathVariable String number) {
		Creditcard data = creditcardRepository.findByNumber(number);
		creditcardRepository.delete(data);
		return new ResponseEntity<String>("Creditcard deleted successfully", HttpStatus.OK);

	}
	
	public ResponseEntity<String> pay(@RequestBody Payment payment) {
		try {

			Creditcard data = creditcardRepository.findByNumber(payment.getCreditcard().getNumber());
			
			Payment newPayment = new Payment();
			newPayment.setAmount(payment.getAmount());
			newPayment.setCreditcard(data);
			newPayment.setDate(payment.getDate());
			paymentRepository.save(newPayment);
			return new ResponseEntity<String>("Payment saved successfully", HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
}