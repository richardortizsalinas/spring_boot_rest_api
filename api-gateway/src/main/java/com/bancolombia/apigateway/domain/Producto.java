package com.bancolombia.apigateway.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "type", required = false, value = "The product type", example = "Account, Creditcard")
	private String type;

	@ApiModelProperty(name = "data", required = true, value = "The product data")
	private Object data;
	
	@ApiModelProperty(name = "status", required = true, value = "The state of the data")
	private String status;



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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
