package com.bancolombia.creditcard.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.github.jasminb.jsonapi.annotations.Type;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * The persistent class for the creditcard database table.
 * 
 */
@Entity
@Table(name="creditcard")
@ApiModel
@Type("creditcard")
public class Creditcard implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 @ApiModelProperty(name = "id",required=false,value = "The database generated creditcard ID")
	 @com.github.jasminb.jsonapi.annotations.Id
	private Integer id;
	
	@ApiModelProperty(name = "number",required=true,value = "The creditcard number",example="0012003003")
	private String number;
	
	@ApiModelProperty(name = "expiration_date",required=true,value = "The creditcard expiration_date",example="2018-03-07T22:00:00.000+0000")
	private Date expiration_date;
	
	@ApiModelProperty(name = "credit_limit",required=true,value = "The creditcard limit",example="0012003003")
	private BigDecimal credit_limit;
	
	@ApiModelProperty(name = "owner_id",required=true,value = "The creditcard owner_id",example="0012003003")
	private String ownerId;
	
	@ApiModelProperty(name = "owner_id_type",required=true,value = "The creditcard owner_id_type",example="0012003003")
	private String ownerIdType;
	
	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getExpiration_date() {
		return expiration_date;
	}

	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}


	public BigDecimal getCredit_limit() {
		return credit_limit;
	}

	public void setCredit_limit(BigDecimal credit_limit) {
		this.credit_limit = credit_limit;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerIdType() {
		return ownerIdType;
	}

	public void setOwnerIdType(String ownerIdType) {
		this.ownerIdType = ownerIdType;
	}


	
}
