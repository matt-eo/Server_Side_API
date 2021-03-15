package com.github.matteo.server_side_app.customer;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CustomerFakeRepository implements CustomerRepo {

    @Override
    public List<Customer> getCustomers() {
        return List.of(
                new Customer(UUID.randomUUID(), "Carl", "12345", "email@gmail.com"),
                new Customer(UUID.randomUUID(), "Simon", "67894", "email@gmail.com")
        );
    }
}
