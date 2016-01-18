package by.bsu.kisel.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import by.bsu.kisel.constants.LoggerConstants;
import by.bsu.kisel.constants.PageConstants;
import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.dao.IDAOUser;
import by.bsu.kisel.entity.Group;
import by.bsu.kisel.entity.User;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.logic.GroupLogic;
import by.bsu.kisel.logic.SignUpLogic;
import by.bsu.kisel.logic.StatementLogic;

/**
 *
 * @author Anastasia Kisel
 */
@Component("SignUpCommand")
public class SignUpCommand implements Command{

    private Logger log = Logger.getLogger(SignUpCommand.class);
    @Autowired
    @Qualifier("DAOJdbcUser")
    private IDAOUser userDAO ;
    @Autowired
    @Qualifier("DAOJdbcGroup")
    private IDAOGroup groupDAO ;
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
            page=PageConstants.ERROR_PAGE_PATH;
            return page;
        } catch (DAOSQLException ex) {
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
