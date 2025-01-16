package com.nextbuy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextbuy.dto.PaymentDTO;
import com.nextbuy.service.IPaymentService;

@RestController
@CrossOrigin
@RequestMapping("/next-buy/api/payment")
public class PaymentController {
	
	
	@Autowired
	IPaymentService paymentService;
	
	@GetMapping("get-total-prod/{user-id}")
	public ResponseEntity<PaymentDTO> getTotalProduct(@PathVariable("user-id")String userId ){
	PaymentDTO totalCost = paymentService.totalPaymentAmount(Integer.parseInt(userId));
		return new ResponseEntity<PaymentDTO>(totalCost, HttpStatus.OK);
	}
	
	@PostMapping("/save-payment/{tnxId}/{user-id}")
	public ResponseEntity<Integer> paymentStatus(@PathVariable("tnxId")  String tnxId,@PathVariable("user-id") String userId){
		Integer paymentId = paymentService.paymentStatus(tnxId, Integer.parseInt(userId));
		return new ResponseEntity<Integer>(paymentId,HttpStatus.OK);
		
	}
	
	
	

}
