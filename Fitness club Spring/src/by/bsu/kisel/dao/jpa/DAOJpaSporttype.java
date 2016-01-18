package by.bsu.kisel.dao.jpa;

import java.util.ArrayList;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.bsu.kisel.dao.IDAOSporttype;
import by.bsu.kisel.entity.Sporttype;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

@Repository("DAOJpaSporttype")
public class DAOJpaSporttype extends DAOJpa implements IDAOSporttype {
	
	@Override
	public ArrayList<Sporttype> showSporttypes(Integer... ids)
			throws DAOSQLException, MyLogicalInvalidParameterException,
			ResourceCreationException {
		ArrayList <Sporttype> sporttypes = new ArrayList<Sporttype>();
		for (int id:ids){
			Query q = em.createNamedQuery("allForConcreteSporttype");
			q.setParameter(1, id);
			Sporttype sporttype = (Sporttype) q.getSingleResult();
			sporttypes.add(sporttype); 
		}
		return sporttypes;
	}
	
}
