package com.assignment.contactservice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(classes = ContactserviceApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class ContactServiceIntegrationTests {

	@LocalServerPort
	private int randomServerPort;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "testdata.sql")
	@Test
    public void getAllContactsTest()
    {	
        assertTrue(this.restTemplate.getForObject("http://localhost:" + randomServerPort + "/contactbook/contacts/", List.class).size() == 3);
    }
	
}
