package by.bsu.kisel.dao;

import java.util.ArrayList;

import by.bsu.kisel.entity.GroupSporttype;
import by.bsu.kisel.entity.Sporttype;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

public interface IDAOSporttype {
	ArrayList<Sporttype> showSporttypes(Integer... ids) throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException;

	ArrayList<GroupSporttype> showuserGroupSporttypes(int userId) 
            throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException;
	
	
}
