package com.nextbuy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextbuy.entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {

}
