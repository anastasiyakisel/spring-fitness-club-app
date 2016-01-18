/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.dao.IDAORegistration;
import by.bsu.kisel.dao.IDAOSporttype;
import by.bsu.kisel.entity.Group;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

/**
 *
 * @author Anastasia Kisel
 */
@Component("GroupLogic")
public class GroupLogic {
	
    @Autowired
    @Qualifier("DAOJpaSporttype")
    private IDAOSporttype sporttypeDAO ;
    
    @Autowired
    @Qualifier("DAOJpaGroup")
    private IDAOGroup groupDAO ;
    
    @Autowired
    @Qualifier("DAOJpaRegistration")
    private IDAORegistration registrationDAO ;
	
    /**
     * update people registered in the concrete groups
     * @param groups  
     * @return groups
     * @throws ResourceCreationException 
     */
    public final ArrayList<Group> updatePeopleRegistered( ArrayList<Group> groups) 
            throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
        ArrayList<Integer> numbers=new ArrayList<Integer>();
        for (Group group:groups){
            int number=registrationDAO.countPeopleRegisteredInGroup(group.getId());
            numbers.add(number);
        }
        int i=0;
        for(Group group:groups){
            group.setPeopleRegistered(numbers.get(i));
            groupDAO.updatePeopleRegistered(group.getId(), group.getPeopleRegistered());
            ++i;
        }
        return groups;
    }   
        
}
