package by.bsu.kisel.exception;
/**
 * This class implements custom logical exception when specified value doesn't exist in the application's enum. 
 * @author Anastasiya Kisel
 */
public class MyLogicalEnumNotPresentException extends Exception{

	private static final long serialVersionUID = 1L;
	private Exception _hidden;
    /**
     * Constructs new Exception object with String message text specified.
     * @param er - String error text
     */
    public MyLogicalEnumNotPresentException(String er){
        super(er);
    }
    /**
     * Constructs new Exception object with String message text and exception object specified.
     * @param er - String error text
     * @param e - Exception object
     */ 
    public MyLogicalEnumNotPresentException(String er, Exception e){
        super(er);
        _hidden=e;
    }
	/**
	 * Provides the exception object.
	 * @return exception object
	 */
    public Exception getHiddenException(){
        return _hidden;
    }
}
