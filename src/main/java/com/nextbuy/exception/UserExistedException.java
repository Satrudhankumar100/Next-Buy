package com.nextbuy.exception;

public class UserExistedException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1718863490877034726L;

	public UserExistedException(String msg){
		super(msg);
	}

}
