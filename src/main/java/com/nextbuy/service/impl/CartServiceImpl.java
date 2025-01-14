package com.nextbuy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextbuy.entity.CartEntity;
import com.nextbuy.entity.ProductEntity;
import com.nextbuy.entity.UserEntity;
import com.nextbuy.exception.CartNotFoundException;
import com.nextbuy.repo.CartRepository;
import com.nextbuy.service.ICartService;
import com.nextbuy.service.IProductService;
import com.nextbuy.service.IUserService;

@Service
public class CartServiceImpl implements ICartService {

	@Autowired
	CartRepository cartRepo;

	@Autowired
	IProductService productService;

	@Autowired
	IUserService userService;

	@Override
	public void addToCart(Integer prodId, Integer userId) {

		// fetching product entity
		ProductEntity productEntity = productService.getProductEntity(prodId);

		// find cart by prodId
		Optional<CartEntity> byProductId = cartRepo.findByProdProdIdAndUserUserId(prodId, userId);
		CartEntity cartEntity;
		if (byProductId.isPresent()) {
			cartEntity = byProductId.get();
			cartEntity.increaseCartQntyByOne();
		} else {
			cartEntity = new CartEntity();
			cartEntity.setProd(productEntity);
			UserEntity userEntity = userService.getUserEntityById(userId);
			cartEntity.setUser(userEntity);
			cartEntity.setCartQnty(1);
		}
		cartRepo.save(cartEntity);

	}

	@Override
	public void removeCart(Integer cartId) {
		CartEntity cartEntity = cartRepo.findById(cartId)
				.orElseThrow(() -> new CartNotFoundException("Cart " + cartId + " not Found!!!"));
		cartRepo.delete(cartEntity);

	}

	@Override
	public int getCartQnty(Integer userId) {
		List<CartEntity> userCarts = cartRepo.findByUserUserId(userId);
		if (userCarts.isEmpty())
			return 0;
		int totalQnty = userCarts.stream().mapToInt(CartEntity::getCartQnty).sum();
		return totalQnty;
	}

	@Override
	public List<CartEntity> getAllCart(Integer userId) {
		List<CartEntity> carts = cartRepo.findByUserUserId(userId);
		return carts;
	}

	@Override
	public void setCartQnty(Integer cartId, Integer qnty) {
		CartEntity cartEntity = cartRepo.findById(cartId)
				.orElseThrow(() -> new CartNotFoundException("cart Not found !!"));
		cartEntity.setCartQnty(qnty);
		cartRepo.save(cartEntity);

	}

}
