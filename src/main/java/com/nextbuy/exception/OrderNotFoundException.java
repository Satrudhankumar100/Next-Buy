package com.nextbuy.exception;

public class OrderNotFoundException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8648402619836424462L;
	
	
	public OrderNotFoundException(String msg) {
		super(msg);
	}

}
