package com.nextbuy.exception;

public class ProductNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1549186372399282918L;

	public ProductNotFoundException(String msg){
		super(msg);
	}
	

}
