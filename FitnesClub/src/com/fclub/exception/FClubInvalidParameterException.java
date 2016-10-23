package com.fclub.exception;

/**
 * This class implements custom logical exception when specified parameter is
 * incorrect.
 * 
 * @author Anastasiya Kisel
 */
public class FClubInvalidParameterException extends Exception {

	private static final long serialVersionUID = 1L;

	public FClubInvalidParameterException() {
		super();
	}

	public FClubInvalidParameterException(final String message) {
		super(message);
	}

	public FClubInvalidParameterException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
