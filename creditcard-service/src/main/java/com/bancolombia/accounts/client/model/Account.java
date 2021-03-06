package com.bancolombia.accounts.client.model;

import java.math.BigDecimal;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModelProperty;

/**
 * Account
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-04-06T14:54:29.083Z")
public class Account {
  @SerializedName("id")
  private String id = null;

  @SerializedName("clientId")
  private String clientId = null;

  @SerializedName("number")
  private String number = null;

  @SerializedName("balance")
  private BigDecimal balance = null;

  @SerializedName("createdAt")
  private String createdAt = null;

  @SerializedName("updatedAt")
  private String updatedAt = null;

  public Account id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "b3a1bdad-691e-4f19-9c0d-176c53864faf", value = "")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Account clientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

   /**
   * Get clientId
   * @return clientId
  **/
  @ApiModelProperty(example = "CC12345", required = true, value = "")
  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public Account number(String number) {
    this.number = number;
    return this;
  }

   /**
   * Get number
   * @return number
  **/
  @ApiModelProperty(example = "12345", required = true, value = "")
  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public Account balance(BigDecimal balance) {
    this.balance = balance;
    return this;
  }

   /**
   * Get balance
   * minimum: 0
   * @return balance
  **/
  @ApiModelProperty(example = "15000.0", value = "")
  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public Account createdAt(String createdAt) {
    this.createdAt = createdAt;
    return this;
  }

   /**
   * Get createdAt
   * @return createdAt
  **/
  @ApiModelProperty(example = "2018-03-05T21:47:42.223Z", value = "")
  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public Account updatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

   /**
   * Get updatedAt
   * @return updatedAt
  **/
  @ApiModelProperty(example = "2018-03-05T21:47:42.223Z", value = "")
  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(this.id, account.id) &&
        Objects.equals(this.clientId, account.clientId) &&
        Objects.equals(this.number, account.number) &&
        Objects.equals(this.balance, account.balance) &&
        Objects.equals(this.createdAt, account.createdAt) &&
        Objects.equals(this.updatedAt, account.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, clientId, number, balance, createdAt, updatedAt);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
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

