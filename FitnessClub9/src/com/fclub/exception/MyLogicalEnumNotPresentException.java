package com.fclub.exception;
/**
 * This class implements custom logical exception when specified value doesn't exist in the application's enum. 
 * @author Anastasiya Kisel
 */
public class MyLogicalEnumNotPresentException extends Exception{

	private static final long serialVersionUID = 1L;

	public MyLogicalEnumNotPresentException(){
		super();
	}
	
    public MyLogicalEnumNotPresentException(String message){
        super(message);
    }

    public MyLogicalEnumNotPresentException(String message, Throwable cause){
        super(message,cause);
    }

}
