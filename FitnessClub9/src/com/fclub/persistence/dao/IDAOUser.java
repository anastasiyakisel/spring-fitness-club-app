package com.fclub.persistence.dao;

import com.fclub.exception.AccountExistsException;
import com.fclub.exception.DAOSQLException;
import com.fclub.exception.MyLogicalInvalidParameterException;
import com.fclub.exception.ResourceCreationException;
import com.fclub.persistence.model.User;
/**
 * This interface describes the operations that DAO classes for User will implement.
 * @author Anastasiya Kisel
 *
 */
public interface IDAOUser {
	/**
	 * Checks if the user with specified login and password exists in the database.
	 * @param login - login 
	 * @param password - password
	 * @return User object if login was successful or null if login failed
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	User checkLogin(String login, String password) throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException;
	
	public User findByUsername (String login) throws ResourceCreationException, DAOSQLException, MyLogicalInvalidParameterException;

	User registerNewUserAccount (User user) throws AccountExistsException, ResourceCreationException, DAOSQLException, MyLogicalInvalidParameterException ;
	
	User findById (Integer id);
}
