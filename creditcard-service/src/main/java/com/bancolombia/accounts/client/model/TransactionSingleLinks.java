package com.bancolombia.accounts.client.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

/**
 * TransactionSingleLinks
 */
public class TransactionSingleLinks {
  @SerializedName("self")
  private String self = null;

  public TransactionSingleLinks self(String self) {
    this.self = self;
    return this;
  }

   /**
   * Get self
   * @return self
  **/
  public String getSelf() {
    return self;
  }

  public void setSelf(String self) {
    this.self = self;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionSingleLinks transactionSingleLinks = (TransactionSingleLinks) o;
    return Objects.equals(this.self, transactionSingleLinks.self);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransactionSingleLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
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

