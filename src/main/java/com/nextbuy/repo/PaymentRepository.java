package com.nextbuy.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextbuy.entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {
	
	
	List<PaymentEntity> findByUserUserId(Integer userId);

}
