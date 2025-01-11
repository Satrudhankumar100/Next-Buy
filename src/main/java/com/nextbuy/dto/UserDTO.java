package com.nextbuy.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserDTO {
	
	@Id
	private Integer userId;
	private String userName;
	private String userGender;
	private String userEmail;
	private String userPwd;
	private String userPhone;

}
