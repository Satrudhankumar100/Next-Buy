package com.nextbuy.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class OrdersEntity {

    @Id
    private Integer ordId;
    private LocalDateTime ordDate;
    private boolean ordStatus;
    private Double ordTotalAmount;
    private LocalDateTime ordCreatedDate;
    private String ordAddress;
    private LocalDateTime ordDeliveryDate;

    // Many-to-One relationship with UserEntity
    @ManyToOne
    @JoinColumn(name = "user_id") // Foreign key column in OrdersEntity
    private UserEntity user;

    // One-to-Many relationship with OrderedProductEntity
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "ordered_product", // Join table name
        joinColumns = @JoinColumn(name = "order_id"), // FK in join table for OrdersEntity
        inverseJoinColumns = @JoinColumn(name = "ord_prod_id") // FK in join table for OrderedProductEntity
    )
    private List<OrderedProductEntity> orderedProducts;

    // One-to-One relationship with PaymentEntity
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pay_id") // FK column for PaymentEntity in OrdersEntity
    private PaymentEntity payment;
}
