package by.bsu.kisel.dao;

import java.util.ArrayList;

import by.bsu.kisel.entity.Group;
import by.bsu.kisel.entity.User;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

public interface IDAOGroup {
	ArrayList<Group> showAllGroups(int sporttypeId) throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException;
	
	ArrayList<Group> showUserGroups(int personId) throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException;
	
	boolean deleteUserFromGroups(User user, Integer... recIds)
			throws DAOSQLException, ResourceCreationException;
	
	public boolean deleteUsersFromGroup(Integer groupId, Integer... userIds)
			throws DAOSQLException, ResourceCreationException;
	
	public ArrayList<Group> adminShowAllGroups() throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException;
	
	int countPeopleRegisteredInGroup(int groupNumber) throws DAOSQLException,
			ResourceCreationException;

	boolean updatePeopleRegistered(int groupNumber, int numberOfUsers)
			throws DAOSQLException, ResourceCreationException;
	
	ArrayList<Group> getFreeGroups(Integer... ids) 
            throws MyLogicalInvalidParameterException, DAOSQLException, ResourceCreationException;
	
	
}
