package by.bsu.kisel.constants;

/**
 * This class provides the String constants for most commonly encountered log
 * messages in the application.
 * 
 * @author Anastasiya Kisel
 */
public class MessageConstants {

	public static final String LOGIN_ERROR_MESSAGE = "Incorrect login or password";
	public static final String SERVLET_EXCEPTION_ERROR_MESSAGE = "ServletException: Servlet";
	public static final String IO_EXCEPTION_ERROR_MESSAGE = "IOException: input or output error";
	public static final String CLASS_NOT_FOUND_ERROR_MESSAGE = "Class not found";
	public static final String SQL_ERROR_MESSAGE = "Something wrong with SQL";
	public static final String PARSE_ERROR_MESSAGE = "Exception while parsing";
	public static final String SAX_ERROR_MESSAGE = "Sax exception while parsing";
	public static final String RADIOBUTTON_ERROR_MESSAGE = "Radio button wasn't checked";
	public static final String INCORRECT_PARAMETER_ERROR_MESSAGE = "Incorrect parameter of the entity";
	public static final String DAO_EXCEPTION = "SQL Exception";
	public static final String NO_SUCH_ENUM_VALUE = "No such enum value";
	public static final String DAO_ERROR_MESSAGE = "Can't close connection !";
	public static final String ERROR_MESSAGE_POOL_IS_EMPTY = "Too many connections at time.";

	/**
	 * Empty constructor.
	 */
	private MessageConstants() {
	}
}
