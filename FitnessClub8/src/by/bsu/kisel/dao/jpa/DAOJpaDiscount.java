package by.bsu.kisel.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.bsu.kisel.dao.IDAODiscount;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.model.Discount;
/**
 * This class implements DAO Discount logic with the help of JPA.
 * @author Anastasiya Kisel
 */
@Repository("DAOJpaDiscount")
@Transactional
public class DAOJpaDiscount extends DAOJpa implements IDAODiscount{
	/**
	 * Provides the information about all available discounts of the fitness club.
	 * @return the list of all available discounts 
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	@SuppressWarnings("unchecked") // for suppression of compiler warnings generated as a result of unchecked type casts
	@Override
	public List<Discount> getInformationDiscount() throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException {
		String queryString = "SELECT a FROM Discount a";				
		Query query = em.createQuery(queryString);
		return (List<Discount>) query.getResultList();
	}
	/**
	 * Calculates the discount percent for the user based on the number of abonements this user owns.
	 * @param numberOfAbonements - number of the abonements the user owns
	 * @return the discount percent for the user
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	@Override
	public int countDiscountPercentForUser(int numberOfAbonements)
			throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException
	{
		Query q = em.createNamedQuery("countDiscountForUser");
		q.setParameter(1, numberOfAbonements);
		if (q.getSingleResult()==null){
			return 0;
		}
		return (int) q.getSingleResult();
	}

	
}
