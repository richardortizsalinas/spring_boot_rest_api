package com.bancolombia.creditcard.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

	private static JsonUtil instance = null;

	private static Gson g;

	protected JsonUtil() {
		g = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	}

	public static JsonUtil getInstance() {
		if (instance == null) {
			instance = new JsonUtil();
		}
		return instance;
	}

	public String toJson(Object data) {

		String jsonString = g.toJson(data);

		return jsonString;
	}

}
