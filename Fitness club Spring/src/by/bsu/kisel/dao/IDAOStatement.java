package by.bsu.kisel.dao;

import java.util.ArrayList;

import by.bsu.kisel.entity.Statement;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

public interface IDAOStatement {
	boolean isUserExistInStatement(int personId) throws DAOSQLException,
			ResourceCreationException;

	Statement getAllFromStatement(int personId) throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException;

	boolean add(Statement entity) throws DAOSQLException;
	
	public boolean update(Statement stat, int numberOfAbonements, int discountPercent, int summCost)
			throws DAOSQLException, MyLogicalInvalidParameterException,
			ResourceCreationException;
	
	ArrayList<Statement> getAllUserStatements() throws DAOSQLException, 
    	MyLogicalInvalidParameterException, ResourceCreationException;

}
