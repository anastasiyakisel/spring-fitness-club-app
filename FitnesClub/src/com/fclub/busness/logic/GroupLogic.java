/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclub.busness.logic;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fclub.exception.FClubInvalidParameterException;
import com.fclub.persistence.model.Group;
import com.fclub.persistence.repository.GroupJpaRepository;
import com.fclub.persistence.repository.RegistrationJpaRepository;
/**
 * This class implements the logic related to group actions.
 * @author Anastasiya Kisel
 */
@Component("GroupLogic")
public class GroupLogic {
	
	private static final Logger LOGGER = Logger.getLogger(GroupLogic.class);
    
    @Autowired
    private GroupJpaRepository groupRepository ;
    
    @Autowired
    private RegistrationJpaRepository registrationRepository ;
	
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
				clientsOfGroup = registrationRepository.countPeopleRegisteredInGroup(group.getId());
				group.setPeopleRegistered(clientsOfGroup);
				groupRepository.save(group);
			} catch (FClubInvalidParameterException e) {
				LOGGER.error("Error updating people registered in group"+e.getMessage());
			}
		});
		return groups;
    }   
        
}
