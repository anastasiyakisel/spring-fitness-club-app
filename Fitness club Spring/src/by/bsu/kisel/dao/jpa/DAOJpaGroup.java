package by.bsu.kisel.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.model.Group;
/**
 * This class implements DAO Group logic with the help of JPA.
 * @author Anastasiya Kisel
 */
@Repository("DAOJpaGroup")
@Transactional//(readOnly=false)
public class DAOJpaGroup extends DAOJpa implements IDAOGroup{
	/**
	 * Provides the groups of the concrete sport type.
	 * @param sporttypeId - id of the sport type
	 * @return the list of the groups for the specified sport type
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> showAllGroups(int sporttypeId)
			throws DAOSQLException, MyLogicalInvalidParameterException,
			ResourceCreationException {
		Query q = em.createNamedQuery("getGroupsBySporttype");
		q.setParameter(1, sporttypeId);
		List<Group> groups = (ArrayList<Group>)q.getResultList();
		return (ArrayList<Group>)groups;
	}
	/**
	 * Provides the information about all the groups in the fitness club.
	 * @return the list of all the groups in the fitness club
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> adminShowAllGroups() throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException {
		List<Group> groups = (ArrayList<Group>) em.createQuery("SELECT x FROM Group x").getResultList();
		return groups;
	}
	/**
	 * Updates the number of attendees for the specified group. 
	 * @param groupNumber - unique id of the group
	 * @param numberOfUsers - number of attendees of the group
	 * @return boolean flag indicated if the group information was updated successfully
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 * @throws MyLogicalInvalidParameterException
	 */
	@Override
	public boolean updatePeopleRegistered(int groupNumber, int numberOfUsers)
			throws DAOSQLException, ResourceCreationException, MyLogicalInvalidParameterException {
		Group group = em.find(Group.class, groupNumber);
		group.setPeopleRegistered(numberOfUsers);
		em.merge(group);
		return true;
	}
	/**
	 * Provides the groups (among specified set of group ids) which still have free spaces for sign up .
	 * @param ids - set of unique ids of random groups
	 * @return the list of available groups for sign up
	 * @throws MyLogicalInvalidParameterException
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getFreeGroups(Integer... ids)
			throws MyLogicalInvalidParameterException, DAOSQLException,
			ResourceCreationException {
		Query q = em.createNamedQuery("selectCapacityAndPeopleInTheGroup");
		List<Group> resultGroups= new ArrayList<Group>();
		for (int i=0; i<ids.length; ++i){
			q.setParameter(1, ids[i]);
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
	/**
	 * Count cost that user should pay for his abonements.
	 * @param groupIds - unique ids of user's groups
	 * @return cost that user should pay for fitness club service / trainings
	 * @throws MyLogicalInvalidParameterException
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
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
	/**
	 * Provides the group based on its' unique id.
	 * @param groupId - specified group unique id
	 * @return group
	 * @throws MyLogicalInvalidParameterException
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
	@Override
	public Group getConcreteGroup(int groupId)
			throws MyLogicalInvalidParameterException, DAOSQLException {
		Query q = em.createNamedQuery("selectGroupById");
		q.setParameter(1, groupId);
		return (Group)q.getSingleResult();
	}

}
