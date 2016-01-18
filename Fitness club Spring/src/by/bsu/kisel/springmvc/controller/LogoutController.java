package by.bsu.kisel.springmvc.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import by.bsu.kisel.constants.ParameterConstants;
/**
 * This class implements business logic of Spring MVC Logout Controller.
 * @author Anastasiya Kisel
 */
@Controller
public class LogoutController {
	
	private Logger log = Logger.getLogger(LogoutController.class);
	/**
	 * Removes all session/model attributes/parameters after log on.
	 * @param model - ModelMap
	 * @param session - HttpSession
	 * @param status - SessionStatus
	 * @return login page
	 */
	@RequestMapping(value = "/logout.html", method = RequestMethod.GET)  
	public String logout(ModelMap model, HttpSession session, SessionStatus status){  
		status.setComplete();	
		
		model.remove(ParameterConstants.PARAMETER_ALLGROUPS);
        model.remove(ParameterConstants.PARAMETER_NUMBER);
        model.remove(ParameterConstants.PARAMETER_ADMIN_GR_SPORTTYPES);
        model.remove(ParameterConstants.PARAMETER_USER_ID);
        model.remove(ParameterConstants.ALL_STATEMENTS);
		session.removeAttribute(ParameterConstants.PARAMETER_ALLGROUPS);
        session.removeAttribute(ParameterConstants.PARAMETER_NUMBER);
        session.removeAttribute(ParameterConstants.PARAMETER_USER_ID);
        session.removeAttribute(ParameterConstants.ALL_STATEMENTS);
	
		model.remove(ParameterConstants.BUNDLE_NAME_PARAMETER);
        model.remove(ParameterConstants.PARAMETER_LOGIN);
        model.remove(ParameterConstants.PARAMETER_USER);
        model.remove(ParameterConstants.PARAMETER_ROLE);
        model.remove(ParameterConstants.PARAMETER_USERSTATEMENT);
        model.remove(ParameterConstants.PARAMETER_GR_SPORTTYPES);
        model.remove(ParameterConstants.PARAMETER_STATEMENT);
        model.remove(ParameterConstants.ALL_DISCOUNTS);
        session.removeAttribute(ParameterConstants.BUNDLE_NAME_PARAMETER);
        session.removeAttribute(ParameterConstants.PARAMETER_LOGIN);
        session.removeAttribute(ParameterConstants.PARAMETER_USER);
        session.removeAttribute(ParameterConstants.PARAMETER_ROLE);
        session.removeAttribute(ParameterConstants.PARAMETER_USERSTATEMENT);
        session.removeAttribute(ParameterConstants.PARAMETER_GR_SPORTTYPES);
        session.removeAttribute(ParameterConstants.ALL_DISCOUNTS); 
        session.removeAttribute(ParameterConstants.PARAMETER_STATEMENT); 
        
        log.info("User has logged out.");
		return "redirect:index.html";
	}
	
	//VALIDATION
	/*
	 * System.out.println("--- Model data ---"); for (Object modelKey :
	 * model.keySet()) { Object modelValue = model.get(modelKey);
	 * System.out.println(modelKey + " -- " + modelValue); }
	 * 
	 * System.out.println("=== Request data ==="); java.util.Enumeration<String>
	 * reqEnum = request.getAttributeNames(); while (reqEnum.hasMoreElements())
	 * { String s = reqEnum.nextElement(); System.out.println(s);
	 * System.out.println("==" + request.getAttribute(s)); }
	 * 
	 * System.out.println("*** Session data ***"); Enumeration<String> e =
	 * session.getAttributeNames(); while (e.hasMoreElements()){ String s =
	 * e.nextElement(); System.out.println(s); System.out.println("**" +
	 * session.getAttribute(s)); }
	 */
	
}
