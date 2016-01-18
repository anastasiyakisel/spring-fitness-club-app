/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.exception;

/**
 *
 * @author Anastasia Kisel
 */
public class ResourceCreationException extends Exception{
    private Exception _hidden;
    
    public ResourceCreationException(String er){
        super(er);
    }
    
    public ResourceCreationException(String er, Exception e){
        super(er);
        _hidden=e;
    }

    public Exception getHiddenException(){
        return _hidden;
    }
}
