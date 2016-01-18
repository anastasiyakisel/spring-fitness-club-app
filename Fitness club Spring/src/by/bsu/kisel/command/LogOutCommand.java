/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.command;

import by.bsu.kisel.constants.LoggerConstants;
import by.bsu.kisel.constants.PageConstants;
import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.entity.User;
import by.bsu.kisel.enums.Post;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kisel Anastasia
 */
@Component("LogOutCommand")
public class LogOutCommand implements Command {

    private Logger log = Logger.getLogger(LogOutCommand.class);
    /**
     * log out
     * @param request
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
         User user = (User) request.getSession().getAttribute(ParameterConstants.PARAMETER_USER);
        if (user.getPost().equals(Post.ADMIN)){
            session.removeAttribute(ParameterConstants.PARAMETER_ALLGROUPS);
            session.removeAttribute(ParameterConstants.PARAMETER_NUMBER);
            session.removeAttribute(ParameterConstants.PARAMETER_ADMIN_GR_SPORTTYPES);
            session.removeAttribute(ParameterConstants.PARAMETER_USER_ID);
            session.removeAttribute(ParameterConstants.ALL_STATEMENTS);
        }
        session.removeAttribute(ParameterConstants.BUNDLE_NAME_PARAMETER);
        session.removeAttribute(ParameterConstants.PARAMETER_LOGIN);
        session.removeAttribute(ParameterConstants.PARAMETER_USER);
        session.removeAttribute(ParameterConstants.PARAMETER_ROLE);
        session.removeAttribute(ParameterConstants.PARAMETER_STATEMENT);
        session.removeAttribute(ParameterConstants.PARAMETER_GR_SPORTTYPES);
        session.removeAttribute(ParameterConstants.ALL_DISCOUNTS);
        log.info(LoggerConstants.LOGGER_USER+user.getId()+LoggerConstants.LOGGER_LOGOUT);
        return PageConstants.INDEX_PAGE_PATH;
    }
    
}
