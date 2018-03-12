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

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * The persistent class for the child database table.
 * 
 */
@Entity
@Table(name="movement")
@ApiModel
public class Movement implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 @ApiModelProperty(name = "id",required=false,value = "The database generated movement ID")
	private Integer id;
	
	@ApiModelProperty(name = "date",required=true,value = "The movement date",example="2018-03-07T22:00:00.000+0000")
	private Date date;
	
	@ApiModelProperty(name = "type",required=true,value = "The movement type",example="IN")
	private String type;
	
	@ApiModelProperty(name = "type",required=true,value = "The movement amount",example="10000")
	private BigDecimal amount;

	@ApiModelProperty(name = "creditcard",required=true,value = "The creditcard number")
	private Creditcard creditcard;

	public Movement() {
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

}
