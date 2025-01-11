package com.nextbuy.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class CartEntity {

	@Id
	private Integer cartId;
	private Integer cartQnty;

	@ManyToOne
	@JoinColumn(name = "user_id") // Foreign key column for UserEntity
	private UserEntity user;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "cart_product", // Join table name
			joinColumns = @JoinColumn(name = "cart_id"), // Foreign key in join table for UserEntity
			inverseJoinColumns = @JoinColumn(name = "prod_id") // Foreign key in join table for AddressEntity
	)
	private List<ProductEntity> prods;
}
