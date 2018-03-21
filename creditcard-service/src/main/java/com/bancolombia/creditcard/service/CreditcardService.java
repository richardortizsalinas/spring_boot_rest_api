package com.bancolombia.creditcard.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.bancolombia.creditcard.domain.Creditcard;
import com.bancolombia.creditcard.domain.Payment;
import com.bancolombia.creditcard.repository.CreditcardRepository;
import com.bancolombia.creditcard.repository.PaymentRepository;

@Service
public class CreditcardService{
	
	@Autowired
	CreditcardRepository creditcardRepository;
	
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
	
	
	public ResponseEntity<List<Creditcard>> getByUserId(String owner_id) {
		
		String[] data=owner_id.split("_");
		
		String type=data[0];
		String number=data[1];
		
		List<Creditcard> list = creditcardRepository.getByOwner(type, number);

		ResponseEntity<List<Creditcard>> responseEntity;

		if (list == null || list.isEmpty()) {
			responseEntity = new ResponseEntity<List<Creditcard>>(list, HttpStatus.NOT_FOUND);

		} else {
			responseEntity = new ResponseEntity<List<Creditcard>>(list, HttpStatus.OK);
		}

		return responseEntity;
	}

	public ResponseEntity<List<Payment>> getMovements(String number) {

		Creditcard data = creditcardRepository.findByNumber(number);

		List<Payment> movementList = paymentRepository.findByCreditcard(data);

		ResponseEntity<List<Payment>> responseEntity;

		if (data == null) {
			responseEntity = new ResponseEntity<List<Payment>>(movementList, HttpStatus.NOT_FOUND);

		} else {
			responseEntity = new ResponseEntity<List<Payment>>(movementList, HttpStatus.OK);
		}

		return responseEntity;
	}

	public ResponseEntity<String> save(Creditcard creditcard) {
		try {

			Creditcard newCard = new Creditcard();
			newCard.setExpiration_date(creditcard.getExpiration_date());
			newCard.setCredit_limit(creditcard.getCredit_limit());
			newCard.setNumber(creditcard.getNumber());
			newCard.setOwnerId(creditcard.getOwnerId());
			newCard.setOwnerIdType(creditcard.getOwnerIdType());
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
		data.setOwnerId(creditcard.getOwnerId());
		data.setOwnerIdType(creditcard.getOwnerIdType());
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
			System.out.println(payment.getAccountId());
			Payment newPayment = new Payment();
			newPayment.setAmount(payment.getAmount());
			newPayment.setCreditcard(data);
			newPayment.setDate(new Date());
			newPayment.setAccountId(payment.getAccountId());
			paymentRepository.save(newPayment);
			return new ResponseEntity<String>("Payment saved successfully", HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}