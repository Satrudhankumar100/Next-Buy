package com.nextbuy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextbuy.dto.CartDTO;
import com.nextbuy.entity.CartEntity;
import com.nextbuy.service.ICartService;

@RestController
@RequestMapping("/next-buy/api/cart")
@CrossOrigin
public class CartController {

	@Autowired
	ICartService cartService;

	@PostMapping("/add-cart/{user-id}")
	public ResponseEntity<String> addToCart(@RequestBody CartDTO cartDTO, @PathVariable("user-id") String userId) {
		cartService.addToCart(cartDTO.getProdId(), Integer.parseInt(userId));
		return new ResponseEntity<String>("added successfully ", HttpStatus.OK);

	}
	
	@DeleteMapping("/remove-cart/{cart-id}")
	public ResponseEntity<String> removeCart(@PathVariable("cart-id") String cartId){
		cartService.removeCart(Integer.parseInt(cartId));
		return new ResponseEntity<String>("Deleted Successfully",HttpStatus.OK);
	}
	
	@GetMapping("/qnty-cart/{id}")
	public ResponseEntity<Integer> getCartQnty(@PathVariable("id")String userId){
		int cartQnty = cartService.getCartQnty(Integer.parseInt(userId));
		return new ResponseEntity<Integer>(cartQnty,HttpStatus.OK);
		 
	 }
	
	
	@GetMapping("/find-all-cart/{id}")
	public ResponseEntity< List<CartEntity>> getAllCarts(@PathVariable("id")String userId){
		 List<CartEntity> carts = cartService.getAllCart(Integer.parseInt(userId));
		return new ResponseEntity< List<CartEntity>>(carts,HttpStatus.OK);
		 
	 }
	
	
	@PostMapping("/update-cart-qnty")
	public ResponseEntity<String> setCartQnty(@RequestBody CartDTO cartDTO){
		cartService.setCartQnty(cartDTO.getCartId(), cartDTO.getCartQnty());
		return new ResponseEntity<String>("quantity update",HttpStatus.OK);
	}

}
