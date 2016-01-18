package by.bsu.kisel.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import by.bsu.kisel.dao.IDAODiscount;
import by.bsu.kisel.entity.Discount;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

@Repository("DAOJpaDiscount")
public class DAOJpaDiscount extends DAOJpa implements IDAODiscount{
	
	@Override
	public List<Discount> getInformationDiscount() throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException {
		String queryString = "SELECT a FROM Discount a";				
		Query query = em.createQuery(queryString);
		return (List<Discount>) query.getResultList();
	}
	
	@Override
	public int countDiscountPercentForUser(int numberOfAbonements)
			throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException
	{
		Query q = em.createNamedQuery("countDiscountForUser");
		q.setParameter(1, numberOfAbonements);
		return (int) q.getSingleResult();
	}

	
}
