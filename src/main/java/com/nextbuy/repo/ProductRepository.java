package com.nextbuy.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nextbuy.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
	
	// Query to find products by name or title (case-insensitive)
    @Query("SELECT p FROM ProductEntity p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(p.title) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<ProductEntity> searchByNameOrTitle(String search);
    
    

}
