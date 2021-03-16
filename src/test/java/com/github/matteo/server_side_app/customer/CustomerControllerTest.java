package com.github.matteo.server_side_app.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URL;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    @LocalServerPort
    private int port;

    private URL base;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/api/v1/customers");
    }

    @Test
    public void itShould_ReturnAListOfCustomer() throws Exception {
        // given
        List<Customer> expected = List.of(
                new Customer(),
                new Customer(),
                new Customer()
        );

        when(customerService.getCustomers()).thenReturn(expected);
        // when
        ResponseEntity<List> responseEntity =
                template.postForEntity(base.toString(), HttpMethod.GET, List.class);
        List<Customer> actual = responseEntity.getBody();

        // then
        assertEquals(expected.size(), actual.size());
    }

}