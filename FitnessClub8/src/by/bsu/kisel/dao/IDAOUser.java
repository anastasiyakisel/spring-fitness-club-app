package by.bsu.kisel.dao;

import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.model.User;
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
	
	public User create( User user) throws ResourceCreationException, DAOSQLException ;

}
