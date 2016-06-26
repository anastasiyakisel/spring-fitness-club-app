package com.fclub.persistence.dao;

import java.util.List;

import com.fclub.exception.DAOSQLException;
import com.fclub.exception.MyLogicalInvalidParameterException;
import com.fclub.exception.ResourceCreationException;
import com.fclub.persistence.model.Sporttype;
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
