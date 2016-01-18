package by.bsu.kisel.dao.jpa;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.entity.Group;
import by.bsu.kisel.entity.User;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

@Repository("DAOJpaGroup")
public class DAOJpaGroup extends DAOJpa implements IDAOGroup{
	
	@Override
	public ArrayList<Group> showAllGroups(int sporttypeId)
			throws DAOSQLException, MyLogicalInvalidParameterException,
			ResourceCreationException {
		Query q = em.createNamedQuery("getGroupsBySporttype");
		q.setParameter(1, sporttypeId);
		ArrayList<Group> groups = (ArrayList<Group>)q.getResultList();
		return (ArrayList<Group>)groups;
	}

	@Override
	public ArrayList<Group> adminShowAllGroups() throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException {
		ArrayList<Group> groups = (ArrayList<Group>) em.createQuery("SELECT x FROM Group x").getResultList();
		return groups;
	}

	@Override
	@Transactional
	public boolean updatePeopleRegistered(int groupNumber, int numberOfUsers)
			throws DAOSQLException, ResourceCreationException, MyLogicalInvalidParameterException {
		Group group = em.find(Group.class, groupNumber);
		group.setPeopleRegistered(numberOfUsers);
		em.persist(group);
		return true;
	}

	@Override
	public ArrayList<Group> getFreeGroups(Integer... ids)
			throws MyLogicalInvalidParameterException, DAOSQLException,
			ResourceCreationException {
		Query q = em.createNamedQuery("selectCapacityAndPeopleInTheGroup");
		ArrayList<Group> resultGroups= new ArrayList<Group>();
		for (int i=0; i<ids.length; ++i){
			q.setParameter(1, ids[i]);
			q.getResultList();
			List<Object[]> resultList = q.getResultList();
			for (Object[] result : resultList){
				Integer capacity =(Integer) result[0];
				Integer peopleRegistered = (Integer)result[1];
			    if (capacity-peopleRegistered>0){
			    	Group group = em.find(Group.class, ids[i]);
                    resultGroups.add(group);
                }
			}
				  
			
		}
		return resultGroups;
	}
	
	@Override
	public int countCostOfAllUsersGroups(int [] recIds)
			throws MyLogicalInvalidParameterException, DAOSQLException,
			ResourceCreationException {
		int existSumm=0;
        for (int recId:recIds){
        	Query q = em.createNamedQuery("getCostOfGroup");
        	q.setParameter(1, recId);
            int currentCostGroup = (int) q.getSingleResult();
            existSumm+=currentCostGroup;
        }
        return existSumm;
	}

	@Override
	public Group getConcreteGroup(int groupId)
			throws MyLogicalInvalidParameterException, DAOSQLException {
		Query q = em.createNamedQuery("selectGroupById");
		q.setParameter(1, groupId);
		return (Group)q.getSingleResult();
	}

}
