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
import by.bsu.kisel.dao.IDAOUser;
import by.bsu.kisel.entity.Group;
import by.bsu.kisel.entity.User;
import by.bsu.kisel.entity.UserStatement;
import by.bsu.kisel.enums.Entities;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.logic.AdminLogic;
import by.bsu.kisel.logic.GroupLogic;
import by.bsu.kisel.logic.StatementLogic;
import by.bsu.kisel.logic.ViewLogic;
import by.bsu.kisel.manager.MessageManager;

/**
 *
 * @author Anastasia Kisel
 */
public class DeleteCommand implements Command {

    private Logger log = Logger.getLogger(DeleteCommand.class);
    
    private IDAOGroup groupDAO = (IDAOGroup) Controller.webContext.getBean("DAOJdbcGroup");
    
    private IDAOUser userDAO = (IDAOUser) Controller.webContext.getBean("DAOJdbcUser");
    
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
            	groupDAO.deleteUsersFromGroup(numberGroup, userIds);
                ArrayList<Group> allGroups=groupDAO.adminShowAllGroups();
                allGroups=GroupLogic.updatePeopleRegistered(allGroups);
                GroupLogic.groupSporttypesShow(allGroups, request);
                ArrayList<User> usersOfGroup=userDAO.getUsersFromGroup(numberGroup);
                ViewLogic.showUserStatements(usersOfGroup, request);                
                log.info(LoggerConstants.LOGGER_ADMIN_DELETE_USERS+ userIdsStr +LoggerConstants.LOGGER_FROM_GROUP+numberGroup);
            } catch (MyLogicalInvalidParameterException ex) {
                    request.setAttribute(ParameterConstants.PARAMETER_ERROR, 
                    		Controller.MSG_MANAGER.getProperty(MessageManager.INCORRECT_PARAMETER_ERROR_MESSAGE));
                    log.error(LoggerConstants.LOGGER_INCORRECT_INPUT);
                    page=PageConstants.ERROR_PAGE_PATH;
                    return page;
            } catch (DAOSQLException ex) {
                    request.setAttribute(ParameterConstants.PARAMETER_ERROR, 
                    		Controller.MSG_MANAGER.getProperty(MessageManager.DAO_EXCEPTION));
                    log.error(LoggerConstants.LOGGER_DAO_EXC);
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
            //for (Integer deleteId:deleteIds){
                try {
                    groupDAO.deleteUserFromGroups(user, deleteIds);
                    StatementLogic.updateUserStatement(request, deleteIds);
                    ArrayList <Group> userGroups= groupDAO.showUserGroups(user.getId());
                    GroupLogic.groupSporttypesShow(userGroups, request);
                    log.info(LoggerConstants.LOGGER_USER+user.getId()+
                            LoggerConstants.LOGGER_USER_DELETE_FROM_GROUP+
                            deleteIds.length+LoggerConstants.LOGGER_GROUPS);
                } catch (MyLogicalInvalidParameterException ex) {
                    request.setAttribute(ParameterConstants.PARAMETER_ERROR, 
                    		Controller.MSG_MANAGER.getProperty(MessageManager.INCORRECT_PARAMETER_ERROR_MESSAGE));
                    log.error(LoggerConstants.LOGGER_INCORRECT_INPUT);
                    page=PageConstants.ERROR_PAGE_PATH;
                    return page;
                } catch (DAOSQLException ex) {
                    request.setAttribute(ParameterConstants.PARAMETER_ERROR, 
                    		Controller.MSG_MANAGER.getProperty(MessageManager.DAO_EXCEPTION));
                    log.error(LoggerConstants.LOGGER_DAO_EXC);
                    page=PageConstants.ERROR_PAGE_PATH;
                    return page;
                }
            //}
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
                groupDAO.deleteUserFromGroups(user, deleteIds);
                AdminLogic.showToAdminUserGroups(request);
                StatementLogic.getStatementOfUser(user);
                ArrayList<UserStatement> allUserStatements=userDAO.getAllUserStatements();
                request.getSession().setAttribute(ParameterConstants.ALL_STATEMENTS, allUserStatements);
                log.info(LoggerConstants.LOGGER_USER+user.getId()+
                        LoggerConstants.LOGGER_USER_DELETE_FROM_GROUP
                        +deleteIds.length+LoggerConstants.LOGGER_GROUPS);
            } catch (DAOSQLException ex) {
                    request.setAttribute(ParameterConstants.PARAMETER_ERROR, 
                    		Controller.MSG_MANAGER.getProperty(MessageManager.DAO_EXCEPTION));
                    log.error(LoggerConstants.LOGGER_DAO_EXC);
                    page=PageConstants.ERROR_PAGE_PATH;
                    return page;
            } catch (MyLogicalInvalidParameterException ex) {
                    request.setAttribute(ParameterConstants.PARAMETER_ERROR, 
                    		Controller.MSG_MANAGER.getProperty(MessageManager.INCORRECT_PARAMETER_ERROR_MESSAGE));
                    log.error(LoggerConstants.LOGGER_INCORRECT_INPUT);
                    page=PageConstants.ERROR_PAGE_PATH;
                    return page;
            }            
        }
        page=PageConstants.ADMIN_ALL_USERS_PATH;
        return page;
    }
    
}
