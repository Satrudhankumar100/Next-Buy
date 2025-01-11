package com.nextbuy.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class AddressDTO {
	
	@Id
	private Integer addId;
	private String country;
	private String state;
	private String city;
	private Integer pincode;

}
