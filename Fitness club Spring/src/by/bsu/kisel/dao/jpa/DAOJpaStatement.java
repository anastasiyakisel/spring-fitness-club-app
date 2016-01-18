package by.bsu.kisel.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.bsu.kisel.dao.IDAOStatement;
import by.bsu.kisel.entity.Statement;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

@Repository("DAOJpaStatement")
public class DAOJpaStatement extends DAOJpa  implements IDAOStatement{
	
	@Override
	public boolean isUserExistInStatement(int personId) throws DAOSQLException,
			ResourceCreationException {
		Query q = em.createNamedQuery("isUserExistsInStatement");
		q.setParameter(1, personId);
		List<Long> countList = (List<Long>) q.getResultList();
		return (countList.get(0)>0) ? true:false;
	}

	@Override
	public Statement getAllFromStatement(int personId) throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException {
		Query q = em.createNamedQuery("selectInfoAboutStatement");
		q.setParameter(1, personId);
		return (Statement) q.getSingleResult(); 
	}

	@Override
	@Transactional
	public boolean add(Statement entity) throws DAOSQLException {
		em.persist(entity);
		return true;
	}

	@Override
	@Transactional
	public boolean update(Statement stat, int numberOfAbonements, int discountPercent, int summCost)
			throws DAOSQLException, MyLogicalInvalidParameterException,
			ResourceCreationException {
		stat =  (stat.getId()!=0) ? em.find(Statement.class, stat.getId()) : getAllFromStatement(stat.getUser().getId());
		stat.setNumberOfAbonements(numberOfAbonements);
		if (discountPercent!=0 && summCost !=0) {
                double generalCost = (discountPercent>0) ? summCost*(100-discountPercent)*0.01 : summCost;
                stat.setDiscountPercent(discountPercent);
        		stat.setGeneralCost(generalCost);
        }
		em.persist(stat);
		return true;
	}
	

	@Override
	public ArrayList<Statement> getAllUserStatements()
			throws DAOSQLException, MyLogicalInvalidParameterException,
			ResourceCreationException {		
		String queryString = "SELECT st FROM Statement st";//on u.id=st.user.id ORDER BY st.numberOfAbonements
		Query query = em.createQuery(queryString);
		return (ArrayList<Statement>) query.getResultList();
		
	}

}
