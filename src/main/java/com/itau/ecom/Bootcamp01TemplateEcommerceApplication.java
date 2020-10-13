package com.itau.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
public class Bootcamp01TemplateEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Bootcamp01TemplateEcommerceApplication.class, args);
	}

}
