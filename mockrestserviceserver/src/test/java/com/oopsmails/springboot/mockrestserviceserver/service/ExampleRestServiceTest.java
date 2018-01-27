package com.oopsmails.springboot.mockrestserviceserver.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.ExpectedCount.times;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class) //SpringRunner is an alias for the SpringJUnit4ClassRunner
@SpringBootTest
public class ExampleRestServiceTest {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ExampleRestService service;

    private MockRestServiceServer mockServer;

    @Before
    public void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testGetRootResource() {
        mockServer.expect(requestTo("http://localhost:8080")).andRespond(withSuccess("hello", MediaType.TEXT_PLAIN));

        String result = service.getRootResource();
        System.out.println("testGetRootResource: " + result);

        mockServer.verify();
        assertEquals("hello", result);
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

    @Test
    public void testGetRootResourceTimes() {
        // originally times(2), will fail
        mockServer.expect(times(1), requestTo("http://localhost:8080"))
                .andRespond(withSuccess("{message : 'under construction'}", MediaType.APPLICATION_JSON));

        String result = service.getRootResource();
        System.out.println("testGetRootResourceTimes: " + result);

        mockServer.verify(); // should fail because this test expects RestTemplate.getForObject to be called twice
        assertEquals("{message : 'under construction'}", result);
    }

    @Test
    public void testAddComment() {
        mockServer.expect(requestTo("http://localhost/add-comment"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess("{post : 'success'}", MediaType.APPLICATION_JSON));

        String result = service.addComment("cute puppy");
        System.out.println("testAddComment: " + result);

        mockServer.verify();
        assertEquals("{post : 'success'}", result);
    }

    @Test
    public void testAddCommentClientError() {
        mockServer.expect(requestTo("http://localhost/add-comment")).andExpect(method(HttpMethod.POST))
                .andRespond(withBadRequest());

        String result = service.addComment("cute puppy");
        System.out.println("testAddCommentClientError: " + result);

        mockServer.verify();
        assertEquals("400 Bad Request", result);
    }

    @Test
    public void testReset() {
        mockServer.expect(requestTo("http://localhost/add-comment")).andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess("{post : 'success'}", MediaType.APPLICATION_JSON));

        String result = service.addComment("cute puppy");
        System.out.println("testReset 1st: " + result);

        mockServer.verify();
        assertEquals("{post : 'success'}", result);

        mockServer.reset();

        mockServer.expect(requestTo("http://localhost:8080")).andRespond(withSuccess("hello", MediaType.TEXT_PLAIN));

        result = service.getRootResource();
        System.out.println("testReset 2nd: " + result);

        mockServer.verify();
        assertEquals("hello", result);
    }
}
