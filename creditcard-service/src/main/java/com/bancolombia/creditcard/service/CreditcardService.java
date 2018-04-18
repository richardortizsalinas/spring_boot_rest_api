package com.bancolombia.creditcard.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.bancolombia.accounts.client.TransactionApi;
import com.bancolombia.accounts.client.model.Transaction;
import com.bancolombia.accounts.client.model.TransactionResponseSingle;
import com.bancolombia.accounts.client.model.TransactionSingle;
import com.bancolombia.creditcard.domain.Creditcard;
import com.bancolombia.creditcard.domain.Payment;
import com.bancolombia.creditcard.kafka.JsonUtil;
import com.bancolombia.creditcard.kafka.PersistenceMessage;
import com.bancolombia.creditcard.kafka.PersistenceOperations;
import com.bancolombia.creditcard.kafka.PersistenceTypes;
import com.bancolombia.creditcard.kafka.Sender;
import com.bancolombia.creditcard.repository.CreditcardRepository;
import com.bancolombia.creditcard.repository.PaymentRepository;

@Service
public class CreditcardService {

	private static final String CUENTA_BANCO = "0000000000";
	
	private static final Logger LOG = LoggerFactory.getLogger(CreditcardService.class);

	@Value("${service.deposits.endpoint}")
	String depositsServiceEndpoint;

	@Autowired
	CreditcardRepository creditcardRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	private Sender sender;

	public List<Creditcard> getAll() {
		return creditcardRepository.findAll();
	}

	public ResponseEntity<Creditcard> get(String number) {

		Creditcard data = creditcardRepository.findByNumber(number);

		ResponseEntity<Creditcard> responseEntity;

		if (data == null) {
			responseEntity = new ResponseEntity<>(data, HttpStatus.NOT_FOUND);

		} else {
			responseEntity = new ResponseEntity<>(data, HttpStatus.OK);
		}

		return responseEntity;
	}

	public ResponseEntity<List<Creditcard>> getByUserId(String ownerId) {

		String[] data = ownerId.split("_");

		String type = data[0];
		String number = data[1];

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

			PersistenceMessage mensaje = new PersistenceMessage();

			mensaje.setOperation(PersistenceOperations.CREATE.value());
			mensaje.setType(PersistenceTypes.CREDITCARD.value());
			mensaje.setData(newCard);

			String message = JsonUtil.getInstance().toJson(mensaje);

			sender.send(message);

			return new ResponseEntity<String>("Creditcard saved successfully", HttpStatus.OK);

		} catch (Exception e) {
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

		PersistenceMessage mensaje = new PersistenceMessage();

		mensaje.setOperation(PersistenceOperations.UPDATE.value());
		mensaje.setType(PersistenceTypes.CREDITCARD.value());
		mensaje.setData(data);

		String message = JsonUtil.getInstance().toJson(mensaje);

		sender.send(message);

		return new ResponseEntity<String>("Creditcard updated successfully",
				HttpStatus.OK);

	}

	public ResponseEntity<String> delete(@PathVariable String number) {

		Creditcard data = creditcardRepository.findByNumber(number);

		PersistenceMessage mensaje = new PersistenceMessage();

		mensaje.setOperation(PersistenceOperations.DELETE.value());
		mensaje.setType(PersistenceTypes.CREDITCARD.value());
		mensaje.setData(data);

		String message = JsonUtil.getInstance().toJson(mensaje);

		sender.send(message);

		return new ResponseEntity<String>("Creditcard deleted successfully", HttpStatus.OK);

	}

	public ResponseEntity<String> pay(Payment payment) {
		try {
			
			Creditcard data = creditcardRepository.findByNumber(payment.getCcnumber());
			// debito a cuenta

			TransactionApi cliente = new TransactionApi(depositsServiceEndpoint);
			TransactionResponseSingle requestBody = new TransactionResponseSingle();

			TransactionSingle request = new TransactionSingle();
			request.setId("");
			request.setType(TransactionSingle.TypeEnum.Transactions);

			Transaction attributes = new Transaction();
			attributes.setId("");
			attributes.setAmount(payment.getAmount());
			attributes.setOrigin(payment.getAccountId());
			attributes.setDestination(CUENTA_BANCO);
			request.setAttributes(attributes);
			requestBody.setData(request);
			
			TransactionResponseSingle response = cliente.create(requestBody);
			
			if (response != null) {
				
				// modificacion del cupo disponible
				BigDecimal cupo = data.getCredit_limit().add(payment.getAmount());
				data.setCredit_limit(cupo);
				updateCreditcard(payment.getCcnumber(), data);

				// registro del pago
				Payment newPayment = new Payment();
				newPayment.setAmount(payment.getAmount());
				newPayment.setCreditcard(data);
				newPayment.setDate(new Date());
				newPayment.setAccountId(payment.getAccountId());

				send(PersistenceOperations.CREATE.value(), PersistenceTypes.PAYMENT.value(), newPayment);

				return new ResponseEntity<String>("Payment saved successfully", HttpStatus.OK);

			} else {

				return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			LOG.error("ERROR",e);
			return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public void send(String operation, String type, Object data) {
		PersistenceMessage mensajePayment = new PersistenceMessage();

		mensajePayment.setOperation(operation);
		mensajePayment.setType(type);
		mensajePayment.setData(data);

		String message = JsonUtil.getInstance().toJson(mensajePayment);

		sender.send(message);
	}

}