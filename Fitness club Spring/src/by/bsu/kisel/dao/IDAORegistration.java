package by.bsu.kisel.dao;

import java.util.ArrayList;

import by.bsu.kisel.entity.Group;
import by.bsu.kisel.entity.User;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

public interface IDAORegistration {
	
	boolean deleteUserFromGroups(User user, Integer... recIds)
			throws DAOSQLException, ResourceCreationException;
		
	boolean deleteUsersFromGroup(Integer groupId, Integer... userIds)
			throws DAOSQLException, ResourceCreationException;
	

	boolean addUserToGroups(User entity, Integer... recIds) throws DAOSQLException,
			ResourceCreationException;
		
	int countPeopleRegisteredInGroup(int groupNumber) throws DAOSQLException,
			ResourceCreationException;
	
	int countNumberOfAbonementsForUser(int personId) throws DAOSQLException,
		ResourceCreationException;
	
	ArrayList<Group> showUserGroups(int userId) throws DAOSQLException,
		MyLogicalInvalidParameterException, ResourceCreationException;

	ArrayList<User> getUsersFromGroup(int groupNumber) throws DAOSQLException,
		MyLogicalInvalidParameterException, ResourceCreationException;

	ArrayList<Integer> showUserGroupIds(int userId)
			throws DAOSQLException, MyLogicalInvalidParameterException,
			ResourceCreationException;

}
