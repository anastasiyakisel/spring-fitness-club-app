package by.bsu.kisel.exception;

/**
 * This class implements custom resource creation exception .
 * 
 * @author Anastasiya Kisel
 */
public class ResourceCreationException extends Exception {

	private static final long serialVersionUID = 1L;

	public ResourceCreationException() {
		super();
	}

	public ResourceCreationException(String message) {
		super(message);
	}

	public ResourceCreationException(String message, Throwable cause) {
		super(message, cause);
	}

}
