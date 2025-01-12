package com.nextbuy.exception;

public class UserNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1549186372399282918L;

	public UserNotFoundException(String msg){
		super(msg);
	}
	

}
