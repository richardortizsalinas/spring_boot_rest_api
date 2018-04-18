package com.bancolombia.accounts.client.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

/**
 * TransactionResponseArray
 */
public class TransactionResponseArray {
  @SerializedName("data")
  private TransactionArray data = null;

  public TransactionResponseArray data(TransactionArray data) {
    this.data = data;
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  public TransactionArray getData() {
    return data;
  }

  public void setData(TransactionArray data) {
    this.data = data;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionResponseArray transactionResponseArray = (TransactionResponseArray) o;
    return Objects.equals(this.data, transactionResponseArray.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransactionResponseArray {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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

