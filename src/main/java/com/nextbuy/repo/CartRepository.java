package com.nextbuy.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextbuy.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Integer> {

	
	Optional<CartEntity> findByProdProdIdAndUserUserId(Integer prodId,Integer userId);
	
	List<CartEntity> findByUserUserId(Integer userId);

}
