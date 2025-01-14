package com.nextbuy.service;

import java.util.List;

import com.nextbuy.entity.CartEntity;

public interface ICartService {
	
	void addToCart(Integer prodId,Integer userId);
	
	void removeCart(Integer cartId);
	
	int getCartQnty(Integer userId);
	
	void setCartQnty(Integer cartId,Integer qnty);
	
	
	List<CartEntity> getAllCart(Integer userId);
	
	
	

}
