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
public class GroupSporttype implements Serializable {
    private Group group;
    private Sporttype sporttype;
    /**
     * constructor
     */
    public GroupSporttype(){
        this.group=new Group();
        this.sporttype=new Sporttype();
    }
    
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) throws MyLogicalInvalidParameterException {
        if (group==null)
            throw new MyLogicalInvalidParameterException("Group can't be null !");
        this.group = group;
    }

    public Sporttype getSporttype() {
        return sporttype;
    }

    public void setSporttype(Sporttype sporttype) throws MyLogicalInvalidParameterException {
        if (sporttype==null)
            throw new MyLogicalInvalidParameterException("Sporttype can't be null !");
        this.sporttype = sporttype;
    }
    
    
}
