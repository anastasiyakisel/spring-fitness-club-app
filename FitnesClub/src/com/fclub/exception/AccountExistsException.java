package com.fclub.exception;

public class AccountExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public AccountExistsException() {
		super();
	}

	public AccountExistsException(final String message) {
		super(message);
	}

	public AccountExistsException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
