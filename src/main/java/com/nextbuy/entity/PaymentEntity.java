package com.nextbuy.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class PaymentEntity {

    @Id
    private Integer payId;

    private String payTnxId;
    private boolean payStatus;
    private LocalDateTime payTnxDateTime;

    // Many-to-One relationship with UserEntity
    @ManyToOne
    @JoinColumn(name = "user_id") // FK column in PaymentEntity referencing UserEntity
    private UserEntity user;

    // One-to-One reverse relationship with OrdersEntity
    @OneToOne(mappedBy = "payment") // Reverse mapping from OrdersEntity
    private OrdersEntity order;
}
