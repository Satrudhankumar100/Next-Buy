package com.nextbuy.service;

import java.util.List;

import com.nextbuy.dto.AddressDTO;
import com.nextbuy.dto.UserDTO;
import com.nextbuy.entity.UserEntity;

public interface IUserService {

	void saveUser(UserDTO userDTO);

	void editUser(Integer id, UserDTO userDTO);

	UserDTO getUser(Integer id);
	
	UserEntity getUserEntityById(Integer id);

	void saveUserAddress(Integer id,AddressDTO addressDTO);

	void editUserAddress(Integer id, AddressDTO addressDTO);

	List<AddressDTO> getAddress(Integer id);

	void removeAddress(Integer id);
	
	

}
