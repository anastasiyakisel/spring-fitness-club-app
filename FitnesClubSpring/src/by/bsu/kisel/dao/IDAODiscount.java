package by.bsu.kisel.dao;

import java.util.ArrayList;

import by.bsu.kisel.entity.Discount;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

public interface IDAODiscount {
	ArrayList<Discount> getInformationDiscount() throws DAOSQLException,
			MyLogicalInvalidParameterException, ResourceCreationException;
	
	
}
