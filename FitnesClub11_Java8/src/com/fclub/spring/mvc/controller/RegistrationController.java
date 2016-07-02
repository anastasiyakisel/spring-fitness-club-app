package com.fclub.spring.mvc.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fclub.constants.ErrorConstants;
import com.fclub.constants.PageConstants;
import com.fclub.constants.URLConstants;
import com.fclub.exception.AccountExistsException;
import com.fclub.exception.DAOSQLException;
import com.fclub.exception.InvalidInputDataException;
import com.fclub.exception.MyLogicalInvalidParameterException;
import com.fclub.exception.ResourceCreationException;
import com.fclub.persistence.dao.IDAOUser;
import com.fclub.persistence.model.User;

@Controller
public class RegistrationController {
	
	private Logger log = Logger.getLogger(RegistrationController.class);
	
	@Autowired
	@Qualifier("DAOJpaUser")
	private IDAOUser userDAO;
	
	@Autowired
	private UserDetailsManager manager; 
	
	@RequestMapping(value = URLConstants.REGISTRATION, method = RequestMethod.GET)  
	public ModelAndView showRegistrationPage(@RequestParam(value = "error", required = false) String error){
		User user = new User();
		return new ModelAndView(PageConstants.REGISTRATION_PAGE_PATH, "user", user);
	}
	
	@RequestMapping(value = URLConstants.REGISTRATION, method=RequestMethod.POST) 
	public ModelAndView createUser(@ModelAttribute("user") @Valid User user, 
			  BindingResult result, WebRequest request, Errors errors) throws InvalidInputDataException {
		User registered = new User();
		if (!result.hasErrors()){
			registered = createUserAccount(user, result);
		} 
		if (registered == null){
			result.rejectValue("", ErrorConstants.USER_ALREADY_EXISTS);
		}		
		if (!result.hasErrors()){
			return new ModelAndView(PageConstants.LOGIN_PAGE_PATH);
		}  else {
			return new ModelAndView(PageConstants.REGISTRATION_PAGE_PATH, "user", user);
		}		
	}

	private User createUserAccount(User user, BindingResult result) {
		User registered = null;
		try {
			registered = userDAO.registerNewUserAccount(user);
		} catch (AccountExistsException | ResourceCreationException
				| DAOSQLException | MyLogicalInvalidParameterException e) {
			log.error("Failed to register user with login "+user.getUsername(), e);
		}
		return registered;
	}
	
	private void doAutoLogin(User user){
		UserDetails userDetails = manager.loadUserByUsername(user.getUsername());
		Authentication auth = new UsernamePasswordAuthenticationToken(
				userDetails.getUsername(), userDetails.getPassword(),
				userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
