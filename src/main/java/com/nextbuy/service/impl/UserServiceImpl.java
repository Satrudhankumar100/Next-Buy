package com.nextbuy.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextbuy.dto.AddressDTO;
import com.nextbuy.dto.UserDTO;
import com.nextbuy.entity.AddressEntity;
import com.nextbuy.entity.UserEntity;
import com.nextbuy.exception.AddressNotFoundException;
import com.nextbuy.exception.UserNotFoundException;
import com.nextbuy.repo.AddressRepository;
import com.nextbuy.repo.UserRepository;
import com.nextbuy.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AddressRepository addressRepo;

	@Override
	public void saveUser(UserDTO userDTO) {
		UserEntity entity = new UserEntity();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(userDTO, entity);
		UserEntity save = userRepo.save(entity);

	}

	@Override
	public void editUser(Integer id, UserDTO userDTO) {
		UserEntity entity = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found!!!"));
		try {
			for (Field field : userDTO.getClass().getDeclaredFields()) {
				field.setAccessible(true); // Enable access to private fields
				Object value = field.get(userDTO); // Get the value of the field from userDTO
				if (value != null) { // Only update non-null fields
					Field entityField = entity.getClass().getDeclaredField(field.getName());
					entityField.setAccessible(true); // Enable access to the entity field
					entityField.set(entity, value); // Copy value from DTO to entity
				}
			}
			userRepo.save(entity); // Save the updated entity
		} catch (IllegalAccessException | NoSuchFieldException e) {
			throw new RuntimeException("Error updating user", e);
		}
	}

	@Override
	public void saveUserAddress(Integer id, AddressDTO addressDTO) {
		UserEntity userEntity = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found!!!"));
		AddressEntity entity = new AddressEntity();
		BeanUtils.copyProperties(addressDTO, entity);
		entity.setUser(userEntity);
		addressRepo.save(entity);
	}

	@Override
	public UserDTO getUser(Integer id) {
		UserEntity userEntity = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found!!!"));
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userEntity, userDTO);
		return userDTO;
	}

	@Override
	public UserEntity getUserEntityById(Integer id) {
		return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found!!!"));

	}

	@Override
	public void editUserAddress(Integer id, AddressDTO addressDTO) {
		AddressEntity addressEntity = addressRepo.findById(id)
				.orElseThrow(() -> new AddressNotFoundException("Address Not Found!!"));
		try {
			for (Field field : addressDTO.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object value = field.get(addressDTO);
				if (value != null) {
					Field entityField = addressEntity.getClass().getDeclaredField(field.getName());
					entityField.setAccessible(true); // Enable access to the entity field
					entityField.set(addressEntity, value); // Copy value from DTO to entity
				}
			}
			addressRepo.save(addressEntity);
		} catch (IllegalAccessException | NoSuchFieldException e) {
			throw new RuntimeException("Error updating user", e);
		}
	}

	@Override
	public List<AddressDTO> getAddress(Integer id) {
		UserEntity userEntity = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found!!!"));
		List<AddressDTO> addresses = userEntity.getUserAddr().stream().map(address -> {
			AddressDTO addressDTO = new AddressDTO();
			BeanUtils.copyProperties(address, addressDTO);
			return addressDTO;
		}).toList();
		return addresses;
	}

	@Override
	public void removeAddress(Integer id) {
		AddressEntity addressEntity = addressRepo.findById(id)
				.orElseThrow(() -> new AddressNotFoundException("Address Not Found!!"));
		addressRepo.deleteById(id);
	}
	
	@Override
	public AddressDTO getAddressById(Integer addrId) {
		AddressDTO addressDTO = new AddressDTO();
		AddressEntity addressEntity = addressRepo.findById(addrId).orElseThrow(()->new AddressNotFoundException("address Not found !!"));
		BeanUtils.copyProperties(addressEntity, addressDTO);
		return addressDTO;
	}

}
