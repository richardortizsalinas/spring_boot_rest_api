package com.bancolombia.creditcard.kafka;

public enum PersistenceOperations {
	CREATE("create"), UPDATE("update"), DELETE("delete");

	private String value;

	PersistenceOperations(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}