package com.nextbuy.service;

public interface IOrderService {
	
	
	void generateOrder(Integer userId,Integer payId,Integer addrId);
	
	//List<OrdersEntity> getAllOrders(Integer userId);
	
	//Optional<OrdersEntity> getOrdersById(Integer ordId);
	
	boolean changeOrderStatus(Integer ordId);
	
	

	

}
