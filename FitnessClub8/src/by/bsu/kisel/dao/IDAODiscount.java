package by.bsu.kisel.dao;

import java.util.List;

import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.model.Discount;
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
