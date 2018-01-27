package com.oopsmails.springboot.mockrestserviceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class MockRestServiceServerExample {
	
	public static void main(String[] args) {
		SpringApplication.run(MockRestServiceServerExample.class);
	}
	
	@RequestMapping("/")
	public String getRootResource() {
		System.out.println("rootResource");
		return "{message : 'under construction'}";
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public AsyncRestTemplate asyncRestTemplate() {
		return new AsyncRestTemplate();
	}
}
