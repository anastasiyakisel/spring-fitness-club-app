package by.bsu.kisel.dao;

import java.util.List;

import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.model.Statement;
/**
 * This interface describes the operations that DAO classes for Statement will implement.
 * @author Anastasiya Kisel
 *
 */
public interface IDAOStatement {
	/**
	 * Indicates if user's statement exists in the database.
	 * @param personId - id of the person
	 * @return boolean flag which indicates if the statement for the user already exists in the database.
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
	boolean isUserExistInStatement(int personId) throws DAOSQLException,
			ResourceCreationException;
	/**
	 * Provides all the information about user's statement.
	 * @param personId - id of the user
	 * @return user's statement 
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	Statement getAllFromStatement(int personId) throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException;
	/**
	 * Adds the statement to the database.
	 * @param statement - statement 
	 * @return boolean flag which indicates if the addition of the user was successful
	 * @throws DAOSQLException
	 */
	boolean add(Statement statement) throws DAOSQLException;
	/**
	 * Updates the statement in the database with the values of the specified method's parameters
	 * @param stat - statement
	 * @param numberOfAbonements - number of abonements
	 * @param discountPercent - discount percent
	 * @param summCost - total cost
	 * @return boolean flag which indicates if the update of the statement was successful
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	public boolean update(Statement stat, int numberOfAbonements, int discountPercent, int summCost)
			throws DAOSQLException, MyLogicalInvalidParameterException,
			ResourceCreationException;
	/**
	 * Provides all statements.
	 * @return the list of all the statements 
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	List<Statement> getAllUserStatements() throws DAOSQLException, 
    	MyLogicalInvalidParameterException, ResourceCreationException;

}
