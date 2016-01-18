package by.bsu.kisel.springmvc.controller;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import by.bsu.kisel.constants.ErrorConstants;
import by.bsu.kisel.constants.MessageConstants;
import by.bsu.kisel.constants.PageConstants;
import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.constants.UserRoleConstants;
import by.bsu.kisel.dao.IDAODiscount;
import by.bsu.kisel.dao.IDAOUser;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.MyTechnicalException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.model.Discount;
import by.bsu.kisel.model.User;
/**
 * This class implements business logic of Spring MVC Login Controller.
 * @author Anastasiya Kisel
 */
@Controller
@SessionAttributes({ParameterConstants.PARAMETER_USER, 
				    ParameterConstants.ALL_DISCOUNTS,
				    ParameterConstants.PARAMETER_ROLE,
				    ParameterConstants.PARAMETER_LOGIN})

public class LoginController {

	private Logger log = Logger.getLogger(LoginController.class);
	@Autowired
	@Qualifier("DAOJpaUser")
	private IDAOUser userDAO;
	@Autowired
	@Qualifier("DAOJpaDiscount")
	private IDAODiscount discountDAO;
	/**
	 * Opens login page.
	 * @return ModelAndView object
	 */ 
	@RequestMapping(value = "index.html", method = RequestMethod.GET)
	public ModelAndView showForm() {
		log.info("User opens login page");
		User user = new User();
		return new ModelAndView("index", "user", user);
	}
	/**
	 * Validate if the user is able to log in with the credentials specified.
	 * @param checkUser - user
	 * @param result - BindingResult
	 * @param model - Model
	 * @return page
	 * @throws ResourceCreationException
	 * @throws MyTechnicalException
	 */
	@RequestMapping(value = "index.html", method = RequestMethod.POST)
	public String checkLogin(@ModelAttribute(value = "user") User checkUser,
			BindingResult result, Model model) throws ResourceCreationException, MyTechnicalException {

		User user = new User();
		String page = null;
		String login = checkUser.getLogin();
		String pass = checkUser.getPassword();
		try {
			user = userDAO.checkLogin(login, pass);
			model.addAttribute(ParameterConstants.PARAMETER_USER, user);
			ArrayList<Discount> discounts = (ArrayList<Discount>) discountDAO.getInformationDiscount();
			model.addAttribute(ParameterConstants.ALL_DISCOUNTS, discounts);			
		} catch (DAOSQLException | MyLogicalInvalidParameterException ex) {
			page = PageConstants.ERROR_PAGE_PATH;
			return page;
		} 
		if (user==null){
			model.addAttribute(ParameterConstants.PARAMETER_ERROR, MessageConstants.LOGIN_ERROR_MESSAGE);
			throw new MyTechnicalException(ErrorConstants.LOGGER_INVALID_CREDENTIALS);
		}
		else if (user.getLogin() != null) {
			model.addAttribute(ParameterConstants.PARAMETER_USER, user);
			model.addAttribute(ParameterConstants.PARAMETER_LOGIN, user.getLogin());
			if (user.getPost().equalsIgnoreCase(UserRoleConstants.ADMIN)) {
				model.addAttribute(ParameterConstants.PARAMETER_ROLE, 1);
			} else {
				model.addAttribute(ParameterConstants.PARAMETER_ROLE, 2);
			}
			log.info("User № " + user.getId() +" had logged in.");
			page = PageConstants.MAIN_PAGE_PATH;
		}
		return page;
	}
	/**
	 * Shows main page.
	 * @return main page
	 */
	@RequestMapping(value = "/home.html", method = RequestMethod.GET)  
	public String showMainPage(){
		return  PageConstants.MAIN_PAGE_PATH;
	}
}
