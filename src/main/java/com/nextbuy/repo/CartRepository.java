package com.nextbuy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextbuy.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Integer> {

}
