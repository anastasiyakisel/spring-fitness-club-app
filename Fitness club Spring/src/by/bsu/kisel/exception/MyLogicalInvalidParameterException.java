package by.bsu.kisel.exception;
/**
 * This class implements custom logical exception when specified parameter is incorrect. 
 * @author Anastasiya Kisel
 */
public class MyLogicalInvalidParameterException extends Exception{

	private static final long serialVersionUID = 1L;
	private Exception _hidden;
    /**
     * Constructs new Exception object with String message text specified.
     * @param er - String error text
     */    
    public MyLogicalInvalidParameterException(String er){
        super(er);
    }
    /**
     * Constructs new Exception object with String message text and exception object specified.
     * @param er - String error text
     * @param e - Exception object
     */   
    public MyLogicalInvalidParameterException(String er, Exception e){
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
