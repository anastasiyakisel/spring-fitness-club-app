package by.bsu.kisel.command;

import by.bsu.kisel.constants.LoggerConstants;
import by.bsu.kisel.constants.PageConstants;
import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.controller.Controller;
import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.dao.IDAOUser;
import by.bsu.kisel.dao.jdbc.DAOJdbcGroup;
import by.bsu.kisel.dao.jdbc.DAOJdbcUser;
import by.bsu.kisel.entity.Group;
import by.bsu.kisel.entity.User;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.logic.GroupLogic;
import by.bsu.kisel.logic.SignUpLogic;
import by.bsu.kisel.logic.StatementLogic;
import by.bsu.kisel.manager.MessageManager;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Anastasia Kisel
 */
public class SignUpCommand implements Command{

    private Logger log = Logger.getLogger(SignUpCommand.class);
    
    private IDAOUser userDAO = (IDAOUser) Controller.webContext.getBean("DAOJdbcUser");
    
    private IDAOGroup groupDAO = (IDAOGroup) Controller.webContext.getBean("DAOJdbcGroup");
    /**
     * sign up to the trainings
     * @param request
     * @throws ResourceCreationException 
     */
    @Override
    public String execute(HttpServletRequest request) throws ResourceCreationException {
        String page=null;
        String [] orderIds=null;
        Integer[] ordIds;
        orderIds=request.getParameterValues(ParameterConstants.PARAMETER_ORDER);        
        User user = (User) request.getSession().getAttribute(ParameterConstants.PARAMETER_USER);
        if (orderIds!=null){
            ordIds=new Integer[orderIds.length];
            for (int idx=0; idx< orderIds.length; idx++){
                ordIds[idx]=Integer.parseInt(orderIds[idx].trim());
            }            
        try {
            ordIds=SignUpLogic.getIndexesOfFreeGroups(ordIds);
            userDAO.add(user, ordIds); 
            StatementLogic.updateUserStatement(request, ordIds);
            ArrayList <Group> userGroups= groupDAO.showUserGroups(user.getId());
            userGroups=GroupLogic.updatePeopleRegistered(userGroups);
            GroupLogic.groupSporttypesShow(userGroups, request);             
        }   catch (MyLogicalInvalidParameterException ex) {
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
        page=PageConstants.USERCABINET_PAGE_PATH;
        
        if (ordIds.length>0){            
            request.setAttribute(ParameterConstants.PARAMETER_GREETING, ParameterConstants.PARAMETER_GREETING);
            log.info(LoggerConstants.LOGGER_USER+user.getId()+LoggerConstants.LOGGER_SIGNUP+
                    ordIds.length+LoggerConstants.LOGGER_GROUPS);
        }
        }   
        else {
            page=PageConstants.ORDER_PATH;
        }       
        return page;
    }    
}
