package com.nextbuy.exception;

public class AddressNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5313673387428782577L;
	
	public AddressNotFoundException(String msg) {
		super(msg);
	}

}
