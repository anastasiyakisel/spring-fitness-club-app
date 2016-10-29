/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclub.busness.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fclub.persistence.model.Group;
import com.fclub.persistence.repository.GroupJpaRepository;

/**
 * This class implements the logic related to sign up actions.
 * @author Anastasiya Kisel
 */
@Component("SignUpLogic")
public class SignUpLogic {
		
    @Autowired
    private GroupJpaRepository groupRepository ;
	
	/**
	 * Provides the ids of groups where it is possible to signup among the specified group ids.
	 * @param ids - array of random groups' ids
	 * @return array of free groups
	 */
	public final List<Group> getAvailableGroups(final List<Long> ids) {
		return groupRepository.findByIdIn(ids).stream().filter(group -> group.getCapacity() - group.getPeopleRegistered() > 0)
				.collect(Collectors.toList());
	}  
}
