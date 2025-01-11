package com.nextbuy.service.impl;

import java.lang.reflect.Field;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextbuy.dto.AddressDTO;
import com.nextbuy.dto.UserDTO;
import com.nextbuy.entity.AddressEntity;
import com.nextbuy.entity.UserEntity;
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
		Optional<UserEntity> byId = userRepo.findById(id);
		if (byId.isPresent()) {
			UserEntity entity = byId.get();
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
				log.warn(entity.toString());
			} catch (IllegalAccessException | NoSuchFieldException e) {
				throw new RuntimeException("Error updating user", e);
			}
		} else {
			throw new RuntimeException("User with ID " + id + " not found");
		}
	}

	@Override
	public void saveUserAddress(AddressDTO addressDTO) {
		ModelMapper modelMapper = new ModelMapper();
		AddressEntity entity = new AddressEntity();
		modelMapper.map(addressDTO, entity);
		addressRepo.save(entity);

	}

	@Override
	public UserDTO getUser(Integer id) {
		Optional<UserEntity> byId = userRepo.findById(id);
		if(byId.isPresent()){
			UserEntity entity = byId.get();
			UserDTO userDTO = new UserDTO();
			
			BeanUtils.copyProperties(entity,userDTO );
			return userDTO;
		}
		return null;
	}
	@Override
	public void editUserAddress(Integer id, AddressDTO addressDTO) {
		Optional<AddressEntity> byId = addressRepo.findById(id);
		if (byId.isPresent()) {
			AddressEntity entity = byId.get();

			try {

				for (Field field : addressDTO.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					Object value = field.get(addressDTO);
					if (value != null) {
						Field entityField = entity.getClass().getDeclaredField(field.getName());
						entityField.setAccessible(true); // Enable access to the entity field
						entityField.set(entity, value); // Copy value from DTO to entity
					}

				}
				addressRepo.save(entity);
				log.info(entity.toString());

			} catch (IllegalAccessException | NoSuchFieldException e) {
				throw new RuntimeException("Error updating user", e);
			}
		} else {
			throw new RuntimeException("User with ID " + id + " not found");
		}
	}
	
	
	@Override
	public AddressDTO getAddress(Integer id) {
		Optional<AddressEntity> byId = addressRepo.findById(id);
		if(byId.isPresent()) {
			AddressDTO addressDTO = new AddressDTO();
			AddressEntity entity = byId.get();
			BeanUtils.copyProperties(entity,addressDTO );
			return addressDTO;
		}
		return null;
	}
	
	@Override
	public void removeAddress(Integer id) {
	Optional<AddressEntity> byId = addressRepo.findById(id);
	if(byId.isPresent())
		addressRepo.deleteById(id);
	}
	

}
