package com.github.matteo.server_side_app.customer;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Primary
public interface CustomerRepository extends JpaRepository<Customer, UUID> {


}
