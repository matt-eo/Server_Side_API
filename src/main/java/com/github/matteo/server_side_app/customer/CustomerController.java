package com.github.matteo.server_side_app.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("{id}")
    public Customer getCustomer(@PathVariable("id") UUID id) {
        return customerService.getCustomer(id);
    }

    @GetMapping("email/{customer_email}")
    public Customer getCustomerByEmail(@PathVariable("customer_email") String email) {
        return customerService.getCustomerByEmail(email);
    }

    @PostMapping("register")
    public String createCustomer(@Valid @RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        return "SUCCESS!";
    }

    @PutMapping("update/{id}")
    public void updateCustomer(@Valid @RequestBody Customer customer, @PathVariable("id") UUID id) {
        customerService.updateCustomer(customer, id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteCustomerById(@PathVariable("id") UUID id) {
        customerService.deleteCustomerById(id);
    }

}
