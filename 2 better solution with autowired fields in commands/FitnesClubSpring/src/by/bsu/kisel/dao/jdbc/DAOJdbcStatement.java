package by.bsu.kisel.dao.jdbc;

import by.bsu.kisel.dao.IDAOStatement;
import by.bsu.kisel.entity.Statement;
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
            "SELECT * FROM `statement` WHERE person_id=(?)";
    private static final String INSERT_INTO_STATEMENT=
            "INSERT INTO `statement` (person_id) VALUES (?)";
    private static final String COUNT_NUMBER_OF_ABONEMENTS=
            "SELECT COUNT(*) FROM `registration` WHERE person_id=(?)";
    private static final String COUNT_DISCOUNT_PERCENT=
            "SELECT COUNT(*) FROM `discount` WHERE number_of_abonements <= (?)";
    private static final String GET_COST_OF_GROUPS=
            "SELECT cost FROM `groups` WHERE group_id=(?)"; 
    private static final String SELECT_USER_GROUPS=
            "SELECT group_id FROM `registration` WHERE person_id=(?)";
    private static final String CHOOSE_DISCOUNT_PERCENT=
            "SELECT MAX(discount_percent) FROM `discount` WHERE number_of_abonements <= (?) ";
    private static final String UPDATE_STATEMENT=
            "UPDATE `statement` SET number_of_abonements=?, discount_percent=?, general_cost=?"
            + " WHERE person_id=?";
    
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
            throw new DAOSQLException("Cannot execute query !", ex);
        }
        finally{
        	JdbcUtil.closeStatement(st);
        	connectionPool.releaseConnection(connection);
        }
        if (columnCount==0){
                return false;
            }
            else {
                return true;
            }
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
                userStatement.setId(resultSet.getInt(1));
                userStatement.setPersonId(resultSet.getInt(2));
                userStatement.setNumberOfAbonements(resultSet.getInt(3));
                userStatement.setDiscountPercent(resultSet.getInt(4));
                userStatement.setGeneralCost(resultSet.getInt(5));
            }
        } catch (SQLException ex) {
            throw new DAOSQLException("Cannot execute query !", ex);
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
     */
    public boolean add(Statement entity) throws DAOSQLException{
        Connection connection = null;
        PreparedStatement st = null;
        try {
            connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
            st=connection.prepareStatement(INSERT_INTO_STATEMENT);
            st.setInt(1, entity.getPersonId());
            st.executeUpdate();
        }  catch (SQLException ex) {
            throw new DAOSQLException("Cannot execute query !", ex);
        } catch (ResourceCreationException ex) {			
        	  throw new DAOSQLException("Cannot execute query !", ex);
		}
        finally{
        	JdbcUtil.closeStatement(st);
        	connectionPool.releaseConnection(connection);
        }
        return true;
    }
    /**
     * get number of abonements of the concrete user
     * @param entity 
     * @return numberOfAbonements
     * @throws ResourceCreationException 
     */
    private int countNumberOfAbonements(Statement entity) throws DAOSQLException, ResourceCreationException{
        int numberOfAbonements=0;
        Connection connection = null;
        PreparedStatement st = null;
        try {
            connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
            st=connection.prepareStatement(COUNT_NUMBER_OF_ABONEMENTS);
            st.setInt(1, entity.getPersonId());
            ResultSet rs = st.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            numberOfAbonements=count;
        } catch (SQLException ex) {
            throw new DAOSQLException("Can't execute query !", ex);
        }finally{
        	JdbcUtil.closeStatement(st);
        	connectionPool.releaseConnection(connection);
        }
        return numberOfAbonements;        
    }
    /**
     * counts discount percent of the concrete user
     * @param entity 
     * @return discountPercent
     * @throws ResourceCreationException 
     */
    private int getDiscountPercent(Statement entity) throws DAOSQLException, ResourceCreationException{
        int discountPercent=0;
        Connection connection = null;
        PreparedStatement st = null;
        try {
            connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
            st=connection.prepareStatement(COUNT_DISCOUNT_PERCENT);
            st.setInt(1, entity.getNumberOfAbonements());
            ResultSet rs = st.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count==0){
                discountPercent=0;
            }
            else 
            {
            st=connection.prepareStatement(CHOOSE_DISCOUNT_PERCENT);
            st.setInt(1, entity.getNumberOfAbonements());
            ResultSet resultSet =  st.executeQuery();
            resultSet.next();
            discountPercent=resultSet.getInt(1);
            }
        }  catch (SQLException ex) {
            throw new DAOSQLException("Can't execute query !", ex);
        }finally{
        	JdbcUtil.closeStatement(st);
        	connectionPool.releaseConnection(connection);
        }
        return discountPercent;            
    }
    /**
     * counts cost of user statement without discount 
     * @param entity 
     * @param recIds
     * @return generalCost
     * @throws ResourceCreationException 
     */
    private int countCost(Statement entity, Integer... recIds) throws DAOSQLException, ResourceCreationException{
        int generalCost=0;
        Connection connection = null;
        PreparedStatement st = null;
        try {
            connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
            st=connection.prepareStatement(SELECT_USER_GROUPS);
            st.setInt(1, entity.getPersonId());
            ResultSet rs = st.executeQuery();
            ArrayList <Integer> groupIds = new ArrayList<Integer>();
            while (rs.next()){
                groupIds.add(rs.getInt(1));
            }
            int existSumm=0;
            for (int groupId:groupIds){
                st=connection.prepareStatement(GET_COST_OF_GROUPS);
                st.setInt(1, groupId);
                ResultSet resultSet = st.executeQuery();
                resultSet.next();
                existSumm+=resultSet.getInt(1);
            }
            generalCost=existSumm;
        } catch (SQLException ex) {
            throw new DAOSQLException("Cannot execute query !", ex);
        } finally{
        	JdbcUtil.closeStatement(st);
        	connectionPool.releaseConnection(connection);
        }
        return generalCost;
    }
    /**
     * updates user statement in the table 'statement'
     * @param entity
     * @param recIds
     * @return 
     * @throws ResourceCreationException 
     */
    public boolean update(Statement entity, Integer... recIds) throws DAOSQLException, 
            MyLogicalInvalidParameterException, ResourceCreationException{
        Connection connection = null;
        PreparedStatement st = null;
        try {
            connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
            int resultCountOfAbonements=countNumberOfAbonements(entity);
            entity.setNumberOfAbonements(resultCountOfAbonements);
            //update discount_id
            int discountPercent = getDiscountPercent(entity);
            //update general_cost
            int summCost = countCost(entity, recIds);
            double generalCost;
            if (discountPercent>0){
               generalCost = summCost*(100-discountPercent)*0.01;
            }
            else {
               generalCost = summCost;
            }
            st=connection.prepareStatement(UPDATE_STATEMENT);
            st.setInt(1, resultCountOfAbonements);
            st.setInt(2, discountPercent);
            st.setDouble(3, generalCost);
            st.setInt(4, entity.getPersonId());
            st.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOSQLException("Can't execute query !", ex); 
        }finally{
        	JdbcUtil.closeStatement(st);
        	connectionPool.releaseConnection(connection);
        } 
        return true;
    }
    
}
