package com.bancolombia.creditcard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableEurekaClient
@SpringBootApplication
public class CreditcardApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditcardApplication.class, args);
    }
    @Value("${spring.datasource.url}")
    String password;

    @PostConstruct
    private void postConstruct() {
        System.out.println("My password is: " + password);
    }

}