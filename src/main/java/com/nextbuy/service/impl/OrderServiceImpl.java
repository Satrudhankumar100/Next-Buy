package com.nextbuy.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextbuy.dto.AddressDTO;
import com.nextbuy.entity.CartEntity;
import com.nextbuy.entity.OrderedProductEntity;
import com.nextbuy.entity.OrdersEntity;
import com.nextbuy.entity.PaymentEntity;
import com.nextbuy.entity.UserEntity;
import com.nextbuy.exception.OrderNotFoundException;
import com.nextbuy.repo.OrdersRepository;
import com.nextbuy.service.ICartService;
import com.nextbuy.service.IOrderService;
import com.nextbuy.service.IPaymentService;
import com.nextbuy.service.IUserService;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrdersRepository ordersRepo;

	@Autowired
	private ICartService cartService;

	@Autowired
	private IPaymentService paymentService;

	@Autowired
	private IUserService userService;

	@Override
	public void generateOrder(Integer userId, Integer payId, Integer addrId) {

		// checking payment success or not
		PaymentEntity paymentEntity = paymentService.getPaymentEntity(payId);

		UserEntity user = userService.getUserEntityById(userId);
		
		
		// fetching all carts with product
		List<CartEntity> carts = cartService.getAllCart(userId);

		//retreving product with qnty
		List<OrderedProductEntity> orderedProducts = carts.stream().map(cart -> {
			OrderedProductEntity orderedProductEntity = new OrderedProductEntity();
			BeanUtils.copyProperties(cart.getProd(), orderedProductEntity);
			orderedProductEntity.setOrdQnty(cart.getCartQnty());
			return orderedProductEntity;
		}).toList();

		
		//remove product from cart database
		carts.stream().forEach(cart -> cartService.removeCart(cart.getCartId()));

		
		AddressDTO addressDTO = userService.getAddressById(addrId);
		OrdersEntity ordersEntity = new OrdersEntity();
		ordersEntity.setOrdAddress(addressDTO.toString());
		ordersEntity.setOrdDeliveryDate(LocalDateTime.now().plusDays(7));
		ordersEntity.setOrderedProducts(orderedProducts);
		ordersEntity.setPayment(paymentEntity);
		ordersEntity.setUser(user);
		ordersRepo.save(ordersEntity);

	}
	
	
	
	@Override
	public boolean changeOrderStatus(Integer ordId) {
		OrdersEntity ordersEntity = ordersRepo.findById(ordId).orElseThrow(()->new OrderNotFoundException("Order Not Found"));
		boolean status=ordersEntity.isOrdStatus();
		ordersEntity.setOrdStatus(true);
		ordersRepo.save(ordersEntity);
		return !status;
	}
	
	
	

}
