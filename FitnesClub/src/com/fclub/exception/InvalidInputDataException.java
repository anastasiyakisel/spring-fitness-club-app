package com.fclub.exception;

public class InvalidInputDataException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public InvalidInputDataException() {
		super();
	}
	
	public InvalidInputDataException(final String message) {
		super(message);
	}
	
	public InvalidInputDataException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
}
