package com.nextbuy.dto;

import lombok.Data;

@Data
public class AddressDTO {
	
	
	private Integer addId;
	private String country;
	private String state;
	private String city;
	private Integer pincode;

}
