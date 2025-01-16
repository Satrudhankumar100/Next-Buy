package com.nextbuy.service;

import com.nextbuy.dto.PaymentDTO;
import com.nextbuy.entity.PaymentEntity;

public interface IPaymentService {
	
	PaymentDTO totalPaymentAmount(Integer userId);
	
	Integer paymentStatus(String tnxId,Integer userId);
	
	PaymentEntity getPaymentEntity(Integer payId);
	
	

}
