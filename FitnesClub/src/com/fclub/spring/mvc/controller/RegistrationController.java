package com.fclub.spring.mvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.fclub.exception.InvalidInputDataException;
import com.fclub.persistence.dao.UserJpaRepository;
import com.fclub.persistence.model.User;

@Controller
public class RegistrationController {
	
	@Autowired
	@Qualifier("DAOJpaUser")
	private UserJpaRepository userDAO;
	
	@RequestMapping(value = URLConstants.REGISTRATION, method = RequestMethod.GET)  
	public ModelAndView showRegistrationPage(@RequestParam(value = "error", required = false) final String error){
		final User user = new User();
		return new ModelAndView(PageConstants.REGISTRATION_PAGE_PATH, "user", user);
	}
	
	@RequestMapping(value = URLConstants.REGISTRATION, method=RequestMethod.POST) 
	public ModelAndView createUser(@ModelAttribute("user") @Valid final User user, 
			  final BindingResult result, final WebRequest request, final Errors errors) throws InvalidInputDataException {
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

	private User createUserAccount(final User user, final BindingResult result) {
		User registered = userDAO.findByUsername(user.getUsername());
		registered=userDAO.save(registered);		
		return registered;
	}
	
}
