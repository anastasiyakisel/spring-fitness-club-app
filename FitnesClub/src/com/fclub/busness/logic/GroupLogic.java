/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclub.busness.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fclub.exception.DAOSQLException;
import com.fclub.exception.MyLogicalInvalidParameterException;
import com.fclub.exception.ResourceCreationException;
import com.fclub.persistence.dao.IDAOGroup;
import com.fclub.persistence.dao.IDAORegistration;
import com.fclub.persistence.dao.IDAOSporttype;
import com.fclub.persistence.model.Group;
/**
 * This class implements the logic related to group actions.
 * @author Anastasiya Kisel
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
	 * Updates number of registered attendees in the specified groups.
	 * @param groups - list of groups
	 * @return list of updated groups
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
    public final List<Group> updatePeopleRegistered(final List<Group> groups) 
            throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
        final List<Integer> numbers=new ArrayList<Integer>();
        for (final Group group:groups){
            final int number=registrationDAO.countPeopleRegisteredInGroup(group.getId());
            numbers.add(number);
        }
        int i=0;
        for(final Group group:groups){
            group.setPeopleRegistered(numbers.get(i));
            groupDAO.updatePeopleRegistered(group.getId(), group.getPeopleRegistered());
            ++i;
        }
        return groups;
    }   
        
}
