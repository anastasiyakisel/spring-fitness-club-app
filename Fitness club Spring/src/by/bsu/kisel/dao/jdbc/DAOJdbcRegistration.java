package by.bsu.kisel.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import by.bsu.kisel.constants.DBConstants;
import by.bsu.kisel.constants.LoggerConstants;
import by.bsu.kisel.dao.IDAORegistration;
import by.bsu.kisel.entity.Group;
import by.bsu.kisel.entity.Sporttype;
import by.bsu.kisel.entity.Statement;
import by.bsu.kisel.entity.User;
import by.bsu.kisel.enums.SportType;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.util.JdbcUtil;

@Component("DAOJdbcRegistration")
public class DAOJdbcRegistration extends DAOJdbc implements IDAORegistration{
	
    private static final String DELETE_FROM_GROUP_IN_REGISTRATION=
            "DELETE FROM `registration` WHERE person_id=(?) AND group_id=(?)";
    private static final String COUNT_CLIENTS_IN_GROUP=
            "SELECT COUNT(*) FROM `registration` WHERE group_id=(?)";
    private static final String SELECT_USER_GROUPS=
            "SELECT group_id FROM `registration` WHERE person_id=(?)";
    private static final String SELECT_ALL_USER_GROUPS=
            "SELECT * FROM groups gr INNER JOIN sporttype sp ON gr.sporttype_id=sp.sporttype_id WHERE gr.group_id=(?)";
    private static final String COUNT_NUMBER_OF_ABONEMENTS=
            "SELECT COUNT(*) FROM `registration` WHERE person_id=(?)";
    private static final String INSERT_USER_INTO_REGISTRATION=
            "INSERT INTO `registration`(person_id, group_id) VALUES (?, ?) ";
    private static final String SELECT_USERS_FROM_GROUPS = "SELECT * FROM person p JOIN registration r ON"
    		+ " r.person_id = p.person_id WHERE r.group_id = (?)";
    /**
     * delete user from groups
     * @param user
     * @param recIds
     * @return
     * @throws ResourceCreationException 
     */
    public boolean deleteUserFromGroups(User user, Integer... recIds) throws DAOSQLException, ResourceCreationException{
        Connection connection = null;
	PreparedStatement statement = null;
        connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
        try {
            statement = connection.prepareStatement(DELETE_FROM_GROUP_IN_REGISTRATION);
            for (Integer recId: recIds){
				statement.setInt(1, user.getId());
		        statement.setInt(2, recId);
				statement.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new DAOSQLException(LoggerConstants.DAO_SQL_EXCEPTION, ex);
        }finally{
        	JdbcUtil.closeStatement(statement);   
        	connectionPool.releaseConnection(connection);
        }
        return true;
    }

    /**
     * delete users from group
     * @param groupId
     * @param userIds
     * @return
     * @throws ResourceCreationException 
     */
    public boolean deleteUsersFromGroup(Integer groupId, Integer... userIds) throws DAOSQLException, ResourceCreationException{
        Connection connection = null;
	PreparedStatement statement = null;
        connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
        try {
            statement = connection.prepareStatement(DELETE_FROM_GROUP_IN_REGISTRATION);
            for (Integer userId: userIds){
		statement.setInt(1, userId);
                statement.setInt(2, groupId);
		statement.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new DAOSQLException(LoggerConstants.DAO_SQL_EXCEPTION, ex);
        } finally{
        	JdbcUtil.closeStatement(statement);   
        	connectionPool.releaseConnection(connection);
        }            
        return true;
    }
    /**
     * count people registered in the concrete group
     * @param groupNumber
     * @return count
     * @throws ResourceCreationException 
     */
    public int countPeopleRegisteredInGroup(int groupNumber) throws DAOSQLException, ResourceCreationException{
        Connection connection = null;
	PreparedStatement statement =null;
        connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
        int count=0;
        try {
            statement = connection.prepareStatement(COUNT_CLIENTS_IN_GROUP);
            statement.setInt(1, groupNumber);
            ResultSet rs=statement.executeQuery();
            rs.next();
            count=rs.getInt(1);            
        } catch (SQLException ex) {
           throw new DAOSQLException(LoggerConstants.DAO_SQL_EXCEPTION, ex);
        } finally{
        	JdbcUtil.closeStatement(statement);   
        	connectionPool.releaseConnection(connection);
        }
        return count;
    }

    /**
     * get all information about the group and sporttype where user signed up
     * @param userId
     * @return userGroupSporttypes
     * @throws ResourceCreationException 
     */
    public ArrayList<Integer> showUserGroupIds(int userId) 
            throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
        ArrayList<Integer> userGroupIds = new ArrayList<Integer>();
        Connection connection = null;
        PreparedStatement st = null;
        connection=connectionPool.getConnection(MAX_CONNECTION_WAIT);
        try {
            st = connection.prepareStatement(SELECT_USER_GROUPS);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                userGroupIds.add(rs.getInt(DBConstants.GROUP_ID));
            }
        } catch (SQLException ex) {
            throw new DAOSQLException(LoggerConstants.DAO_SQL_EXCEPTION, ex);
        } finally{
        	JdbcUtil.closeStatement(st);
        	connectionPool.releaseConnection(connection);
        }            
        return userGroupIds;
    }
    
    /**
     * get all grooups of the concrete user
     * @param personId
     * @return groups
     * @throws ResourceCreationException 
     */
    public ArrayList<Group> showUserGroups(int personId) throws DAOSQLException, 
            MyLogicalInvalidParameterException, ResourceCreationException{
        ArrayList<Group> groups=new ArrayList<Group>();
        Connection connection = null;
        PreparedStatement statement =null;
        connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
        try {
            statement = connection.prepareStatement(SELECT_USER_GROUPS);
            statement.setInt(1, personId);
            ResultSet rs = statement.executeQuery();
            ArrayList <Integer> groupIds = new ArrayList<Integer>();
            while (rs.next()){
                groupIds.add(rs.getInt(DBConstants.GROUP_ID));
            }
            for (int groupId:groupIds){
                statement=connection.prepareStatement(SELECT_ALL_USER_GROUPS);
                statement.setInt(1, groupId);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                Group group = new Group();
                group.setId(resultSet.getInt(DBConstants.GROUP_ID));
                Sporttype sp = new Sporttype();
                sp.setId(resultSet.getInt(DBConstants.SPORTTYPE_ID));
                group.setSporttype(sp);
                group.setCapacity(resultSet.getInt(DBConstants.GROUP_CAPACITY));
                group.setDaysOfWeek(resultSet.getString(DBConstants.GROUP_DAY_OF_WEEK));
                group.setTimeStart(resultSet.getTime(DBConstants.GROUP_START_TIME));
                group.setDuration(resultSet.getInt(DBConstants.GROUP_DURATION));
                group.setCostAbonement(resultSet.getInt(DBConstants.GROUP_COST));
                group.setPeopleRegistered(resultSet.getInt(DBConstants.GROUP_PEOPLE_REGISTERED));
                group.getSporttype().setCaloriesburned(resultSet.getInt(DBConstants.SPORTTYPE_CALORIES));
                group.getSporttype().setSportType(SportType.valueOf(resultSet.getString(DBConstants.SPORTTYPE_TYPE).toUpperCase().trim()));
                groups.add(group);
            }
        } catch (SQLException ex) {
            throw new DAOSQLException(LoggerConstants.DAO_SQL_EXCEPTION, ex);
        } finally{
        	JdbcUtil.closeStatement(statement);   
        	connectionPool.releaseConnection(connection);
        }
        return groups;
    }
    
    /**
     * get number of abonements of the concrete user
     * @param entity 
     * @return numberOfAbonements
     * @throws DAOSQLException, ResourceCreationException 
     */
	@Override
	public int countNumberOfAbonementsForUser(int personId)
			throws DAOSQLException, ResourceCreationException {
		int numberOfAbonements=0;
        Connection connection = null;
        PreparedStatement st = null;
        try {
            connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
            st=connection.prepareStatement(COUNT_NUMBER_OF_ABONEMENTS);
            st.setInt(1, personId);
            ResultSet rs = st.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            numberOfAbonements=count;
        } catch (SQLException ex) {
            throw new DAOSQLException(LoggerConstants.DAO_SQL_EXCEPTION, ex);
        }finally{
        	JdbcUtil.closeStatement(st);
        	connectionPool.releaseConnection(connection);
        }
        return numberOfAbonements;
	}

    /**
     *insert user into table 'registration'
     * @param entity 
     * @param recIds 
     * @return
     * @throws ResourceCreationException 
     */
	@Override
	public boolean addUserToGroups(User entity, Integer... recIds)
			throws DAOSQLException, ResourceCreationException {
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
            throw new DAOSQLException(LoggerConstants.DAO_SQL_EXCEPTION, ex);
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
	@Override
	public ArrayList<User> getUsersFromGroup(int groupNumber)
			throws DAOSQLException, MyLogicalInvalidParameterException,
			ResourceCreationException {
        ArrayList<User> users=new ArrayList<User>();;
        Connection connection = null;
        PreparedStatement statement=null;	
        connection=connectionPool.getConnection(MAX_CONNECTION_WAIT);
        try {
            statement=connection.prepareStatement(SELECT_USERS_FROM_GROUPS);
            statement.setInt(1, groupNumber);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt(DBConstants.PERSON_ID));
                user.setPost(rs.getString(DBConstants.PERSON_POST).toUpperCase().trim());
                user.setFirstName(rs.getString(DBConstants.PERSON_FIRSTNAME));
                user.setLastName(rs.getString(DBConstants.PERSON_LASTNAME));
                user.setAddress(rs.getString(DBConstants.PERSON_ADDRESS));
                user.setTelephone(rs.getString(DBConstants.PERSON_TELEPHONE));
                user.setDescription(rs.getString(DBConstants.PERSON_DESCRIPTION));
                user.setLogin(rs.getString(DBConstants.PERSON_LOGIN));
                user.setPassword(rs.getString(DBConstants.PERSON_PASSWORD));
                users.add(user);
            }
        } catch (SQLException ex) {
            throw new DAOSQLException(LoggerConstants.DAO_SQL_EXCEPTION, ex);
        } finally {
        	JdbcUtil.closeStatement(statement);
        	connectionPool.releaseConnection(connection);
        }
        return users;
	} 

	
}
