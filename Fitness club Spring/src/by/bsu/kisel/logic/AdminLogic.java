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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.dao.IDAORegistration;
import by.bsu.kisel.dao.IDAOSporttype;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.model.Group;
/**
 * This class implements the logic related to admin actions.
 * @author Anastasiya Kisel
 */
@Component("AdminLogic")
@SessionAttributes({ParameterConstants.PARAMETER_ADMIN_GR_SPORTTYPES})
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
	 * Shows to admin groups of the specified user.
	 * @param userId - user's id
	 * @param model - Spring Model object
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
    public final void showToAdminUserGroups(Integer userId, Model model) 
            throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
        List<Group> userGroups = new ArrayList<Group>();
        userGroups=showUserGroups(userId);
        if (userGroups.size()==0){
            model.addAttribute(ParameterConstants.PASSIVE_USER_PARAMETER,
                                 ParameterConstants.PASSIVE_USER_PARAMETER);
        }
        model.addAttribute(ParameterConstants.PARAMETER_ADMIN_GR_SPORTTYPES, userGroups); 
    }
	/**
	 * Shows to admin groups of the specified user.
	 * @param userId
	 * @return the list of user's groups
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
    private List<Group> showUserGroups(int userId) throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
    	List<Integer> groupIds=registrationDAO.showUserGroupIds(userId);
    	List<Group> groupList = new ArrayList<Group>();
    	for (int groupId : groupIds){
    		Group currentGroup = groupDAO.getConcreteGroup(groupId);
   			groupList.add(currentGroup);  
    	}
   		return groupList;
    }
}
