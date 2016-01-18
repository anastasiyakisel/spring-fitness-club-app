package by.bsu.kisel.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import by.bsu.kisel.constants.LoggerConstants;
import by.bsu.kisel.constants.PageConstants;
import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.controller.Controller;
import by.bsu.kisel.dao.IDAODiscount;
import by.bsu.kisel.dao.IDAOUser;
import by.bsu.kisel.entity.Discount;
import by.bsu.kisel.entity.User;
import by.bsu.kisel.enums.Post;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.manager.MessageManager;


/**
 *
 * @author Kisel Anastasia
 */
@Component("LoginCommand")
public class LoginCommand implements Command{
    
    private Logger log = Logger.getLogger(LoginCommand.class);
    @Autowired
    @Qualifier("DAOJdbcUser")
    private IDAOUser userDAO ;
    @Autowired
    @Qualifier("DAOJdbcDiscount")
    private IDAODiscount discountDAO ;
    
    public LoginCommand(){}
    /**
     * log in
     * @param request
     * @throws ResourceCreationException 
     */
    @Override
    public String execute(HttpServletRequest request) throws ResourceCreationException {
        HttpSession session = request.getSession();
        User user=new User();
        String page = null;
        String login = request.getParameter(ParameterConstants.PARAMETER_LOGIN);
        String pass = request.getParameter(ParameterConstants.PARAMETER_PASSWORD);
        try {
            user=userDAO.checkLogin(login, pass);
            request.getSession().setAttribute(ParameterConstants.PARAMETER_USER, user);  
            ArrayList<Discount> discounts=discountDAO.getInformationDiscount();
            request.getSession().setAttribute(ParameterConstants.ALL_DISCOUNTS, discounts);
            
            
    		
            request.setAttribute(ParameterConstants.PARAMETER_ERROR,
            		Controller.MSG_MANAGER.getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
            page = PageConstants.ERROR_PAGE_PATH;
        } catch (DAOSQLException ex) {
            request.setAttribute(ParameterConstants.PARAMETER_ERROR,
            		Controller.MSG_MANAGER.getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
            log.error(LoggerConstants.LOGGER_DAO_EXC);
            page = PageConstants.ERROR_PAGE_PATH;
            return page;
        } catch (MyLogicalInvalidParameterException ex) {
            request.setAttribute(ParameterConstants.PARAMETER_ERROR, 
            		Controller.MSG_MANAGER.getProperty(MessageManager.INCORRECT_PARAMETER_ERROR_MESSAGE));
            log.error(LoggerConstants.LOGGER_INCORRECT_INPUT);
            page = PageConstants.ERROR_PAGE_PATH;
            return page;
        }
        if (user.getLogin()!= null){            
                request.getSession().setAttribute(ParameterConstants.PARAMETER_USER, user);
                request.getSession().setAttribute(ParameterConstants.PARAMETER_LOGIN, user.getLogin());
                if (user.getPost().equals(Post.ADMIN)){
                    request.getSession().setAttribute(ParameterConstants.PARAMETER_ROLE, 1);
                }else {
                    request.getSession().setAttribute(ParameterConstants.PARAMETER_ROLE, 2);
                }
                log.info(LoggerConstants.LOGGER_USER+user.getId()+LoggerConstants.LOGGER_LOGIN);
                page=PageConstants.MAIN_PAGE_PATH;
                }
    	return page;
    }

}