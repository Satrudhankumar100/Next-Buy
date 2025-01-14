package com.nextbuy.entity;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class CartEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartId;

	@ColumnDefault("1")
	private Integer cartQnty;

	@ManyToOne
	@JoinColumn(name = "user_id") // Foreign key column for UserEntity
	private UserEntity user;

	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "prod_id", referencedColumnName = "prodId")
	private ProductEntity prod;

	public void increaseCartQntyByOne() {
		this.cartQnty++;
	}
}
