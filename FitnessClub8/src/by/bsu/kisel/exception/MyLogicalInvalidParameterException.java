package by.bsu.kisel.exception;

/**
 * This class implements custom logical exception when specified parameter is
 * incorrect.
 * 
 * @author Anastasiya Kisel
 */
public class MyLogicalInvalidParameterException extends Exception {

	private static final long serialVersionUID = 1L;

	public MyLogicalInvalidParameterException() {
		super();
	}

	public MyLogicalInvalidParameterException(String message) {
		super(message);
	}

	public MyLogicalInvalidParameterException(String message, Throwable cause) {
		super(message, cause);
	}

}
