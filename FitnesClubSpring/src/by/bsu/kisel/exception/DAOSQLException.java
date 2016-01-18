/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.exception;

/**
 *
 * @author Kisel Anastasia
 */
public class DAOSQLException extends Exception{
    private Exception _hidden;
    
    public DAOSQLException(String er){
        super(er);
    }
    
    public DAOSQLException(String er, Exception e){
        super(er);
        _hidden=e;
    }

    public Exception getHiddenException(){
        return _hidden;
    }
}
