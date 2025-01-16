package com.nextbuy.exception;

public class PaymentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7657069214076773358L;
	
	
	public PaymentNotFoundException(String msg) {
		super(msg);
	}

}
