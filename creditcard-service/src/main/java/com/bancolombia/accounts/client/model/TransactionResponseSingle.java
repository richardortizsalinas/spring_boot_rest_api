package com.bancolombia.accounts.client.model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.gson.annotations.SerializedName;

/**
 * TransactionResponseSingle
 */
@JsonInclude(Include.NON_NULL)
public class TransactionResponseSingle implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("data")
	private transient TransactionSingle data = null;

	public TransactionResponseSingle data(TransactionSingle data) {
		this.data = data;
		return this;
	}

	/**
	 * Get data
	 * 
	 * @return data
	 **/
	public TransactionSingle getData() {
		return data;
	}

	public void setData(TransactionSingle data) {
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
		TransactionResponseSingle transactionResponseSingle = (TransactionResponseSingle) o;
		return Objects.equals(this.data, transactionResponseSingle.data);
	}

	@Override
	public int hashCode() {
		return Objects.hash(data);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TransactionResponseSingle {\n");

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
