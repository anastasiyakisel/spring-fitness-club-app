/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.entity;

import java.io.Serializable;

import by.bsu.kisel.exception.MyLogicalInvalidParameterException;

/**
 *
 * @author Kisel Anastasia
 */
public abstract class AbstractEntity implements Serializable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) throws MyLogicalInvalidParameterException {
        if (id<=0)
            throw new MyLogicalInvalidParameterException("ID of entity can't be null !");
        this.id = id;
    }
    
    @Override
    public String toString(){
        return getClass().getName()+" [№ = "+id+" ] ";
    }
}
