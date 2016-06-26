package com.fclub.exception;

public class UsernameNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	
	
	public UsernameNotFoundException() {
		super();
	}
	
	public UsernameNotFoundException(String message) {
		super(message);
	}
	
	public UsernameNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
