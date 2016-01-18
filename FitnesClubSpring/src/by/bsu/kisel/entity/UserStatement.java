/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.entity;

import java.io.Serializable;

import by.bsu.kisel.exception.MyLogicalInvalidParameterException;

/**
 *
 * @author Anastasia Kisel
 */
public class UserStatement implements Serializable{
    private User user;
    private Statement statement;
    /**
     * constructor
     */
    public UserStatement(){
        this.user=new User();
        this.statement=new Statement();
    }
    
    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) throws MyLogicalInvalidParameterException {
        if (statement==null){
            throw new MyLogicalInvalidParameterException("Statement can't be null !");
        }
        this.statement = statement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) throws MyLogicalInvalidParameterException {
        if (user==null){
            throw new MyLogicalInvalidParameterException("User can't be null !");
        }
        this.user = user;
    }
    
    
}
