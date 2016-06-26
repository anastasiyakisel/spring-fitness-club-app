package com.fclub.persistence.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fclub.exception.DAOSQLException;
import com.fclub.exception.MyLogicalInvalidParameterException;
import com.fclub.exception.ResourceCreationException;
import com.fclub.persistence.dao.IDAOSporttype;
import com.fclub.persistence.model.Sporttype;
/**
 * This class implements DAO Sporttype logic with the help of JPA.
 * @author Anastasiya Kisel
 */
@Repository("DAOJpaSporttype")
@Transactional
public class DAOJpaSporttype extends DAOJpa implements IDAOSporttype {
	/**
	 * Provides Sporttype objects for the specified sport type's ids.
	 * @param ids - ids of the sport types
	 * @return the list of sport types 
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	@Override
	public List<Sporttype> showSporttypes(Integer... ids)
			throws DAOSQLException, MyLogicalInvalidParameterException,
			ResourceCreationException {
		List <Sporttype> sporttypes = new ArrayList<Sporttype>();
		for (int id:ids){
			Query q = em.createNamedQuery("allForConcreteSporttype");
			q.setParameter(1, id);
			Sporttype sporttype = (Sporttype) q.getSingleResult();
			sporttypes.add(sporttype); 
		}
		return sporttypes;
	}
	
}
