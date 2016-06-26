package com.fclub.persistence.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fclub.exception.DAOSQLException;
import com.fclub.exception.MyLogicalInvalidParameterException;
import com.fclub.exception.ResourceCreationException;
import com.fclub.persistence.dao.IDAOStatement;
import com.fclub.persistence.model.Statement;
/**
 * This class implements DAO Statement logic with the help of JPA.
 * @author Anastasiya Kisel
 */
@Repository("DAOJpaStatement")
@Transactional
public class DAOJpaStatement extends DAOJpa  implements IDAOStatement{
	/**
	 * Indicates if user's statement exists in the database.
	 * @param personId - id of the person
	 * @return boolean flag which indicates if the statement for the user already exists in the database.
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean isUserExistInStatement(int personId) throws DAOSQLException,
			ResourceCreationException {
		Query q = em.createNamedQuery("isUserExistsInStatement");
		q.setParameter(1, personId);
		List<Long> countList = (List<Long>) q.getResultList();
		return (countList.get(0)>0) ? true:false;
	}
	/**
	 * Provides all the information about user's statement.
	 * @param personId - id of the user
	 * @return user's statement 
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	@Override
	public Statement getAllFromStatement(int personId) throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException {
		Query q = em.createNamedQuery("selectInfoAboutStatement");
		q.setParameter(1, personId);
		return (Statement) q.getSingleResult(); 
	}
	/**
	 * Adds the statement to the database.
	 * @param statement - statement 
	 * @return boolean flag which indicates if the addition of the user was successful
	 * @throws DAOSQLException
	 */
	@Override
	public boolean add(Statement entity) throws DAOSQLException {
		em.persist(entity);
		return true;
	}
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
	@Override
	public boolean update(Statement stat, int numberOfAbonements, int discountPercent, int summCost)
			throws DAOSQLException, MyLogicalInvalidParameterException,
			ResourceCreationException {
		stat =  (stat.getId()!=0) ? em.find(Statement.class, stat.getId()) : getAllFromStatement(stat.getUser().getId());
		stat.setNumberOfAbonements(numberOfAbonements);
		stat.setDiscountPercent(discountPercent);
		if (numberOfAbonements==0){
			summCost=0;
			stat.setGeneralCost(0);
		}
		if (summCost !=0) {
                double generalCost = (discountPercent>0) ? summCost*(100-discountPercent)*0.01 : summCost;                
        		stat.setGeneralCost(generalCost);
        }
		em.merge(stat);
		return true;
	}	
	/**
	 * Provides all statements.
	 * @return the list of all the statements 
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Statement> getAllUserStatements()
			throws DAOSQLException, MyLogicalInvalidParameterException,
			ResourceCreationException {		
		String queryString = "SELECT st FROM Statement st";
		Query query = em.createQuery(queryString);
		return (List<Statement>) query.getResultList();
		
	}

}
