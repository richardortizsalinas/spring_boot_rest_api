/*
 * Accounts
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.bancolombia.accounts.client.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * AccountSingleLinks
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-04-06T14:54:29.083Z")
public class AccountSingleLinks {
  @SerializedName("self")
  private String self = null;

  public AccountSingleLinks self(String self) {
    this.self = self;
    return this;
  }

   /**
   * Get self
   * @return self
  **/
  @ApiModelProperty(example = "http://localhost:3000/api/accounts/CC12345", required = true, value = "")
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
    AccountSingleLinks accountSingleLinks = (AccountSingleLinks) o;
    return Objects.equals(this.self, accountSingleLinks.self);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountSingleLinks {\n");
    
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

