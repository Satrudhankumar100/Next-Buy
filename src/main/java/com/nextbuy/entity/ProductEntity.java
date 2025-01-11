package com.nextbuy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ProductEntity {
	@Id
	private Integer prodId;
	private Double prodPrice;
	private Integer prodStock;
	private String title;
	private String category;
	private String brand;
	private String name;
	private String rating;
	private String image;
	private String thumbnail;
	private String description;
	

}
