package com.nextbuy.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class CartDTO {
	
	
	private Integer cartId;
	private Integer cartQnty;
	private Integer prodId;

}
