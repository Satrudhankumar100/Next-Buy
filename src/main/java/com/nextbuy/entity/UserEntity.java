package com.nextbuy.entity;

import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	private String userName;
	private String userGender;
	private String userEmail;
	private String userPwd;
	private String userPhone;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user",fetch = FetchType.LAZY) // cascade all operations like persist
	private List<OrdersEntity> orders;
	
	 // One-to-many relationship with AddressEntity
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<AddressEntity> userAddr;
    
}
