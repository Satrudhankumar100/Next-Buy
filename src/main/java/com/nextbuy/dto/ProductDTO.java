package com.nextbuy.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ProductDTO {
	
	
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
	private String description;

}
