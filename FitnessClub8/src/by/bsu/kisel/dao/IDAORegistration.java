package by.bsu.kisel.dao;

import java.util.List;

import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.model.Group;
import by.bsu.kisel.model.User;
/**
 * This interface describes the operations that DAO classes for Registration will implement.
 * @author Anastaisiya Kisel
 *
 */
public interface IDAORegistration {
	/**
	 * Deletes the user from specified groups.
	 * @param user - user
	 * @param recIds - ids of the groups from which user should be deleted
	 * @return boolean flag which indicates if the deletion was successful
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
	boolean deleteUserFromGroups(User user, Integer... recIds)
			throws DAOSQLException, ResourceCreationException;
	/**
	 * Deletes users from specified group.
	 * @param groupId - id of the group
	 * @param userIds - ids of users
	 * @return boolean flag which indicates if the deletion was successful
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
	boolean deleteUsersFromGroup(Integer groupId, Integer... userIds)
			throws DAOSQLException, ResourceCreationException;
	
	/**
	 * Adds user to the specified groups.
	 * @param user - user 
	 * @param recIds - unique ids of the group to which user should be added
	 * @return boolean flag which indicates if the addition was successful.
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
	boolean addUserToGroups(User user, Integer... recIds) throws DAOSQLException,
			ResourceCreationException;
	/**
	 * Provides the number of attendees of the specified group.
	 * @param groupNumber - number of the group
	 * @return number of attendees in the group
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
	int countPeopleRegisteredInGroup(int groupNumber) throws DAOSQLException,
			ResourceCreationException;
	/**
	 * Calculates the number of user's abonements.
	 * @param personId - id of the user
	 * @return the number of user's abonements
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
	int countNumberOfAbonementsForUser(int personId) throws DAOSQLException,
		ResourceCreationException;
	/**
	 * Provides the groups of the specified user.
	 * @param userId - unique id of the user
	 * @return list of user's groups
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	List<Group> showUserGroups(int userId) throws DAOSQLException,
		MyLogicalInvalidParameterException, ResourceCreationException;
	/**
	 * Provides the list of group's attendees.
	 * @param groupNumber - number of the group
	 * @return list of the group's attendees
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	List<User> getUsersFromGroup(int groupNumber) throws DAOSQLException,
		MyLogicalInvalidParameterException, ResourceCreationException;
	/**
	 * Provides the set of group unique ids for  the user.
	 * @param userId - id of the user
	 * @return list of user's groups
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	List<Integer> showUserGroupIds(int userId)
			throws DAOSQLException, MyLogicalInvalidParameterException,
			ResourceCreationException;

}
