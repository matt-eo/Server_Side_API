package com.github.matteo.server_side_app.customer;

import com.github.matteo.server_side_app.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j // Lombok will provide logger accessible via 'log.'
public class CustomerService {

    // Now provided by Lombok
    // private final static Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepo;

    public CustomerService(CustomerRepository customerRepo) {
        log.info("getCustomer was called");
        this.customerRepo = customerRepo;
    }

    List<Customer> getCustomers() {
        return customerRepo.findAll();
    }

    Customer getCustomer(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> {
                    NotFoundException notFoundException = new NotFoundException(
                            "Customer NOT found"
                    );
                    log.error("error in getting customer {}", notFoundException.toString());
                    return notFoundException;
                });
    }

    Customer getCustomerByEmail(String email) {
        return customerRepo.findCustomerByEmail(email)
                .orElseThrow(() -> {
                    NotFoundException notFoundException = new NotFoundException(
                            "Customer NOT found"
                    );
                    log.error("error in getting customer {}", notFoundException.toString());
                    return notFoundException;
                });
    }

    void saveCustomer(Customer customer) {
        log.info("Saving Customer...");
        customerRepo.save(customer);
    }

    void updateCustomer(Customer customer, Long id) {
        Optional<Customer> maybeCustomer = customerRepo.findById(id);
        if (maybeCustomer.isEmpty()) {
            throw new IllegalStateException("The customer with id: " + id + " does not exist");
        }
        Customer toUpdate = new Customer(customer);
        customerRepo.save(toUpdate);
    }

    void deleteCustomerById(Long id) {
        Optional<Customer> maybeCustomer = customerRepo.findById(id);
        if (maybeCustomer.isEmpty()) {
            throw new IllegalStateException("The customer with id: " + id + " does not exist");
        }
        customerRepo.deleteById(id);
    }

}
