/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import by.bsu.kisel.constants.DBConstants;
import by.bsu.kisel.constants.LoggerConstants;
import by.bsu.kisel.dao.IDAOUser;
import by.bsu.kisel.entity.User;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.logic.StatementLogic;
import by.bsu.kisel.util.JdbcUtil;

/**
 *
 * @author Kisel Anastasia
 */
@Component("DAOJdbcUser")
public class DAOJdbcUser extends DAOJdbc implements IDAOUser{
	
    private static final String GET_PERSONS_BY_LOGIN_AND_PASSWORD=
            "SELECT * FROM `person` WHERE login = ? AND password = ?";
    
    @Autowired
    @Qualifier("StatementLogic")
    private StatementLogic statementLogic ;
    
    /**
     * check if user exist in table 'person'
     * @param login 
     * @param password 
     * @return user
     * @throws ResourceCreationException 
     */
    public User checkLogin (String login , String password) throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
    	User user = new User();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
            statement=connection.prepareStatement(GET_PERSONS_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
             if (resultSet.next()){            
                user.setId(resultSet.getInt(DBConstants.PERSON_ID));
                user.setPost(resultSet.getString(DBConstants.PERSON_POST).toUpperCase());
                user.setFirstName(resultSet.getString(DBConstants.PERSON_FIRSTNAME));
                user.setLastName(resultSet.getString(DBConstants.PERSON_LASTNAME));
                user.setAddress(resultSet.getString(DBConstants.PERSON_ADDRESS));
                user.setTelephone(resultSet.getString(DBConstants.PERSON_TELEPHONE));
                user.setDescription(resultSet.getString(DBConstants.PERSON_DESCRIPTION));
                user.setLogin(resultSet.getString(DBConstants.PERSON_LOGIN));
                user.setPassword(resultSet.getString(DBConstants.PERSON_PASSWORD));                
            }
        }  catch (SQLException ex) {
            throw new DAOSQLException(LoggerConstants.DAO_SQL_EXCEPTION, ex);
        }  finally{
        	JdbcUtil.closeStatement(statement);
        	connectionPool.releaseConnection(connection);
        }
        return user;
    }


}
