package com.nextbuy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextbuy.dto.AddressDTO;
import com.nextbuy.dto.UserDTO;
import com.nextbuy.service.IUserService;

@RestController
@CrossOrigin
@RequestMapping("next-buy/api/user")
public class UserController {

	@Autowired
	IUserService userService;

	@PostMapping("/save-user")
	public ResponseEntity<String> saveUser(@RequestBody UserDTO UserDTO) {
		userService.saveUser(UserDTO);
		return new ResponseEntity<>("User Created Successfully..", HttpStatus.OK);
	}

	@PatchMapping("/edit-user/{id}")
	public ResponseEntity<String> editUser(@PathVariable("id") String id, @RequestBody UserDTO userDTO) {
		userService.editUser(Integer.parseInt(id), userDTO);
		return new ResponseEntity<String>("Updated successfully", HttpStatus.OK);

	}

	@GetMapping("/find-user/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("id") String id) {
		UserDTO user = userService.getUser(Integer.parseInt(id));
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}
	
	
	

	@PostMapping("/save-addr")
	public ResponseEntity<String> saveUserAddress(@RequestBody AddressDTO addressDTO) {
		userService.saveUserAddress(addressDTO);
		return new ResponseEntity<>("User Created Successfully..", HttpStatus.OK);

	}

	@PatchMapping("/edit-addr/{id}")
	public ResponseEntity<String> editUserAddress(@PathVariable("id") String id, @RequestBody AddressDTO addressDTO) {
		userService.editUserAddress(Integer.parseInt(id), addressDTO);
		return new ResponseEntity<String>("Updated successfully", HttpStatus.OK);
	}

	@GetMapping("/find-addr/{id}")
	public ResponseEntity<AddressDTO> getAddress(@PathVariable("id") String id) {
		AddressDTO address = userService.getAddress(Integer.parseInt(id));
		return new ResponseEntity<AddressDTO>(address, HttpStatus.OK);
	}

	@DeleteMapping("/delete-addr/{id}")
	public ResponseEntity<String> removeAddress(@PathVariable("id") String id) {
		userService.removeAddress(Integer.parseInt(id));
		return new ResponseEntity<String>("deleted Successfully", HttpStatus.OK);
	}

}
