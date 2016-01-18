package by.bsu.kisel.dao;

import java.util.List;

import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.model.Sporttype;
/**
 * This interface describes the operations that DAO classes for Sporttype will implement.
 * @author Anastasiya Kisel
 *
 */
public interface IDAOSporttype {
	/**
	 * Provides Sporttype objects for the specified sport type's ids.
	 * @param ids - ids of the sport types
	 * @return the list of sport types 
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	List<Sporttype> showSporttypes(Integer... ids) throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException;

}
