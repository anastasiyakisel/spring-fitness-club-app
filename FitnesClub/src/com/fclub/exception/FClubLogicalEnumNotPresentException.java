package com.fclub.exception;
/**
 * This class implements custom logical exception when specified value doesn't exist in the application's enum. 
 * @author Anastasiya Kisel
 */
public class FClubLogicalEnumNotPresentException extends Exception{

	private static final long serialVersionUID = 1L;

	public FClubLogicalEnumNotPresentException(){
		super();
	}
	
    public FClubLogicalEnumNotPresentException(final String message){
        super(message);
    }

    public FClubLogicalEnumNotPresentException(final String message, final Throwable cause){
        super(message,cause);
    }

}
