package by.bsu.kisel.exception;

/**
 * This class implements custom Database exception.
 * 
 * @author Anastasiya Kisel
 */
public class DAOSQLException extends Exception {

	private static final long serialVersionUID = 1L;

	public DAOSQLException() {
		super();
	}

	public DAOSQLException(String message) {
		super(message);
	}

	public DAOSQLException(String message, Throwable cause) {
		super(message, cause);
	}

}
