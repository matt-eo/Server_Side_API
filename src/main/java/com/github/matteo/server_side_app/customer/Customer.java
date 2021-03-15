package com.github.matteo.server_side_app.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity(name = "Customer")
@Table(name = "customer")
@ToString // Lombok annotation - will generate toString for us
@EqualsAndHashCode // Lombok will create equals and hashcode for us
public class Customer {

    @Id
    private UUID id;

    //@NotBlank needs to be activated in the controller class
    //where we receive the object by using the @Valid annotation
    @NotBlank(message = "Name must not be empty")
    private String name;

    @NotBlank(message = "Password must not be empty")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Email(message = "Email must not be empty")
    private String email;

    public Customer() {
    }

    public Customer(UUID id,
                    String name,
                    String password,
                    String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public Customer(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.password = customer.getPassword();
        this.email = customer.getEmail();
    }

    public Customer(@NotBlank String name, @NotBlank String password, String email) {
        this(UUID.randomUUID(), name, password, email);
    }

    public Customer(String name) {
        this(UUID.randomUUID(), name, "password", "email@gmail.com");
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
