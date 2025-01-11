package com.nextbuy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextbuy.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
