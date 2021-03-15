package com.github.matteo.server_side_app.customer;

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

    private CustomerService underTest;
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        underTest = new CustomerService(customerRepository);
    }

    @Test
    void itShouldGetAllSavedCustomers() {
        // Given
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        Customer jamila = new Customer(
                id1,
                "Jamila Harris",
                "NvI88sm26go",
                "jam@gmail.com"
        );

        Customer ali = new Customer(
                id2,
                "Ali Baba",
                "as8BNNhxxj",
                "alibaba@gmail.com"
        );

        // When
        customerRepository.saveAll(Arrays.asList(jamila, ali));
        List<Customer> customers = customerRepository.findAllById(Arrays.asList(id1, id2));

        // Then
        assertEquals(2, customers.size());
    }

    @Test
    void itShouldGetCustomer() {
        // Given
        UUID id = UUID.randomUUID();

        Customer otto = new Customer(
                id,
                "Otto Marzotto",
                "NvI88ddd6go",
                "ottomarzo@gmail.com"
        );

        // When
        customerRepository.save(otto);
        Customer actual = underTest.getCustomer(id);

        // Then
        assertEquals(otto, actual);
    }
}
