package com.fclub.persistence.dao;

import java.util.List;

import com.fclub.exception.DAOSQLException;
import com.fclub.exception.MyLogicalInvalidParameterException;
import com.fclub.exception.ResourceCreationException;
import com.fclub.persistence.model.Group;
/**
 * This interface describes the operations that DAO classes for Group will implement.
 * @author Anastasiya Kisel
 *
 */
public interface IDAOGroup {
	/**
	 * Provides the groups of the concrete sport type.
	 * @param sporttypeId - id of the sport type
	 * @return the list of the groups for the specified sport type
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	List<Group> showAllGroups(int sporttypeId) throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException;
	/**
	 * Provides the information about all the groups in the fitness club.
	 * @return the list of all the groups in the fitness club
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	List<Group> adminShowAllGroups() throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException;
	/**
	 * Updates the number of attendees for the specified group. 
	 * @param groupNumber - unique id of the group
	 * @param numberOfUsers - number of attendees of the group
	 * @return boolean flag indicated if the group information was updated successfully
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 * @throws MyLogicalInvalidParameterException
	 */
	boolean updatePeopleRegistered(int groupNumber, int numberOfUsers)
			throws DAOSQLException, ResourceCreationException,  MyLogicalInvalidParameterException;
	/**
	 * Provides the groups (among specified set of group ids) which still have free spaces for sign up .
	 * @param ids - set of unique ids of random groups
	 * @return the list of available groups for sign up
	 * @throws MyLogicalInvalidParameterException
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
	List<Group> getFreeGroups(Integer... ids) 
            throws MyLogicalInvalidParameterException, DAOSQLException, ResourceCreationException;
	/**
	 * Count cost that user should pay for his abonements.
	 * @param groupIds - unique ids of user's groups
	 * @return cost that user should pay for fitness club service / trainings
	 * @throws MyLogicalInvalidParameterException
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
	int countCostOfAllUsersGroups(int [] groupIds)
			throws MyLogicalInvalidParameterException, DAOSQLException,	ResourceCreationException;
	/**
	 * Provides the group based on its' unique id.
	 * @param groupId - specified group unique id
	 * @return group
	 * @throws MyLogicalInvalidParameterException
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
	Group getConcreteGroup(int groupId)
			throws MyLogicalInvalidParameterException, DAOSQLException,
			ResourceCreationException;
}
