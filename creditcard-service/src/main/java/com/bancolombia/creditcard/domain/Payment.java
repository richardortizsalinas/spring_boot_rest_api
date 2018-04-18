package com.bancolombia.creditcard.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * The persistent class for the child database table.
 * 
 */
@Entity
@Table(name="payment")
@ApiModel
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 @ApiModelProperty(name = "id",required=false,value = "The database generated payment ID")
	private Integer id;
	
	@ApiModelProperty(name = "date",required=true,value = "The payment date",example="2018-03-07T22:00:00.000+0000")
	private Date date;
	
	@ApiModelProperty(name = "amount",required=true,value = "The payment amount",example="10000")
	private BigDecimal amount;
	
	@ApiModelProperty(name = "account_id",required=true,value = "The account for the payment",example="1112222333")
	@SerializedName("account_id")
	private String accountId;

	@ApiModelProperty(name = "creditcard",required=true,value = "The creditcard number")
	private Creditcard creditcard;
	
	
	@ApiModelProperty(name = "ccnumber",required=true,value = "The creditcard number",example="1112222333")
	@SerializedName("ccnumber")
	private String ccnumber;
	

	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@ManyToOne
	@JoinColumn(name="creditcard_id", nullable=false)
	@JsonBackReference
	public Creditcard getCreditcard() {
		return this.creditcard;
	}

	public void setCreditcard(Creditcard parent) {
		this.creditcard = parent;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCcnumber() {
		return ccnumber;
	}

	public void setCcnumber(String ccnumber) {
		this.ccnumber = ccnumber;
	}

}
