package com.nextbuy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextbuy.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {

}
