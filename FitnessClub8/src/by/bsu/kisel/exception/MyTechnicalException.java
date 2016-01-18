package by.bsu.kisel.exception;
/**
 * This class implements custom technical exception when there are some logical/technical errors. 
 * 
 * @author Anastasiya Kisel
 */
public class MyTechnicalException extends Exception{

	private static final long serialVersionUID = 1L;

    public MyTechnicalException(){
        super();
    }
	
    public MyTechnicalException(String message){
        super(message);
    }
    
    public MyTechnicalException(String message, Throwable cause){
    	super(message, cause);
    }

}
