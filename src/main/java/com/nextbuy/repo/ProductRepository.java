package com.nextbuy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextbuy.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

}
