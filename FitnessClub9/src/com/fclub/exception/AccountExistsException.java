package com.fclub.exception;

public class AccountExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public AccountExistsException() {
		super();
	}

	public AccountExistsException(String message) {
		super(message);
	}

	public AccountExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
