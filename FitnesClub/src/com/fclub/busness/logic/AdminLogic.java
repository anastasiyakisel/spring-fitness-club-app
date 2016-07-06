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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fclub.constants.ParameterConstants;
import com.fclub.exception.DAOSQLException;
import com.fclub.exception.MyLogicalInvalidParameterException;
import com.fclub.exception.ResourceCreationException;
import com.fclub.persistence.dao.IDAOGroup;
import com.fclub.persistence.dao.IDAORegistration;
import com.fclub.persistence.dao.IDAOSporttype;
import com.fclub.persistence.model.Group;
/**
 * This class implements the logic related to admin actions.
 * @author Anastasiya Kisel
 */
@Component("AdminLogic")
@SessionAttributes({ParameterConstants.ADMIN_GR_SPORTTYPES})
public class AdminLogic {
	
    @Autowired
    @Qualifier("DAOJpaRegistration")
    private IDAORegistration registrationDAO ;
    
    @Autowired
    @Qualifier("DAOJpaGroup")
    private IDAOGroup groupDAO ;
    
    @Autowired
    @Qualifier("DAOJpaSporttype")
    private IDAOSporttype sporttypeDAO ;
    
    public AdminLogic(){
    	
    }
    
	/**
	 * Shows to admin groups of the specified user.
	 * @param userId - user's id
	 * @param model - Spring Model object
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
    public final void showToAdminUserGroups(final Integer userId, final Model model) 
            throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
        List<Group> userGroups = new ArrayList<Group>();
        userGroups=showUserGroups(userId);
        if (userGroups.size()==0){
            model.addAttribute(ParameterConstants.PASSIVE_USER,
                                 ParameterConstants.PASSIVE_USER);
        }
        model.addAttribute(ParameterConstants.ADMIN_GR_SPORTTYPES, userGroups); 
    }
	/**
	 * Shows to admin groups of the specified user.
	 * @param userId
	 * @return the list of user's groups
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
    private List<Group> showUserGroups(final int userId) throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
    	final List<Integer> groupIds=registrationDAO.showUserGroupIds(userId);
    	final List<Group> groupList = new ArrayList<Group>();
    	for (final int groupId : groupIds){
    		final Group currentGroup = groupDAO.getConcreteGroup(groupId);
   			groupList.add(currentGroup);  
    	}
   		return groupList;
    }
}
