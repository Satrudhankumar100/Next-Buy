package com.nextbuy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nextbuy.dto.OrderDTO;
import com.nextbuy.service.IOrderService;

@RestController
@CrossOrigin
@RequestMapping("/next-buy/api/order")
public class OrderController {
	
	
	@Autowired
	IOrderService orderService;
	
	@PostMapping("/create-order")
	public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO){
		orderService.generateOrder(orderDTO.getUserId(), orderDTO.getPayId(), orderDTO.getAddrId());
		return new ResponseEntity<String> ("order craeated",HttpStatus.CREATED);
	}
	
	@PostMapping("/change-order-status")
	public ResponseEntity<String> changeOrderStatus(@RequestParam  Integer ordId) {
		boolean status = orderService.changeOrderStatus(ordId);
		return new ResponseEntity<String>("deliverd successfully.",HttpStatus.OK);
		
	}
	
	
	
	
	
	

	
	
	

}
