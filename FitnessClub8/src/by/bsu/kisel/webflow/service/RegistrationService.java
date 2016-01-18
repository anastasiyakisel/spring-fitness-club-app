package by.bsu.kisel.webflow.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.webflow.execution.RequestContextHolder;

import by.bsu.kisel.constants.MessageConstants;
import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.constants.UserRoleConstants;
import by.bsu.kisel.dao.IDAOUser;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.InvalidInputDataException;
import by.bsu.kisel.exception.LoginException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.model.User;

@Service
public class RegistrationService {
	private Logger log = Logger.getLogger(RegistrationService.class);
	
	private static final String EMPTY_STRING="";
	
	@Autowired
	@Qualifier("DAOJpaUser")
	private IDAOUser userDAO;
	
	public User createUser(User user) throws InvalidInputDataException, LoginException {
		
		HttpServletRequest request = (HttpServletRequest) RequestContextHolder.getRequestContext().getExternalContext().getNativeRequest();
		if (user.getLogin()!=null && user.getLogin().trim()!=EMPTY_STRING &&
			user.getFirstName()!=null && user.getFirstName().trim()!=EMPTY_STRING &&
			user.getLastName()!=null && user.getFirstName().trim()!=EMPTY_STRING &&
			user.getAddress()!=null && user.getAddress().trim()!=EMPTY_STRING &&
			user.getTelephone()!=null && user.getTelephone().trim()!=EMPTY_STRING &&
			user.getLogin()!=null && user.getLogin().trim() != EMPTY_STRING &&
			user.getPassword()!=null && user.getPassword().trim() != EMPTY_STRING){
			
			try {
				user.setPost(UserRoleConstants.CLIENT);
				userDAO.create(user);
				log.info("User "+user.getLogin()+" registered successfully.");
			} catch (ResourceCreationException | DAOSQLException | MyLogicalInvalidParameterException ex) {
				request.getSession().setAttribute(ParameterConstants.PARAMETER_ERROR, MessageConstants.REGISTRATION_ERROR_MESSAGE);				
				log.error("Problem to register for user "+user.getLogin());
				throw new LoginException("Problem to register for user "+user.getLogin(), user.getLogin(), ex);
			} 
			return user;
		} else {
			request.getSession().setAttribute(ParameterConstants.PARAMETER_ERROR, MessageConstants.REGISTRATION_ERROR_MESSAGE);
			log.error("Problem to register for user "+user.getLogin());
			throw new InvalidInputDataException("Registration problem occured for user "+user.getLogin());
		}
	}

}
