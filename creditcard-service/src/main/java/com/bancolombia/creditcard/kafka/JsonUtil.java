package com.bancolombia.creditcard.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

	private static JsonUtil instance = null;


	protected JsonUtil() {
		
	}

	public static JsonUtil getInstance() {
		if (instance == null) {
			instance = new JsonUtil();
		}
		return instance;
	}

	public String toJson(Object data) {
		Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return  g.toJson(data);

	}

}
