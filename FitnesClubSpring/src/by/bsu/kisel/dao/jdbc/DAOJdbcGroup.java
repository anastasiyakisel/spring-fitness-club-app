/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.dao.jdbc;

import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.entity.Group;
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
@Component("DAOJdbcGroup")
public class DAOJdbcGroup extends DAOJdbc implements IDAOGroup{
    private static final String GET_GROUPS_BY_SPORTTYPE=
            "SELECT * FROM `groups` WHERE sporttype_id = (?)";
    private static final String SELECT_USER_GROUPS=
            "SELECT group_id FROM `registration` WHERE person_id=(?)";
    private static final String SELECT_ALL_USER_GROUPS=
            "SELECT * FROM `groups` WHERE group_id=(?)";
    private static final String DELETE_FROM_GROUP_IN_REGISTRATION=
            "DELETE FROM `registration` WHERE person_id=(?) AND group_id=(?)";
    private static final String ADMIN_SELECT_ALL_GROUPS=
            "SELECT * FROM `groups`";
    private static final String SELECT_REGISTERED_PEOPLE_AND_CAPACITY_OF_GROUP=
            "SELECT capacity, people_registered FROM `groups` WHERE group_id=(?)";
    private static final String UPDATE_PEOPLE_REGISTERED_IN_GROUP=
            "UPDATE `groups` SET people_registered=(?) WHERE group_id=(?)";
    private static final String COUNT_CLIENTS_IN_GROUP=
            "SELECT COUNT(*) FROM `registration` WHERE group_id=(?)";
    
    
    /**
     * get all grooups of the concrete sporttype
     * @param sporttypeId
     * @return groups
     * @throws ResourceCreationException 
     */
    public ArrayList<Group> showAllGroups(int sporttypeId) throws DAOSQLException,
            MyLogicalInvalidParameterException, ResourceCreationException{
        ArrayList<Group> groups=new ArrayList<Group>();
        Connection connection = null;
	PreparedStatement statement =null;
        connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
        try {
            statement = connection.prepareStatement(GET_GROUPS_BY_SPORTTYPE);
            statement.setInt(1, sporttypeId);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Group group = new Group();
                group.setId(resultSet.getInt(1));
                group.setSporttypeID(resultSet.getInt(2));
                group.setCapacity(resultSet.getInt(3));
                String days = resultSet.getString(4);
                group.setDaysOfWeek(days);
                group.setTimeStart(resultSet.getTime(5));
                group.setDuration(resultSet.getInt(6));
                group.setCostAbonement(resultSet.getInt(7));
                group.setPeopleRegistered(resultSet.getInt(8));
                groups.add(group);
            }         
        } catch (SQLException ex) {
            throw new DAOSQLException("Cannot execute query", ex);
        } finally{
        	JdbcUtil.closeStatement(statement);   
            connectionPool.releaseConnection(connection);
        }
        return groups;
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
                groupIds.add(rs.getInt(1));
            }
            for (int groupId:groupIds){
                statement=connection.prepareStatement(SELECT_ALL_USER_GROUPS);
                statement.setInt(1, groupId);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                Group group = new Group();
                group.setId(resultSet.getInt(1));
                group.setSporttypeID(resultSet.getInt(2));
                group.setCapacity(resultSet.getInt(3));
                group.setDaysOfWeek(resultSet.getString(4));
                group.setTimeStart(resultSet.getTime(5));
                group.setDuration(resultSet.getInt(6));
                group.setCostAbonement(resultSet.getInt(7));
                group.setPeopleRegistered(resultSet.getInt(8));
                groups.add(group);
            }
        } catch (SQLException ex) {
            throw new DAOSQLException("Can't execute query !", ex);
        } finally{
        	JdbcUtil.closeStatement(statement);   
        	connectionPool.releaseConnection(connection);
        }
        return groups;
    }
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
            throw new DAOSQLException("Can't execute query !", ex);
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
            throw new DAOSQLException("Can't execute query!", ex);
        } finally{
        	JdbcUtil.closeStatement(statement);   
        	connectionPool.releaseConnection(connection);
        }
            
        return true;
    }
    /**
     * get all groups of the fitness-club
     * @return groups
     * @throws ResourceCreationException 
     */
    public ArrayList<Group> adminShowAllGroups() throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
        ArrayList<Group> groups=new ArrayList<Group>();
        Connection connection = null;
	PreparedStatement statement =null;
        connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
        try {
            statement = connection.prepareStatement(ADMIN_SELECT_ALL_GROUPS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Group group = new Group();
                group.setId(resultSet.getInt(1));
                group.setSporttypeID(resultSet.getInt(2));
                group.setCapacity(resultSet.getInt(3));
                group.setDaysOfWeek(resultSet.getString(4));
                group.setTimeStart(resultSet.getTime(5));
                group.setDuration(resultSet.getInt(6));
                group.setCostAbonement(resultSet.getInt(7));
                group.setPeopleRegistered(resultSet.getInt(8));
                groups.add(group);
            }
        } catch (SQLException ex) {
            throw new DAOSQLException("Can't execute query!", ex);
        } finally{
        	JdbcUtil.closeStatement(statement);   
        	connectionPool.releaseConnection(connection);
        }
        return groups;
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
           throw new DAOSQLException("Can't execute query!", ex);
        } finally{
        	JdbcUtil.closeStatement(statement);   
        	connectionPool.releaseConnection(connection);
        }
        return count;
    }
    /**
     * update people registered in the concrete group
     * @param groupNumber
     * @param numberOfUsers 
     * @return
     * @throws ResourceCreationException 
     */
    public boolean updatePeopleRegistered(int groupNumber, int numberOfUsers) throws DAOSQLException, ResourceCreationException{
        Connection connection = null;
	PreparedStatement statement =null;
        connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
        try {
            statement = connection.prepareStatement(UPDATE_PEOPLE_REGISTERED_IN_GROUP);
            statement.setInt(1, numberOfUsers);
            statement.setInt(2, groupNumber);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOSQLException("Can't execute query!", ex);
        }
        finally{
        	JdbcUtil.closeStatement(statement);   
        	connectionPool.releaseConnection(connection);
        }
        return true;
    }
    /**
     * get groups where abonements still exist
     * @param ids 
     * @return groups
     * @throws ResourceCreationException 
     */
    public ArrayList<Group> getFreeGroups(Integer... ids) 
            throws MyLogicalInvalidParameterException, DAOSQLException, ResourceCreationException{
        ArrayList<Group> groups=new ArrayList<Group>();
        ArrayList<Group> resultGroups= new ArrayList<Group>();
        for (int i=0; i<ids.length; ++i){
            Group group = new Group();
            group.setId(ids[i]);
            groups.add(group);
        }
        Connection connection = null;
	PreparedStatement statement =null;
        connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
        try {
            for (int i=0; i<ids.length; ++i){
                Group group = groups.get(i); 
                statement = connection.prepareStatement(SELECT_REGISTERED_PEOPLE_AND_CAPACITY_OF_GROUP);
                statement.setInt(1, group.getId());
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                    group.setCapacity(rs.getInt(1));
                    group.setPeopleRegistered(rs.getInt(2));
                }
                if (group.getCapacity()-group.getPeopleRegistered()>0){
                    resultGroups.add(group);
                }
            }
        } catch (SQLException ex) {
            throw new DAOSQLException("Can't execute query!", ex);
        } finally{
        	JdbcUtil.closeStatement(statement);   
        	connectionPool.releaseConnection(connection);
        }
        return resultGroups;
    }


}
