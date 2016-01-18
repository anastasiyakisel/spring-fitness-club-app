package by.bsu.kisel.dao.jdbc;

import by.bsu.kisel.constants.DBConstants;
import by.bsu.kisel.constants.LoggerConstants;
import by.bsu.kisel.dao.IDAOStatement;
import by.bsu.kisel.entity.Statement;
import by.bsu.kisel.entity.User;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

/**
 *
 * @author Anastasia Kisel
 */
@Component("DAOJdbcStatement")
public class DAOJdbcStatement extends DAOJdbc implements IDAOStatement{
    private static final String IF_USER_EXISTS_IN_STATEMENT=
            "SELECT COUNT(*) FROM `statement` WHERE person_id=(?)";
    private static final String SELECT_USER_FROM_STATEMENT=
            "SELECT * FROM statement st INNER JOIN person p ON st.person_id=p.person_id WHERE st.person_id=(?)";
    private static final String INSERT_INTO_STATEMENT=
            "INSERT INTO `statement` (person_id) VALUES (?)";
    private static final String UPDATE_STATEMENT=
            "UPDATE `statement` SET number_of_abonements=?, discount_percent=?, general_cost=?"
            + " WHERE person_id=?";
    private static final String GET_ALL_USERS_STATEMENTS="SELECT * FROM `person` "
            + "INNER JOIN `statement` ON person.person_id=statement.person_id "
            + "ORDER BY statement.number_of_abonements";
    
     /**
     * check if user exist in the table 'statement'
     * @param personId
     * @return
     * @throws ResourceCreationException 
     */
    public boolean isUserExistInStatement(int personId) throws DAOSQLException, ResourceCreationException{
        Connection connection = null;
        PreparedStatement st = null;
        int columnCount=0;
        try {
            connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
            st=connection.prepareStatement(IF_USER_EXISTS_IN_STATEMENT);
            st.setInt(1, personId);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            columnCount=resultSet.getInt(1);
        } catch (SQLException ex) {
            throw new DAOSQLException(LoggerConstants.DAO_SQL_EXCEPTION, ex);
        }
        finally{
        	JdbcUtil.closeStatement(st);
        	connectionPool.releaseConnection(connection);
        }
        return (columnCount==0) ? false : true;
    }
    /**
     * get all information about the user from table 'statement'
     * @param personId
     * @return userStatement
     * @throws ResourceCreationException 
     */
    public Statement getAllFromStatement (int personId) throws DAOSQLException,
            MyLogicalInvalidParameterException, ResourceCreationException{
        Connection connection = null;
        PreparedStatement st = null;       
        Statement userStatement = new Statement();
        try {
            connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
            st=connection.prepareStatement(SELECT_USER_FROM_STATEMENT);
            st.setInt(1, personId);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()){
                userStatement.setId(resultSet.getInt(DBConstants.STATEMENT_ID));
                User user = new User();
                user.setId(resultSet.getInt(DBConstants.PERSON_ID));
                userStatement.setUser(user);
                userStatement.setNumberOfAbonements(resultSet.getInt(DBConstants.STATEMENT_ABONEMENTS));
                userStatement.setDiscountPercent(resultSet.getInt(DBConstants.DISCOUNT_PERCENT));
                userStatement.setGeneralCost(resultSet.getInt(DBConstants.STATEMENT_COST));
                userStatement.getUser().setPost(resultSet.getString(DBConstants.PERSON_POST).toUpperCase().trim());
                userStatement.getUser().setFirstName(resultSet.getString(DBConstants.PERSON_FIRSTNAME));
                userStatement.getUser().setLastName(resultSet.getString(DBConstants.PERSON_LASTNAME));
                userStatement.getUser().setAddress(resultSet.getString(DBConstants.PERSON_ADDRESS));
                userStatement.getUser().setTelephone(resultSet.getString(DBConstants.PERSON_TELEPHONE));
                userStatement.getUser().setDescription(resultSet.getString(DBConstants.PERSON_DESCRIPTION));
                userStatement.getUser().setLogin(resultSet.getString(DBConstants.PERSON_LOGIN));
                userStatement.getUser().setPassword(resultSet.getString(DBConstants.PERSON_PASSWORD));
            }
        } catch (SQLException ex) {
            throw new DAOSQLException(LoggerConstants.DAO_SQL_EXCEPTION, ex);
        }finally{
        	JdbcUtil.closeStatement(st);
        	connectionPool.releaseConnection(connection);
        } 
        return userStatement;
    }
    /**
     * add information about the concrete user to table 'statement'
     * @param entity 
     * @return
     * NOTE ! Please note that in DB all other columns in statement table should have default value in order this method works fine.
     */
    public boolean add(Statement entity) throws DAOSQLException{
        Connection connection = null;
        PreparedStatement st = null;
        try {
            connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
            st=connection.prepareStatement(INSERT_INTO_STATEMENT);
            st.setInt(1, entity.getUser().getId());
            st.executeUpdate();
        }  catch (SQLException |ResourceCreationException ex) {
            throw new DAOSQLException(LoggerConstants.DAO_SQL_EXCEPTION, ex);
        } 
        finally{
        	JdbcUtil.closeStatement(st);
        	connectionPool.releaseConnection(connection);
        }
        return true;
    }
    /**
     * updates user statement in the table 'statement'
     * @param entity
     * @param recIds
     * @return 
     * @throws ResourceCreationException 
     */
    public boolean update(Statement entity, int numberOfAbonements, int discountPercent, int summCost) throws DAOSQLException, 
            MyLogicalInvalidParameterException, ResourceCreationException{
        Connection connection = null;
        PreparedStatement st = null;
        try {
            connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
            entity.setNumberOfAbonements(numberOfAbonements);
            double generalCost = (discountPercent>0) ? summCost*(100-discountPercent)*0.01 : summCost;
            st=connection.prepareStatement(UPDATE_STATEMENT);
            st.setInt(1, numberOfAbonements);
            st.setInt(2, discountPercent);
            st.setDouble(3, generalCost);
            st.setInt(4, entity.getUser().getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOSQLException(LoggerConstants.DAO_SQL_EXCEPTION, ex); 
        }finally{
        	JdbcUtil.closeStatement(st);
        	connectionPool.releaseConnection(connection);
        } 
        return true;
    }
    
    
   /**
     * get information about all users and their statements
     * @param number 
     * @return userStatements
 * @throws ResourceCreationException 
     */
   public ArrayList<Statement> getAllUserStatements() throws DAOSQLException, 
           MyLogicalInvalidParameterException, ResourceCreationException{
        ArrayList<Statement> statements=new ArrayList<Statement>();
        Connection connection = null;
        PreparedStatement statement=null;
        connection=connectionPool.getConnection(MAX_CONNECTION_WAIT);
        try {
            statement=connection.prepareStatement(GET_ALL_USERS_STATEMENTS);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Statement userStatement = new Statement();
                User user = new User();
                user.setId(rs.getInt(DBConstants.PERSON_ID));
                user.setPost(rs.getString(DBConstants.PERSON_POST).toUpperCase());
                user.setFirstName(rs.getString(DBConstants.PERSON_FIRSTNAME));
                user.setLastName(rs.getString(DBConstants.PERSON_LASTNAME));
                user.setAddress(rs.getString(DBConstants.PERSON_ADDRESS));
                user.setTelephone(rs.getString(DBConstants.PERSON_TELEPHONE));
                user.setDescription(rs.getString(DBConstants.PERSON_DESCRIPTION));
                userStatement.setUser(user);
                userStatement.setNumberOfAbonements(rs.getInt(DBConstants.STATEMENT_ABONEMENTS));
                userStatement.setDiscountPercent(rs.getInt(DBConstants.STATEMENT_DISCOUNT));
                userStatement.setGeneralCost(rs.getInt(DBConstants.STATEMENT_COST));
                statements.add(userStatement);
            }
        } catch (SQLException ex) {
            throw new DAOSQLException(LoggerConstants.DAO_SQL_EXCEPTION, ex);
        } finally {
        	JdbcUtil.closeStatement(statement);
        	connectionPool.releaseConnection(connection);
        }
        return statements;
   } 
    
}
