package com.oopsmails.springboot.mockrestserviceserver.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestGatewaySupport;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ExampleRestServiceTestViaRestGateway {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ExampleRestService service;

    private MockRestServiceServer mockServer;

    @Before
    public void setUp() {
        RestGatewaySupport gateway = new RestGatewaySupport();
        gateway.setRestTemplate(restTemplate);
        mockServer = MockRestServiceServer.createServer(gateway);
    }

    @Test
    public void testGetRootResourceOnce() {
        mockServer.expect(once(), requestTo("http://localhost:8080"))
                .andRespond(withSuccess("{message : 'under construction'}", MediaType.APPLICATION_JSON));

        String result = service.getRootResource();
        System.out.println("testGetRootResourceOnce: " + result);

        mockServer.verify();
        assertEquals("{message : 'under construction'}", result);
    }

}
