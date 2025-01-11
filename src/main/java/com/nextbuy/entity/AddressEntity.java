package com.nextbuy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class AddressEntity {
	
	
	@Id
	private Integer addId;
	private String country;
	private String state;
	private String city;
	private Integer pincode;
	
	 // Many-to-one relationship with UserEntity
    @ManyToOne
    @JoinColumn(name = "user_id") // Foreign key for user reference in AddressEntity
    private UserEntity user; // Relationship with UserEntity

	
}
