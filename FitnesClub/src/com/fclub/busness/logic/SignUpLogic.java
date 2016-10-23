/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclub.busness.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fclub.persistence.dao.GroupJpaRepository;
import com.fclub.persistence.model.Group;

/**
 * This class implements the logic related to sign up actions.
 * @author Anastasiya Kisel
 */
@Component("SignUpLogic")
public class SignUpLogic {
		
    @Autowired
    @Qualifier("DAOJpaGroup")
    private GroupJpaRepository groupDAO ;
	
	/**
	 * Provides the ids of groups where it is possible to signup among the specified group ids.
	 * @param ids - array of random groups' ids
	 * @return array of free groups
	 */
	public final List<Group> getAvailableGroups(final List<Integer> ids) {
		return groupDAO.findByIdIn(ids).stream().filter(group -> group.getCapacity() - group.getPeopleRegistered() > 0)
				.collect(Collectors.toList());
	}  
}
