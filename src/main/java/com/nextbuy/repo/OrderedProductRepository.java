package com.nextbuy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextbuy.entity.OrderedProductEntity;

public interface OrderedProductRepository extends JpaRepository<OrderedProductEntity, Integer> {

}
