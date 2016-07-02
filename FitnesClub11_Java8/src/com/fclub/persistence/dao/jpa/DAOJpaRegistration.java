package com.fclub.persistence.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fclub.exception.DAOSQLException;
import com.fclub.exception.MyLogicalInvalidParameterException;
import com.fclub.exception.ResourceCreationException;
import com.fclub.persistence.dao.IDAORegistration;
import com.fclub.persistence.model.Group;
import com.fclub.persistence.model.Registration;
import com.fclub.persistence.model.User;
/**
 * This class implements DAO Registration logic with the help of JPA.
 * @author Anastasiya Kisel
 */
@Repository("DAOJpaRegistration")
@Transactional(readOnly=false)
public class DAOJpaRegistration extends DAOJpa implements IDAORegistration {
	/**
	 * Deletes the user from specified groups.
	 * @param userId - user's id
	 * @param recIds - ids of the groups from which user should be deleted
	 * @return boolean flag which indicates if the deletion was successful
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
	@Override
	public boolean deleteUserFromGroups(Integer userId, Integer... recIds)
			throws DAOSQLException, ResourceCreationException {
		Query q = em.createNamedQuery("deleteFromGroupInRegistration");
		for (Integer recId : recIds) {
			q.setParameter(1, userId);
			q.setParameter(2, recId);
			q.executeUpdate();
		}
		return true;
	}
	/**
	 * Deletes users from specified group.
	 * @param groupId - id of the group
	 * @param userIds - ids of users
	 * @return boolean flag which indicates if the deletion was successful
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
	@Override
	public boolean deleteUsersFromGroup(Integer groupId, Integer... userIds)
			throws DAOSQLException, ResourceCreationException {
		Query q = em.createNamedQuery("deleteFromGroupInRegistration");
		for (Integer userId : userIds) {
			q.setParameter(1, userId);
			q.setParameter(2, groupId);
			q.executeUpdate();
		}
		return true;
	}
	/**
	 * Provides the number of attendees of the specified group.
	 * @param groupNumber - number of the group
	 * @return number of attendees in the group
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int countPeopleRegisteredInGroup(int groupNumber)
			throws DAOSQLException, ResourceCreationException {
		Query q = em.createNamedQuery("countClientsInGroup");
		q.setParameter(1, groupNumber);
		List<Long> countList = (List<Long>) q.getResultList();
		return countList.get(0).intValue();
	}
	/**
	 * Provides the set of group unique ids for  the user.
	 * @param userId - id of the user
	 * @return list of user's groups
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> showUserGroupIds(int userId)
			throws DAOSQLException, MyLogicalInvalidParameterException,
			ResourceCreationException {
		List<Integer> userGroupsIds = new ArrayList<Integer>();
		Query q1 = em.createNamedQuery("selectGroupsFromRegistrationByUserId");
		q1.setParameter(1, userId);
		List<Group> groups = (ArrayList<Group>) q1.getResultList();
		for (Group group : groups) {
			userGroupsIds.add(group.getId());
		}
		return userGroupsIds;
	}
	/**
	 * Provides the groups of the specified user.
	 * @param userId - unique id of the user
	 * @return list of user's groups
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Group> showUserGroups(int userId) throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException {
		Query q = em.createNamedQuery("selectGroupsFromRegistrationByUserId");
		q.setParameter(1, userId);
		ArrayList<Group> groups = (ArrayList<Group>) q.getResultList();
		return (ArrayList<Group>) groups;
	}
	/**
	 * Calculates the number of user's abonements.
	 * @param personId - id of the user
	 * @return the number of user's abonements
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int countNumberOfAbonementsForUser(int personId)
			throws DAOSQLException, ResourceCreationException {
		Query q = em.createNamedQuery("countNumberOfAbonementsForUser");
		q.setParameter(1, personId);
		List<Long> countList = (List<Long>) q.getResultList();
		return countList.get(0).intValue();
	}
	/**
	 * Adds user to the specified groups.
	 * @param user - user 
	 * @param recIds - unique ids of the group to which user should be added
	 * @return boolean flag which indicates if the addition was successful.
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean addUserToGroups(User entity, Integer... recIds) {
		
		for (Integer recId : recIds) {
			Registration reg = new Registration();
			reg.setUser(entity);
			reg.setGroup(em.find(Group.class, recId));
			em.persist(reg);
		}
		return true;
	}
	/**
	 * Provides the list of group's attendees.
	 * @param groupNumber - number of the group
	 * @return list of the group's attendees
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<User> getUsersFromGroup(int groupNumber)
			throws DAOSQLException, MyLogicalInvalidParameterException,
			ResourceCreationException {
		Query query = em.createNamedQuery("selectUsersFromGroup");
		query.setParameter(1, groupNumber);
		return (ArrayList<User>) query.getResultList();
	}

}
