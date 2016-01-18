package by.bsu.kisel.command;

import by.bsu.kisel.constants.CommandConstants;
import by.bsu.kisel.constants.LoggerConstants;
import by.bsu.kisel.constants.PageConstants;
import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.controller.Controller;
import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.dao.IDAOUser;
import by.bsu.kisel.dao.jdbc.DAOJdbcGroup;
import by.bsu.kisel.dao.jdbc.DAOJdbcUser;
import by.bsu.kisel.entity.*;
import by.bsu.kisel.enums.Entities;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.logic.*;
import by.bsu.kisel.manager.MessageManager;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Anastasia Kisel
 */
public class ViewCommand implements Command{

    private Logger log = Logger.getLogger(ViewCommand.class);
    
    private IDAOGroup groupDAO = (IDAOGroup) Controller.webContext.getBean("DAOJdbcGroup");
    
    private IDAOUser userDAO = (IDAOUser) Controller.webContext.getBean("DAOJdbcUser");
    
    @Override
    public String execute(HttpServletRequest request) throws ResourceCreationException {
        switch (Entities.valueOf(request.getParameter(ParameterConstants.PARAMETER_ENTITY).toUpperCase())){
            case GROUPS:
                return viewGroupsBySportType(request);
            case STATEMENT:
                return viewStatement(request);
            case ADMIN:
                return viewAdminGroups(request);
            case USER:
                return viewUsersOfGroup(request);
            case USERS:
                return viewAdminAllUsers(request);
            case ABONEMENT:
                return viewAdminGroupsOfUser(request);
            case DISCOUNT:
                return viewMainPage(request);
            case ORDER:
                return viewOrderPage();
            default:
		request.setAttribute(ParameterConstants.PARAMETER_ERROR, 
				Controller.MSG_MANAGER.getProperty(MessageManager.NO_SUCH_ENUM_VALUE));
                log.error(LoggerConstants.LOGGER_ENUM_EXC);
		return PageConstants.ERROR_PAGE_PATH;
        }
    }
    /**
     * view groups of concrete sporttype 
     * @param request
     * @throws ResourceCreationException 
     */
    private String viewGroupsBySportType(HttpServletRequest request) throws ResourceCreationException{
        String page = null;
        if (request.getParameter(CommandConstants.COMMAND_VIEW)!=null){
            String sporttypeIdStr=request.getParameter(CommandConstants.COMMAND_VIEW);
            int sporttypeId=Integer.parseInt(sporttypeIdStr);
            ArrayList<Group> sportGroups=null;
            try {
                sportGroups=groupDAO.showAllGroups(sporttypeId);
                sportGroups=GroupLogic.updatePeopleRegistered(sportGroups);                
                request.setAttribute(ParameterConstants.PARAMETER_SPORTGROUPS, sportGroups);
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
            page=PageConstants.GROUPS_BY_SPORTTYPE_PATH;
        }
        else {
            request.setAttribute(ParameterConstants.PARAMETER_ERROR, Controller.MSG_MANAGER.
                    getProperty(MessageManager.RADIOBUTTON_ERROR_MESSAGE));
            log.error(LoggerConstants.LOGGER_VIEW_FAIL);
            page=PageConstants.ERROR_PAGE_PATH;
        }
        return page;
    }
    /**
     * view statement of the user and his groups 
     * @param request
     * @throws ResourceCreationException 
     */
    private String viewStatement(HttpServletRequest request) throws ResourceCreationException{
        Statement userStatement=new Statement();
        ArrayList <GroupSporttype> groupSporttypes = new ArrayList<GroupSporttype>();
        String page = null;
        User user = (User) request.getSession().getAttribute(ParameterConstants.PARAMETER_USER);
        try {
            userStatement=StatementLogic.getStatementOfUser(user);
            ArrayList <Group> userGroups= groupDAO.showUserGroups(user.getId());
            userGroups=GroupLogic.updatePeopleRegistered(userGroups);
            GroupLogic.groupSporttypesShow(userGroups, request);
            
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
            log.info(LoggerConstants.LOGGER_USER+user.getId()+LoggerConstants.LOGGER_VIEW_STATEMENT);
            request.getSession().setAttribute(ParameterConstants.PARAMETER_STATEMENT, userStatement);
            page=PageConstants.USERCABINET_PAGE_PATH;
        return page;
    }
    /**
     * method used when admin wants to view information about all groups 
     * in the fitness-club
     * @param request
     * @throws ResourceCreationException 
     */
    private String viewAdminGroups(HttpServletRequest request) throws ResourceCreationException{
        ArrayList<Group> allGroups=null;
        String page = null;
        try {
            allGroups=groupDAO.adminShowAllGroups();
            allGroups=GroupLogic.updatePeopleRegistered(allGroups);
            GroupLogic.groupSporttypesShow(allGroups, request);
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
        //log.info("Admin had been viewing all groups of the fitness-club. ");
        request.setAttribute(ParameterConstants.PARAMETER_ALLGROUPS, allGroups);
        page=PageConstants.ADMIN_PAGE_PATH;
        return page;
    }
    /**
     * method used when admin wants to view information about users
     * of the concrete group
     * @param request
     * @throws ResourceCreationException 
     */
    private String viewUsersOfGroup(HttpServletRequest request) throws ResourceCreationException {
        ArrayList<UserStatement> userStatements =null;        
        String page = null;
        String[] numberStr=request.getParameterValues(ParameterConstants.GROUP_SELECT);
        if (numberStr!=null){
            int number=Integer.parseInt(numberStr[0].trim());   
            request.getSession().setAttribute(ParameterConstants.PARAMETER_NUMBER, number);
            try {
                ArrayList<User> usersOfGroup=userDAO.getUsersFromGroup(number);
                request.getSession().setAttribute(ParameterConstants.PARAMETER_NUMBER, number);
                ViewLogic.showUserStatements(usersOfGroup, request);
                log.info(LoggerConstants.LOGGER_ADMIN_VIEW_USERS+number);
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
        else{
            request.setAttribute(ParameterConstants.PARAMETER_REGRET, ParameterConstants.PARAMETER_REGRET);            
        }        
        page=PageConstants.ADMIN_PAGE_PATH;
        return page;
    }
    /**
     * method used when admin wants to view information about all users
     * of the site
     * @param request
     * @throws ResourceCreationException 
     */
    private String viewAdminAllUsers(HttpServletRequest request) throws ResourceCreationException{
        String page = null;
        ArrayList<UserStatement> allUserStatements=null;
        try {
            allUserStatements=userDAO.getAllUserStatements();
            
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
        //log.info("Admin had been viewing all users of the site. \n");
        page=PageConstants.ADMIN_ALL_USERS_PATH;
        request.getSession().setAttribute(ParameterConstants.ALL_STATEMENTS, allUserStatements);
        return page;
    }
    /**
     * method used when admin wants to view groups of the concrete user
     * @param request
     * @throws ResourceCreationException 
     */
    private String viewAdminGroupsOfUser(HttpServletRequest request) throws ResourceCreationException {
        ArrayList <GroupSporttype> userGroupSporttypes=null;
        String page = null;
        String[] userIdStr=request.getParameterValues(ParameterConstants.USERID_SELECT);
        if (userIdStr!=null){
            int userId=Integer.parseInt(userIdStr[0].trim());
            request.getSession().setAttribute(ParameterConstants.PARAMETER_USER_ID, userId);
            userGroupSporttypes = new ArrayList<GroupSporttype>();
            try {
                AdminLogic.showToAdminUserGroups(request);
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
            log.info(LoggerConstants.LOGGER_ADMIN_VIEW_USERGROUPS+userId);
        }
        else {
            page=PageConstants.ADMIN_ALL_USERS_PATH;
        }
        page=PageConstants.ADMIN_ALL_USERS_PATH;
        return page;
    }

    private String viewMainPage(HttpServletRequest request) {
        String page = null;
        page=PageConstants.MAIN_PAGE_PATH;
        return page;
    }

    private String viewOrderPage() {
        String page = null;
        page=PageConstants.ORDER_PATH;
        return page;
    }
}

