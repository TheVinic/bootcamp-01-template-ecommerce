package com.itau.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.itau.ecom")
public class Bootcamp01TemplateEcommerceApplication  extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Bootcamp01TemplateEcommerceApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Bootcamp01TemplateEcommerceApplication.class);
	}

}
