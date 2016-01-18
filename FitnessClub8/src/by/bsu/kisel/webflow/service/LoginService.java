package by.bsu.kisel.webflow.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.webflow.execution.RequestContextHolder;

import by.bsu.kisel.constants.ErrorConstants;
import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.constants.UserRoleConstants;
import by.bsu.kisel.dao.IDAODiscount;
import by.bsu.kisel.dao.IDAOUser;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.LoginException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.model.Discount;
import by.bsu.kisel.model.User;

@Service
@SessionAttributes({ParameterConstants.PARAMETER_USER, 
    ParameterConstants.ALL_DISCOUNTS,
    ParameterConstants.PARAMETER_ROLE,
    ParameterConstants.PARAMETER_LOGIN})
public class LoginService {
	private Logger log = Logger.getLogger(LoginService.class);
	@Autowired
	@Qualifier("DAOJpaUser")
	private IDAOUser userDAO;
	@Autowired
	@Qualifier("DAOJpaDiscount")
	private IDAODiscount discountDAO;
	
	public User checkLogin(User checkUser)
			throws LoginException {

		User user = new User();
		String login = checkUser.getLogin();
		String pass = checkUser.getPassword();
		HttpServletRequest request = (HttpServletRequest)RequestContextHolder.getRequestContext().getExternalContext().getNativeRequest();
		try {
			user = userDAO.checkLogin(login, pass);
			request.getSession().setAttribute(ParameterConstants.PARAMETER_USER, user);
			ArrayList<Discount> discounts = (ArrayList<Discount>) discountDAO.getInformationDiscount();
			request.getSession().setAttribute(ParameterConstants.ALL_DISCOUNTS, discounts);			
		} catch (DAOSQLException | ResourceCreationException | MyLogicalInvalidParameterException ex) {
			throw new LoginException("User with name "+login+" is not able to log in", login, ex);
		} 
		if (user==null){
			throw new LoginException("User with name "+login+" is not able to log in - "+ErrorConstants.LOGGER_INVALID_CREDENTIALS, login);
		}
		else if (user.getLogin() != null) {
			request.getSession().setAttribute(ParameterConstants.PARAMETER_USER, user);
			request.getSession().setAttribute(ParameterConstants.PARAMETER_LOGIN, user.getLogin());
			if (user.getPost().equalsIgnoreCase(UserRoleConstants.ADMIN)) {
				request.getSession().setAttribute(ParameterConstants.PARAMETER_ROLE, 1);
			} else {
				request.getSession().setAttribute(ParameterConstants.PARAMETER_ROLE, 2);
			}
			log.info("User № " + user.getId() +" had logged in.");
		}
		return user;
	}
}
