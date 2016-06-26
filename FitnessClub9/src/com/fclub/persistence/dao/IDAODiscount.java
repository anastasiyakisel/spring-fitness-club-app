package com.fclub.persistence.dao;

import java.util.List;

import com.fclub.exception.DAOSQLException;
import com.fclub.exception.MyLogicalInvalidParameterException;
import com.fclub.exception.ResourceCreationException;
import com.fclub.persistence.model.Discount;
/**
 * This interface describes the operations that DAO classes for Discount will implement.
 * @author Anastasiya Kisel
 *
 */
public interface IDAODiscount {
	/**
	 * Provides the information about all available discounts of the fitness club.
	 * @return the list of all available discounts 
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	List<Discount> getInformationDiscount() throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException;
	/**
	 * Calculates the discount percent for the user based on the number of abonements this user owns.
	 * @param numberOfAbonements - number of the abonements the user owns
	 * @return the discount percent for the user
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	int countDiscountPercentForUser(int numberOfAbonements)
			throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException;
}
