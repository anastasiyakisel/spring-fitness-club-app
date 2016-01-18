package by.bsu.kisel.constants;

/**
 * This class provides the String constants for most commonly encountered errors
 * in the application.
 * 
 * @author Anastasiya Kisel
 */
public class ErrorConstants {
	public static final String LOGGER_DAO_EXC = "Can't execute query!";
	public static final String LOGGER_INCORRECT_INPUT = "Incorrect input!";
	public static final String LOGGER_ENUM_EXC = "No such entity!";
	public static final String LOGGER_VIEW_FAIL = "Can't execute  the view command.";
	public static final String LOGGER_TECHNICAL_ERROR = "Technical exception in connection pool.";
	public static final String LOGGER_CLASS_NOT_FOUND = "No such driver! Impossible to establish connection with DB.";
	public static final String DAO_SQL_EXCEPTION = "Cannot execute query !";
	public static final String LOGGER_INVALID_CREDENTIALS = "Invalid Credentials";

	/**
	 * Empty constructor.
	 */
	private ErrorConstants() {
	}
}
