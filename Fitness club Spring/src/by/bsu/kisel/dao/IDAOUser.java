package by.bsu.kisel.dao;

import java.util.ArrayList;

import by.bsu.kisel.entity.User;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

public interface IDAOUser {
	User checkLogin(String login, String password) throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException;

}
