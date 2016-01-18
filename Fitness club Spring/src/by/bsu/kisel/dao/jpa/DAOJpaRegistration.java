package by.bsu.kisel.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.bsu.kisel.dao.IDAORegistration;
import by.bsu.kisel.entity.Group;
import by.bsu.kisel.entity.Registration;
import by.bsu.kisel.entity.User;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

@Repository("DAOJpaRegistration")
public class DAOJpaRegistration extends DAOJpa implements IDAORegistration {
	@Override
	@Transactional
	public boolean deleteUserFromGroups(User user, Integer... recIds)
			throws DAOSQLException, ResourceCreationException {
		Query q = em.createNamedQuery("deleteFromGroupInRegistration");
		for (Integer recId : recIds) {
			q.setParameter(1, user.getId());
			q.setParameter(2, recId);
			q.executeUpdate();
		}
		return true;
	}

	@Override
	@Transactional
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

	@Override
	public int countPeopleRegisteredInGroup(int groupNumber)
			throws DAOSQLException, ResourceCreationException {
		Query q = em.createNamedQuery("countClientsInGroup");
		q.setParameter(1, groupNumber);
		List<Long> countList = (List<Long>) q.getResultList();
		return countList.get(0).intValue();
	}

	@Override
	public ArrayList<Integer> showUserGroupIds(int userId)
			throws DAOSQLException, MyLogicalInvalidParameterException,
			ResourceCreationException {
		ArrayList<Integer> userGroupsIds = new ArrayList<Integer>();
		Query q1 = em.createNamedQuery("selectGroupsFromRegistrationByUserId");
		q1.setParameter(1, userId);
		ArrayList<Group> groups = (ArrayList<Group>) q1.getResultList();
		for (Group group : groups){
			userGroupsIds.add(group.getId());
		}
		return userGroupsIds;
	}

	@Override
	public ArrayList<Group> showUserGroups(int userId) throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException {
		Query q = em.createNamedQuery("selectGroupsFromRegistrationByUserId");
		q.setParameter(1, userId);
		ArrayList<Group> groups = (ArrayList<Group>)q.getResultList();
		return (ArrayList<Group>)groups;
	}

	@Override
	public int countNumberOfAbonementsForUser(int personId)
			throws DAOSQLException, ResourceCreationException {
		Query q = em.createNamedQuery("countNumberOfAbonementsForUser");
		q.setParameter(1, personId);
		List<Long> countList = (List<Long>) q.getResultList();
		return countList.get(0).intValue();
	}

	@Override
	@Transactional
	public boolean addUserToGroups(User entity, Integer... recIds)
			throws DAOSQLException, ResourceCreationException {
		for (Integer recId : recIds){
			Registration reg = new Registration();
			reg.setUser(entity);
			reg.setGroup(em.find(Group.class, recId));
			em.persist(reg);
		}
		return true;
	}

	@Override	
	public ArrayList<User> getUsersFromGroup(int groupNumber)
			throws DAOSQLException, MyLogicalInvalidParameterException,
			ResourceCreationException {
		Query query = em.createNamedQuery("selectUsersFromGroup");
		query.setParameter(1, groupNumber);
		return (ArrayList<User>) query.getResultList();
	}
	
}
