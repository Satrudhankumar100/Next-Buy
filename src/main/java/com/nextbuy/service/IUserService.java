package com.nextbuy.service;

import com.nextbuy.dto.AddressDTO;
import com.nextbuy.dto.UserDTO;

public interface IUserService {

	void saveUser(UserDTO userDTO);

	void editUser(Integer id, UserDTO userDTO);

	UserDTO getUser(Integer id);

	void saveUserAddress(AddressDTO addressDTO);

	void editUserAddress(Integer id, AddressDTO addressDTO);

	AddressDTO getAddress(Integer id);

	void removeAddress(Integer id);

}
