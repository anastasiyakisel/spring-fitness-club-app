/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.exception;

/**
 *
 * @author Kisel Anastasia
 */
public class MyLogicalEnumNotPresentException extends Exception{
    private Exception _hidden;
    
    public MyLogicalEnumNotPresentException(String er){
        super(er);
    }
    
    public MyLogicalEnumNotPresentException(String er, Exception e){
        super(er);
        _hidden=e;
    }

    public Exception getHiddenException(){
        return _hidden;
    }
}