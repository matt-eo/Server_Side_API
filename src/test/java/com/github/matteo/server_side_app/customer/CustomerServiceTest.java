package com.github.matteo.server_side_app.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    private CustomerService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CustomerService(customerRepository);
    }

    @AfterEach
    void cleanUp() {
        customerRepository.deleteAll();
    }

    @Test
    void itShouldGetAllSavedCustomers() {
        // Given
        Customer jamila = new Customer(
                1L,
                UUID.randomUUID(),
                "Jamila Harris",
                "NvI88sm26go",
                "jam@gmail.com",
                "80 Arcor Street"
        );

        Customer ali = new Customer(
                2L,
                UUID.randomUUID(),
                "Ali Baba",
                "as8BNNhxxj",
                "alibaba@gmail.com",
                "70 Calma Avenue"
        );

        // When
        customerRepository.saveAll(Arrays.asList(jamila, ali));
        List<Customer> customers = customerRepository.findAllById(Arrays.asList(1L, 2L));

        // Then
        assertEquals(2, customers.size());
    }

    @Test
    void itShouldGetCustomer() {
        // Given
        Customer otto = new Customer(
                UUID.randomUUID(),
                "Otto Marzotto",
                "NvI88ddd6go",
                "ottomarzo@gmail.com",
                "101 Warehouse Court"
        );

        // When
        customerRepository.save(otto);
        Customer actual = underTest.getCustomer(1L);

        // Then
        assertEquals(otto, actual);
    }
}
