/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.dao.IDAORegistration;
import by.bsu.kisel.dao.IDAOSporttype;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.model.Group;
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
    public final List<Group> updatePeopleRegistered(List<Group> groups) 
            throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
        List<Integer> numbers=new ArrayList<Integer>();
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
