package com.github.matteo.server_side_app.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.matteo.server_side_app.order.CustomerOrder;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "Customer")
@Table(
        name = "customer",
        uniqueConstraints = {
                @UniqueConstraint(name = "customer_email_unique", columnNames = "email")
        }
)
@ToString
@EqualsAndHashCode
@Setter
public class Customer {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(name = "recovery_key")
    private UUID accountRecoveryKey;

    //@NotBlank needs to be activated in the controller class
    //where we receive the object by using the @Valid annotation
    @NotBlank(message = "Name must not be empty")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Password must not be empty")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    @Email(message = "Email must not be empty")
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Address must not be empty")
    private String address;

    @OneToMany(
            mappedBy = "customer",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY

    )
    @JsonManagedReference
    private List<CustomerOrder> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(Long id,
                    UUID accountRecoveryKey,
                    String name,
                    String password,
                    String email,
                    String address) {
        this.id = id;
        this.accountRecoveryKey = accountRecoveryKey;
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
    }

    public Customer(UUID accountRecoveryKey,
                    String name,
                    String password,
                    String email,
                    String address) {
        this.accountRecoveryKey = accountRecoveryKey;
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
    }

    public Customer(String name, String password, String email, String address) {
        this(UUID.randomUUID(), name, password, email, address);
    }

    public Customer(Customer customer) {
        this.accountRecoveryKey = customer.getAccountRecoveryKey();
        this.name = customer.getName();
        this.password = customer.getPassword();
        this.email = customer.getEmail();
        this.address = customer.getAddress();
    }

    public Long getId() {
        return id;
    }

    public UUID getAccountRecoveryKey() {
        return accountRecoveryKey;
    }

    public String getName() {
        return name;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void addOrder(CustomerOrder order) {
        if (!orders.contains(order)) {
            orders.add(order);
            order.setCustomer(this);
        }
    }

    public void removeOrder(CustomerOrder order) {
        if (orders.contains(order)) {
            orders.remove(order);
            order.setCustomer(null);
        }
    }
}
