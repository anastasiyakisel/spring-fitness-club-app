package by.bsu.kisel.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.bsu.kisel.dao.IDAOSporttype;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.model.Sporttype;
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
