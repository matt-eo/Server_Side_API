package com.github.matteo.server_side_app.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.matteo.server_side_app.customer.Customer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "CustomerOrder")
@Table(name = "customer_order")
@EqualsAndHashCode
@ToString
@Setter
@Getter
public class CustomerOrder {

    @Id
    @SequenceGenerator(
            name = "order_id_sequence",
            sequenceName = "order_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_id_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "product_name",
            nullable = false
    )
    private String productName;

    @Column(
            name = "price",
            nullable = false
    )
    private Double price;

    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "customer_id_fk"
            )
    )
    @JsonBackReference
    private Customer customer;

    public CustomerOrder() {}

    public CustomerOrder(String productName,
                         LocalDateTime createdAt,
                         Customer customer) {
        this.productName = productName;
        this.createdAt = createdAt;
        this.customer = customer;
    }

    public CustomerOrder(String productName, Double price, LocalDateTime createdAt) {
        this.productName = productName;
        this.price = price;
        this.createdAt = createdAt;
    }
}
