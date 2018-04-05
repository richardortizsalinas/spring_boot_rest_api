package com.bancolombia.creditcard.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties()
public class Configuration {

	 @Value("${service.deposits.endpoint}")
	 private static String depositsServiceEndpoint;

	public static String getDepositsServiceEndpoint() {
		return depositsServiceEndpoint;
	}
	
}