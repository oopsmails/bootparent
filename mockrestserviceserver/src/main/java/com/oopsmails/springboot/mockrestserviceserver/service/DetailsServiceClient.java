package com.oopsmails.springboot.mockrestserviceserver.service;

import com.oopsmails.springboot.mockrestserviceserver.domain.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DetailsServiceClient {
    @Autowired
    private RestTemplate restTemplate;

    public Details getUserDetails(String name) {
        return restTemplate.getForObject("/{name}/details",
                Details.class, name);
    }
}
