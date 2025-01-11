package com.nextbuy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextbuy.entity.OrdersEntity;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer> {

}
