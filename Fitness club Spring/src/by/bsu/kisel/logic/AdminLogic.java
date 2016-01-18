/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.logic;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.controller.Controller;
import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.dao.IDAORegistration;
import by.bsu.kisel.dao.IDAOSporttype;
import by.bsu.kisel.entity.Group;
import by.bsu.kisel.entity.Sporttype;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

/**
 *
 * @author Anastasia Kisel
 */
@Component("AdminLogic")
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
	/**
     * get groups of the concrete user
     * @param request 
     * @return
     * @throws ResourceCreationException 
     */
    public final void showToAdminUserGroups(HttpServletRequest request) 
            throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
        String page=null;
        Integer userId=(Integer) request.getSession().getAttribute(ParameterConstants.PARAMETER_USER_ID);
        ArrayList<Group> userGroups = new ArrayList<Group>();
        userGroups=showUserGroups(userId);
        if (userGroups.size()==0){
            request.setAttribute(ParameterConstants.PASSIVE_USER_PARAMETER,
                                 ParameterConstants.PASSIVE_USER_PARAMETER);
        }
        request.getSession().setAttribute(ParameterConstants.PARAMETER_ADMIN_GR_SPORTTYPES, userGroups); 
    }
    
    private ArrayList<Group> showUserGroups(int userId) throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
    	ArrayList<Integer> groupIds=registrationDAO.showUserGroupIds(userId);
    	ArrayList<Group> groupList = new ArrayList<Group>();
    	for (int groupId : groupIds){
    		Group currentGroup = groupDAO.getConcreteGroup(groupId);
   			groupList.add(currentGroup);  
    	}
   		return groupList;
    }
}
