/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclub.busness.logic;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fclub.exception.FClubInvalidParameterException;
import com.fclub.persistence.dao.GroupJpaRepository;
import com.fclub.persistence.dao.RegistrationJpaRepository;
import com.fclub.persistence.model.Group;
/**
 * This class implements the logic related to group actions.
 * @author Anastasiya Kisel
 */
@Component("GroupLogic")
public class GroupLogic {
	
	private static final Logger LOGGER = Logger.getLogger(GroupLogic.class);
    
    @Autowired
    @Qualifier("DAOJpaGroup")
    private GroupJpaRepository groupDAO ;
    
    @Autowired
    @Qualifier("DAOJpaRegistration")
    private RegistrationJpaRepository registrationDAO ;
	
	/**
	 * Updates number of registered attendees in the specified groups.
	 * @param groups - list of groups
	 * @return list of updated groups
	 * @throws FClubInvalidParameterException
	 */
    public final List<Group> updatePeopleRegistered(final List<Group> groups) {
		groups.forEach(group -> {
			try {
				int clientsOfGroup;
				clientsOfGroup = registrationDAO.countPeopleRegisteredInGroup(group.getId());
				group.setPeopleRegistered(clientsOfGroup);
				groupDAO.save(group);
			} catch (FClubInvalidParameterException e) {
				LOGGER.error("Error updating people registered in group"+e.getMessage());
			}
		});
		return groups;
    }   
        
}
