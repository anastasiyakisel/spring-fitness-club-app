/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.logic;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.controller.Controller;
import by.bsu.kisel.dao.IDAOSporttype;
import by.bsu.kisel.entity.GroupSporttype;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

/**
 *
 * @author Anastasia Kisel
 */
public class AdminLogic {
	
	/**
     * get groups of the concrete user
     * @param request 
     * @return
     * @throws ResourceCreationException 
     */
    public static final void showToAdminUserGroups(HttpServletRequest request) 
            throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
        String page=null;
        IDAOSporttype sporttypeDAO = (IDAOSporttype) Controller.webContext.getBean("DAOJdbcSporttype");
        Integer userId=(Integer) request.getSession().getAttribute(ParameterConstants.PARAMETER_USER_ID);
        ArrayList<GroupSporttype> userGroupSporttypes = new ArrayList<GroupSporttype>();
        userGroupSporttypes=sporttypeDAO.showuserGroupSporttypes(userId);
        if (userGroupSporttypes.size()==0){
            request.setAttribute(ParameterConstants.PASSIVE_USER_PARAMETER,
                                 ParameterConstants.PASSIVE_USER_PARAMETER);
        }
        request.getSession().setAttribute(ParameterConstants.PARAMETER_ADMIN_GR_SPORTTYPES, userGroupSporttypes); 
    }
}
