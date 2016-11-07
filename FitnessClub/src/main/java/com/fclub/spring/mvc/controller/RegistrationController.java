package com.fclub.spring.mvc.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.fclub.exception.InvalidInputDataException;
import com.fclub.persistence.model.User;
import com.fclub.persistence.repository.UserJpaRepository;

@Controller
public class RegistrationController {
	
	private static final Logger logger = Logger.getLogger(RegistrationController.class);
	
	@Autowired
	private UserJpaRepository userDAO;
	
	@RequestMapping(value = URLConstants.REGISTRATION, method = RequestMethod.GET)  
	public ModelAndView showRegistrationPage(@RequestParam(value = "error", required = false) final String error){
		final User user = new User();
		return new ModelAndView(PageConstants.REGISTRATION_PAGE_PATH, "user", user);
	}
	
	@RequestMapping(value = URLConstants.REGISTRATION, method=RequestMethod.POST) 
	public ModelAndView createUser(@ModelAttribute("user") @Valid final User user, 
			  final BindingResult result, final WebRequest request, final Errors errors) throws InvalidInputDataException, AccountExistsException {
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

	private User createUserAccount(final User user, final BindingResult result) throws AccountExistsException {
		User registered = userDAO.findByUsername(user.getUsername());
		if (registered == null){
		 registered=userDAO.save(user);
		} else {
			logger.error("Account with username "+user.getUsername()+" already exists in the system.");            
			throw new AccountExistsException("Account with username "+user.getUsername()+" already exists in the system.");
		}
		return registered;
	}
	
}
