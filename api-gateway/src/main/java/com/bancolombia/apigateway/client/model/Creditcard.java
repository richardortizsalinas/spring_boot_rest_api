package com.bancolombia.apigateway.client.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;


public class Creditcard {
	
  private BigDecimal creditLimit = null;

  private Date expirationDate = null;

  private Integer id = null;

  private String number = null;

  private String ownerId = null;

  private String ownerIdType = null;

  public Creditcard creditLimit(BigDecimal creditLimit) {
    this.creditLimit = creditLimit;
    return this;
  }

   /**
   * The creditcard limit
   * @return creditLimit
  **/
  public BigDecimal getCreditLimit() {
    return creditLimit;
  }

  public void setCreditLimit(BigDecimal creditLimit) {
    this.creditLimit = creditLimit;
  }

  public Creditcard expirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
    return this;
  }

   /**
   * The creditcard expiration_date
   * @return expirationDate
  **/
  public Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }

  public Creditcard id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * The database generated creditcard ID
   * @return id
  **/
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Creditcard number(String number) {
    this.number = number;
    return this;
  }

   /**
   * The creditcard number
   * @return number
  **/
  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public Creditcard ownerId(String ownerId) {
    this.ownerId = ownerId;
    return this;
  }

   /**
   * The creditcard owner_id
   * @return ownerId
  **/
  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public Creditcard ownerIdType(String ownerIdType) {
    this.ownerIdType = ownerIdType;
    return this;
  }

   /**
   * The creditcard owner_id_type
   * @return ownerIdType
  **/
  public String getOwnerIdType() {
    return ownerIdType;
  }

  public void setOwnerIdType(String ownerIdType) {
    this.ownerIdType = ownerIdType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Creditcard creditcard = (Creditcard) o;
    return Objects.equals(this.creditLimit, creditcard.creditLimit) &&
        Objects.equals(this.expirationDate, creditcard.expirationDate) &&
        Objects.equals(this.id, creditcard.id) &&
        Objects.equals(this.number, creditcard.number) &&
        Objects.equals(this.ownerId, creditcard.ownerId) &&
        Objects.equals(this.ownerIdType, creditcard.ownerIdType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(creditLimit, expirationDate, id, number, ownerId, ownerIdType);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Creditcard {\n");
    
    sb.append("    creditLimit: ").append(toIndentedString(creditLimit)).append("\n");
    sb.append("    expirationDate: ").append(toIndentedString(expirationDate)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    ownerId: ").append(toIndentedString(ownerId)).append("\n");
    sb.append("    ownerIdType: ").append(toIndentedString(ownerIdType)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

