package com.oopsmails.springboot.mockrestserviceserver.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.AsyncRestTemplate;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncExampleRestServiceTest {

    @Autowired
    AsyncRestTemplate asyncRestTemplate;

    @Autowired
    AsyncExampleRestService service;

    private MockRestServiceServer mockServer;

    @Before
    public void setUp() {
        mockServer = MockRestServiceServer.createServer(asyncRestTemplate);
    }

    @Test
    public void testDeleteAllSuspendedUsers() {
        mockServer.expect(requestTo("http://localhost/delete-all-suspended-users")).andExpect(method(HttpMethod.DELETE))
                .andRespond(withServerError());

        String result = service.deleteAllSuspendedUsers();
        System.out.println("testDeleteAllSuspendedUsers: " + result);

        mockServer.verify();
        assertEquals("{result: 'server error'}", result);
    }

}
