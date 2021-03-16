package com.github.matteo.server_side_app.customer;

import com.github.javafaker.Faker;
import com.github.matteo.server_side_app.order.CustomerOrder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            Faker faker = new Faker();

            for (int i = 0; i < 20; i++) {
                String firstName = faker.name().firstName();
                String lastName = faker.name().lastName();
                String password = faker.company().buzzword();
                String email = String.format("%s.%s@gmail.com", firstName, lastName);
                String address = faker.address().fullAddress();

                Customer customer = new Customer(firstName + " " + lastName, password, email, address);

                String productName = faker.commerce().productName();
                Double price = Double.valueOf(faker.commerce().price(0.50, 500.0));

                customer.addOrder(new CustomerOrder(
                        productName,
                        price,
                        LocalDateTime.now().minusDays(ThreadLocalRandom.current().nextInt(100))));

                customerRepository.save(customer);
            }
        };
    }
}
