package com.oopsmails.springboot.mockrestserviceserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ExampleRestService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public String getRootResource() {
		String result = restTemplate.getForObject("http://localhost:8080", String.class);
		System.out.println("getRootResource: " + result);
		
		return result;
	}
	
	public String addComment(String comment) {
		String result = null;
		try {
			result = restTemplate.postForObject("http://localhost/add-comment", comment, String.class);
			System.out.println("addComment: " + result);
		} catch (HttpClientErrorException e) {
			result = e.getMessage();
		}
		
		return result;
	}

}
