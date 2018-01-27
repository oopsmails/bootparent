package com.oopsmails.springboot.mockrestserviceserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oopsmails.springboot.mockrestserviceserver.domain.Details;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(DetailsServiceClient.class)
public class DetailsServiceClientTest {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DetailsServiceClient client;

    //    @Autowired
    private MockRestServiceServer mockServer;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void whenCallingGetUserDetails_thenClientMakesCorrectCall()
            throws Exception {
        String detailsString =
                objectMapper.writeValueAsString(new Details("John Smith", "john"));
        mockServer.expect(once(), requestTo("/john/details"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));

        Details details = this.client.getUserDetails("john");

        assertEquals("john", details.getLogin());
        assertEquals("John Smith", details.getName());
    }
}
