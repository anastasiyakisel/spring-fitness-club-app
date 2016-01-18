/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.bsu.kisel.constants.LoggerConstants;
import by.bsu.kisel.constants.PageConstants;
import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.controller.Controller;
import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.dao.IDAORegistration;
import by.bsu.kisel.dao.IDAOStatement;
import by.bsu.kisel.dao.IDAOUser;
import by.bsu.kisel.entity.Group;
import by.bsu.kisel.entity.Statement;
import by.bsu.kisel.entity.User;
import by.bsu.kisel.enums.Entities;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.logic.AdminLogic;
import by.bsu.kisel.logic.GroupLogic;
import by.bsu.kisel.logic.StatementLogic;
import by.bsu.kisel.logic.ViewLogic;
import by.bsu.kisel.manager.MessageManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anastasia Kisel
 */
@Component("DeleteCommand")
public class DeleteCommand implements Command {

    private Logger log = Logger.getLogger(DeleteCommand.class);
    @Autowired
    @Qualifier("DAOJpaGroup")
    private IDAOGroup groupDAO ;
    
    @Autowired
    @Qualifier("DAOJpaUser")
    private IDAOUser userDAO ;
    
    @Autowired
    @Qualifier("DAOJpaRegistration")
    private IDAORegistration registrationDAO ;
    
    @Autowired
    @Qualifier("DAOJpaStatement")
    private IDAOStatement statementDAO ;
    //Autowiring Logic classes
    @Autowired
    @Qualifier("GroupLogic")
    private GroupLogic groupLogic ;
    
    @Autowired
    @Qualifier("StatementLogic")
    private StatementLogic statementLogic ;
    
    @Autowired
    @Qualifier("ViewLogic")
    private ViewLogic viewLogic ;
    
    @Autowired
    @Qualifier("AdminLogic")
    private AdminLogic adminLogic ;
    
    @Override
    public String execute(HttpServletRequest request) throws ResourceCreationException {
        
        switch (Entities.valueOf(request.getParameter(ParameterConstants.PARAMETER_ENTITY).toUpperCase())){
            case GROUPS:
                return userWantsToDeleteFromGroup(request);
            case USER:
                return adminWantsToDeleteUserFromGroup(request);
            case ADMIN:
                return deleteUserFromTrainings(request);
            default:
		request.setAttribute(ParameterConstants.PARAMETER_ERROR, 
                        Controller.MSG_MANAGER.getProperty(MessageManager.NO_SUCH_ENUM_VALUE));
        log.error(LoggerConstants.LOGGER_ENUM_EXC);
		return PageConstants.ERROR_PAGE_PATH;
        }
    }
    
    /**
     * method is used when admin wants to delete users from group 
     * @param request
     * @throws ResourceCreationException 
     */
    private String adminWantsToDeleteUserFromGroup(HttpServletRequest request) throws ResourceCreationException{
        String page=null;
        String [] userIdsStr=null;
        Integer[] userIds;
        userIdsStr=request.getParameterValues(ParameterConstants.USER_SELECT);
        
        if (userIdsStr!=null){
            userIds=new Integer[userIdsStr.length];
            for (int idx=0; idx< userIdsStr.length; idx++){
                userIds[idx]=Integer.parseInt(userIdsStr[idx].trim());
            }
            String numberGroupStr=request.getParameter(ParameterConstants.GROUP_NUMBER);
            int numberGroup=Integer.parseInt(numberGroupStr.trim());
            
            try {    
            	registrationDAO.deleteUsersFromGroup(numberGroup, userIds);
                ArrayList<Group> allGroups=groupDAO.adminShowAllGroups();
                allGroups=groupLogic.updatePeopleRegistered(allGroups);
                request.getSession().setAttribute(ParameterConstants.PARAMETER_GR_SPORTTYPES, allGroups);
                ArrayList<User> usersOfGroup=registrationDAO.getUsersFromGroup(numberGroup);
                viewLogic.showUserStatements(usersOfGroup, request);                
                log.info(LoggerConstants.LOGGER_ADMIN_DELETE_USERS+ userIdsStr +LoggerConstants.LOGGER_FROM_GROUP+numberGroup);
            } catch (MyLogicalInvalidParameterException ex) {
                    page=PageConstants.ERROR_PAGE_PATH;
                    return page;
            } catch (DAOSQLException ex) {
                    page=PageConstants.ERROR_PAGE_PATH;
                    return page;}
        }
        page=PageConstants.ADMIN_PAGE_PATH;
        return page;
    }
    
    /**
     * method is used when user wants to delete from groups 
     * @param request
     * @throws ResourceCreationException 
     */
    private String userWantsToDeleteFromGroup(HttpServletRequest request) throws ResourceCreationException{
        String page=null;
        String [] deleteIdsString=null;
        Integer [] deleteIds;
        deleteIdsString=request.getParameterValues(ParameterConstants.DELETE_SELECT);
        User user = (User) request.getSession().getAttribute(ParameterConstants.PARAMETER_USER);
        if (deleteIdsString!=null){
            deleteIds=new Integer[deleteIdsString.length];
            for (int idx=0; idx< deleteIds.length; idx++){
                deleteIds[idx]=Integer.parseInt(deleteIdsString[idx].trim());
            }
                try {
                    registrationDAO.deleteUserFromGroups(user, deleteIds);
                    statementLogic.updateOrAddUserStatement(request);
                    ArrayList <Group> userGroups= registrationDAO.showUserGroups(user.getId());
                    request.getSession().setAttribute(ParameterConstants.PARAMETER_GR_SPORTTYPES, userGroups);
                    log.info(LoggerConstants.LOGGER_USER+user.getId()+
                            LoggerConstants.LOGGER_USER_DELETE_FROM_GROUP+
                            deleteIds.length+LoggerConstants.LOGGER_GROUPS);
                } catch (MyLogicalInvalidParameterException ex) {
                    page=PageConstants.ERROR_PAGE_PATH;
                    return page;
                } catch (DAOSQLException ex) {
                    page=PageConstants.ERROR_PAGE_PATH;
                    return page;
                }
            
            page=PageConstants.USERCABINET_PAGE_PATH;
        }
        else {
            page=PageConstants.USERCABINET_PAGE_PATH;
        }
        return page;
    }

    /**
     * method is used when admin wants to delete user from groups 
     * @param request
     * @throws ResourceCreationException 
     */
    private String deleteUserFromTrainings(HttpServletRequest request) throws ResourceCreationException {
        String page=null;
        String [] deleteIdsString=null;
        Integer [] deleteIds;
        deleteIdsString=request.getParameterValues(ParameterConstants.DELETE_GROUP_SELECT);
        if (deleteIdsString!=null){
            deleteIds=new Integer[deleteIdsString.length];
            for (int idx=0; idx< deleteIds.length; idx++){
                deleteIds[idx]=Integer.parseInt(deleteIdsString[idx].trim());
            }
            Integer userId=(Integer) request.getSession().getAttribute(ParameterConstants.PARAMETER_USER_ID);
            User user = new User();
            try {
                user.setId(userId);
                registrationDAO.deleteUserFromGroups(user, deleteIds);
                adminLogic.showToAdminUserGroups(request);
                statementLogic.getStatementOfUser(user);
                ArrayList<Statement> allUserStatements=statementDAO.getAllUserStatements();
                request.getSession().setAttribute(ParameterConstants.ALL_STATEMENTS, allUserStatements);
                log.info(LoggerConstants.LOGGER_USER+user.getId()+
                        LoggerConstants.LOGGER_USER_DELETE_FROM_GROUP
                        +deleteIds.length+LoggerConstants.LOGGER_GROUPS);
            } catch (DAOSQLException ex) {
                    page=PageConstants.ERROR_PAGE_PATH;
                    return page;
            } catch (MyLogicalInvalidParameterException ex) {
                    page=PageConstants.ERROR_PAGE_PATH;
                    return page;
            }            
        }
        page=PageConstants.ADMIN_ALL_USERS_PATH;
        return page;
    }
    
}
