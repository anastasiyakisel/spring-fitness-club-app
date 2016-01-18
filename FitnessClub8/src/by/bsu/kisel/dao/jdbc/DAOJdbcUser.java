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
import by.bsu.kisel.constants.ErrorConstants;
import by.bsu.kisel.dao.IDAOUser;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.logic.StatementLogic;
import by.bsu.kisel.model.User;
import by.bsu.kisel.util.JdbcUtil;

/**
 * This class implements DAO User logic with the help of JDBC.
 * @author Anastasiya Kisel
 */
@Component("DAOJdbcUser")
public class DAOJdbcUser extends DAOJdbc implements IDAOUser{
	
    private static final String GET_PERSONS_BY_LOGIN_AND_PASSWORD_STATEMENT=
            "SELECT * FROM `person` WHERE login = ? AND password = ?";
    
    private static final String CREATE_USER_STATEMENT="INSERT INTO `person` (firstname,lastname,address,telephone,description,post,login,password) "
    		+ "VALUES (?,?,?,?,?,?,?,?);";
    
    @Autowired
    @Qualifier("StatementLogic")
    private StatementLogic statementLogic ;
    
	/**
	 * Checks if the user with specified login and password exists in the database.
	 * @param login - login 
	 * @param password - password
	 * @return User object if login was successful or null if login failed
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
    public User checkLogin (String login , String password) throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
    	User user = new User();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
            statement=connection.prepareStatement(GET_PERSONS_BY_LOGIN_AND_PASSWORD_STATEMENT);
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
            throw new DAOSQLException(ErrorConstants.DAO_SQL_EXCEPTION, ex);
        }  finally{
        	JdbcUtil.closeStatement(statement);
        	connectionPool.releaseConnection(connection);
        }
        return user;
    }

	@Override
	public User create(User user) throws ResourceCreationException, DAOSQLException {
		Connection connection = null;
	    PreparedStatement statement = null;
	    connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
	    try {
			statement=connection.prepareStatement(CREATE_USER_STATEMENT);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getAddress());
			statement.setString(4, user.getTelephone());
			statement.setString(5, user.getDescription());
			statement.setString(6, user.getPost());
			statement.setString(7, user.getLogin());
			statement.setString(8, user.getPassword());
			statement.executeUpdate();
		} catch (SQLException ex) {
			throw new DAOSQLException(ErrorConstants.DAO_SQL_EXCEPTION, ex);
		} finally {
        	JdbcUtil.closeStatement(statement);
        	connectionPool.releaseConnection(connection);
        }
		return user;
	}


}
