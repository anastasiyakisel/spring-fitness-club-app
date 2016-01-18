/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.dao.jdbc;

import by.bsu.kisel.dao.IDAOUser;
import by.bsu.kisel.entity.User;
import by.bsu.kisel.entity.UserStatement;
import by.bsu.kisel.enums.Post;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.logic.StatementLogic;
import by.bsu.kisel.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

/**
 *
 * @author Kisel Anastasia
 */
@Component("DAOJdbcUser")
public class DAOJdbcUser extends DAOJdbc implements IDAOUser{
    private static final String GET_PERSONS_BY_LOGIN_AND_PASSWORD=
            "SELECT * FROM `person` WHERE login = ? AND password = ?";
    private static final String INSERT_USER_INTO_REGISTRATION=
            "INSERT INTO `registration`(person_id, group_id) VALUES (?, ?) ";
    private static final String GET_USERS_BY_ID=
            "SELECT * FROM  `person` WHERE person_id=(?) ";
    private static final String SELECT_USERID_OF_GROUP=
            "SELECT person_id FROM `registration` WHERE group_id=(?)";
    private static final String SELECT_ALL_PERSONS="SELECT * FROM `person`";
    private static final String GET_ALL_USERS="SELECT * FROM `person` "
            + "INNER JOIN `statement` ON person.person_id=statement.person_id "
            + "ORDER BY statement.number_of_abonements";
    
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
            
                user.setId(resultSet.getInt(1));
                user.setPost(Post.valueOf(resultSet.getString(2).toUpperCase()));
                user.setFirstName(resultSet.getString(3));
                user.setLastName(resultSet.getString(4));
                user.setAddress(resultSet.getString(5));
                user.setTelephone(resultSet.getString(6));
                user.setDescription(resultSet.getString(7));
                user.setLogin(resultSet.getString(8));
                user.setPassword(resultSet.getString(9));                
            }
        }  catch (SQLException ex) {
            throw new DAOSQLException("Cannot execute query !", ex);
        }  finally{
        	JdbcUtil.closeStatement(statement);
        	connectionPool.releaseConnection(connection);
        }
        return user;
    }

    /**
     *insert user into table 'registration'
     * @param entity 
     * @param recIds 
     * @return
     * @throws ResourceCreationException 
     */
    public boolean add(User entity, Integer... recIds) throws DAOSQLException, ResourceCreationException {
        Connection connection = null;
        PreparedStatement statement=null;
        try {
            connection=connectionPool.getConnection(MAX_CONNECTION_WAIT);
            statement=connection.prepareStatement(INSERT_USER_INTO_REGISTRATION);
            for (Integer recId : recIds){
                statement.setInt(1, entity.getId());
                statement.setInt(2, recId);
                statement.executeUpdate();
            }
            
        } catch (SQLException ex) {
            throw new DAOSQLException("Cannot execute query !", ex);
        } finally {
        	JdbcUtil.closeStatement(statement);
        	connectionPool.releaseConnection(connection);
        }
        return true;
    }

    /**
     * get users of the concrete groups
     * @param number 
     * @return users
     * @throws ResourceCreationException 
     */
    public ArrayList<User> getUsersFromGroup(int number) throws DAOSQLException, 
            MyLogicalInvalidParameterException, ResourceCreationException{
        ArrayList<User> users=null;
        Connection connection = null;
        PreparedStatement statement=null;
        connection=connectionPool.getConnection(MAX_CONNECTION_WAIT);
        try {
            statement=connection.prepareStatement(SELECT_USERID_OF_GROUP);
            statement.setInt(1, number);
            ResultSet rs=statement.executeQuery();
            ArrayList<Integer> personIds = new ArrayList<Integer>();
            while(rs.next()){
                personIds.add(rs.getInt(1));
            }
            users=new ArrayList<User>();
            
            for(Integer personId: personIds){
                statement=connection.prepareStatement(GET_USERS_BY_ID);
                statement.setInt(1, personId);
                ResultSet rs2=statement.executeQuery();
                rs2.next();
                User user = new User();
                user.setId(rs2.getInt(1));             
                user.setPost(Post.valueOf(rs2.getString(2).toUpperCase().trim()));
                user.setFirstName(rs2.getString(3));
                user.setLastName(rs2.getString(4));
                user.setAddress(rs2.getString(5));
                user.setTelephone(rs2.getString(6));
                user.setDescription(rs2.getString(7));
                user.setLogin(rs2.getString(8));
                user.setPassword(rs2.getString(9));
                users.add(user);
            }
        } catch (SQLException ex) {
            throw new DAOSQLException("Can't execute query !", ex);
        } finally {
        	JdbcUtil.closeStatement(statement);
        	connectionPool.releaseConnection(connection);
        }
        return users;
    }
   /**
     * get information about all users and their statements
     * @param number 
     * @return userStatements
 * @throws ResourceCreationException 
     */
   public ArrayList<UserStatement> getAllUserStatements() throws DAOSQLException, 
           MyLogicalInvalidParameterException, ResourceCreationException{
        ArrayList<UserStatement> userStatements=new ArrayList<UserStatement>();
        Connection connection = null;
        PreparedStatement statement=null;
        connection=connectionPool.getConnection(MAX_CONNECTION_WAIT);
        try {
            statement=connection.prepareStatement(SELECT_ALL_PERSONS);
            ResultSet rs=statement.executeQuery();
            ArrayList<Integer> personIds=new ArrayList<Integer>();
            ArrayList<User> users=new ArrayList<User>();
            while(rs.next()){
                personIds.add(rs.getInt(1));   
                User user = new User();
                user.setId(rs.getInt(1));
                users.add(user);
            }
            for(User user:users){
                StatementLogic.getStatementOfUser(user);
            }
            statement=connection.prepareStatement(GET_ALL_USERS);
            rs = statement.executeQuery();
            while (rs.next()){
                UserStatement userStatement = new UserStatement();
                userStatement.getUser().setId(rs.getInt(1));
                userStatement.getUser().setPost(Post.valueOf(rs.getString(2).toUpperCase()));
                userStatement.getUser().setFirstName(rs.getString(3));
                userStatement.getUser().setLastName(rs.getString(4));
                userStatement.getUser().setAddress(rs.getString(5));
                userStatement.getUser().setTelephone(rs.getString(6));
                userStatement.getUser().setDescription(rs.getString(7));
                userStatement.getStatement().setNumberOfAbonements(rs.getInt(12));
                userStatement.getStatement().setDiscountPercent(rs.getInt(13));
                userStatement.getStatement().setGeneralCost(rs.getInt(14));
                userStatements.add(userStatement);
            }
        } catch (SQLException ex) {
            throw new DAOSQLException("Can't execute query!", ex);
        } finally {
        	JdbcUtil.closeStatement(statement);
        	connectionPool.releaseConnection(connection);
        }
        return userStatements;
   } 

}
