package com.nextbuy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class OrderedProductEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ordProdId;
	private Integer ordQnty;	
	private String title;
	private String category;
	private String brand;
	private String name;
	private String rating;
	private String image;
	private String description;
	
	

}
