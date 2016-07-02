package com.fclub.exception;

public class InvalidInputDataException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public InvalidInputDataException() {
		super();
	}
	
	public InvalidInputDataException(String message) {
		super(message);
	}
	
	public InvalidInputDataException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
