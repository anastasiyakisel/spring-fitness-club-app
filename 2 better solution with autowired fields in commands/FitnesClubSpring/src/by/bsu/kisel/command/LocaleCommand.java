/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.command;

import by.bsu.kisel.constants.LocaleConstants;
import by.bsu.kisel.constants.LoggerConstants;
import by.bsu.kisel.constants.PageConstants;
import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anastasia Kisel
 */
@Component("LocaleCommand")
public class LocaleCommand implements Command {
    
    private Logger log = Logger.getLogger(LocaleCommand.class);
    /**
     * change locale
     * @param request
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page=PageConstants.INDEX_PAGE_PATH;
        HttpSession session = request.getSession();
        if (LocaleConstants.LOCALE_RU.equals(request.getParameter(ParameterConstants.LOCALE))) {
            session.setAttribute(ParameterConstants.BUNDLE_NAME_PARAMETER, LocaleConstants.LOCALE_RU);
        } else
        {
            session.setAttribute(ParameterConstants.BUNDLE_NAME_PARAMETER, LocaleConstants.LOCALE_USA);
        }
        User user = (User) request.getSession().getAttribute(ParameterConstants.PARAMETER_USER);
        if(user!=null){
        	if (user.getFirstName()!=null){
        		page=PageConstants.MAIN_PAGE_PATH;
        		log.info(LoggerConstants.LOGGER_USER+ user.getId()+LoggerConstants.LOGGER_LOCALE_CHANGE);
            }
        }       
        return page;
    }
    
}
