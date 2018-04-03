package com.bancolombia.creditcard.kafka;

public enum PersistenceTypes {
	CREDITCARD("Creditcard"), PAYMENT("Payment");

	private String value;

	PersistenceTypes(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}