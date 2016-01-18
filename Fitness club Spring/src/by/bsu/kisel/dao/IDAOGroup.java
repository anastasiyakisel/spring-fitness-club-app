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
	
	ArrayList<Group> adminShowAllGroups() throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException;

	boolean updatePeopleRegistered(int groupNumber, int numberOfUsers)
			throws DAOSQLException, ResourceCreationException,  MyLogicalInvalidParameterException;
	
	ArrayList<Group> getFreeGroups(Integer... ids) 
            throws MyLogicalInvalidParameterException, DAOSQLException, ResourceCreationException;
	
	int countCostOfAllUsersGroups(int [] groupIds)
			throws MyLogicalInvalidParameterException, DAOSQLException,	ResourceCreationException;
	
	Group getConcreteGroup(int groupId)
			throws MyLogicalInvalidParameterException, DAOSQLException,
			ResourceCreationException;
}
