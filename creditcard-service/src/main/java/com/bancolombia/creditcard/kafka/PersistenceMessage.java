package com.bancolombia.creditcard.kafka;

public class PersistenceMessage {
	
	
	 String operation;
	 String type;
	 Object data;
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	 
}
