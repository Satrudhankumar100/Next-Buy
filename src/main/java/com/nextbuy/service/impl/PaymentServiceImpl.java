package com.nextbuy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextbuy.dto.PaymentDTO;
import com.nextbuy.entity.CartEntity;
import com.nextbuy.entity.PaymentEntity;
import com.nextbuy.entity.ProductEntity;
import com.nextbuy.entity.UserEntity;
import com.nextbuy.exception.PaymentNotFoundException;
import com.nextbuy.repo.PaymentRepository;
import com.nextbuy.service.ICartService;
import com.nextbuy.service.IPaymentService;
import com.nextbuy.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImpl implements IPaymentService{
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private ICartService cartService;
	
	@Autowired
	private IUserService userService;
	
	
	
	@Override
	public PaymentDTO totalPaymentAmount(Integer userId) {
		
	//	List<PaymentEntity> entity = paymentRepo.findByUserUserId(userId);
		List<CartEntity> cartsProduct = cartService.getAllCart(userId);
		PaymentDTO paymentDTO = new PaymentDTO();
		double prodCost=0;
		double gstTax=0;
		for(CartEntity cart:cartsProduct) {
			ProductEntity prod = cart.getProd();
			prodCost+=(prod.getProdPrice()*cart.getCartQnty());
		}
		gstTax=(prodCost*18.0)/100.0;
		double totalCost=prodCost+gstTax;
		paymentDTO.setProductAmount(prodCost);
		paymentDTO.setGstTax(gstTax);
		paymentDTO.setTotalAmount(totalCost);
		log.info(String.valueOf(paymentDTO));
	
		
		
		return paymentDTO;
	}
	
	
	@Override
	public Integer paymentStatus(String tnxId, Integer userId) {
		PaymentDTO totalPaymentAmount = totalPaymentAmount(userId);
		PaymentEntity paymentEntity = new PaymentEntity();
		BeanUtils.copyProperties(totalPaymentAmount, paymentEntity);
		paymentEntity.setPayTnxId(tnxId);
		UserEntity user = userService.getUserEntityById(userId);
		paymentEntity.setUser(user);
		paymentEntity.setPayStatus(true);
		paymentEntity= paymentRepo.save(paymentEntity);
		return paymentEntity.getPayId();
	}
	
	
	@Override
	public PaymentEntity getPaymentEntity(Integer payId) {
		return paymentRepo.findById(payId).orElseThrow(()->new PaymentNotFoundException("Payment not found!!"));
	}

}
