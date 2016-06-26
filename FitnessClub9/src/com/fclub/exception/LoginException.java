package com.fclub.exception;
/**
 * This class represents 
 * @author Anastasiya Kisel
 *
 */
public class LoginException extends Exception {

	private static final long serialVersionUID = 1L;
	private String loginName;

	public LoginException() {
		super();
	}

	public LoginException(String message, String loginName) {
		super(message);
		this.loginName = loginName;
	}

	public LoginException(String message, String loginName, Throwable cause) {
		super(message, cause);
		this.loginName = loginName;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public String getMessage() {
		return super.getMessage() + " for user with login name  :" + loginName;
	}

	public String getLoginName() {
		return loginName;
	}
}
