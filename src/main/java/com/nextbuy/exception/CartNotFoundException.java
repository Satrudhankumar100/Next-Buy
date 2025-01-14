package com.nextbuy.exception;

public class CartNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8459926753929629585L;
	
	public CartNotFoundException(String msg){
		super(msg);
	}

}
