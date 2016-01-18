package by.bsu.kisel.dao.jdbc;

import by.bsu.kisel.constants.DBConstants;
import by.bsu.kisel.constants.ErrorConstants;
import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.util.JdbcUtil;
import by.bsu.kisel.enums.SportType;
import by.bsu.kisel.model.Group;
import by.bsu.kisel.model.Sporttype;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * This class implements DAO Group logic with the help of JDBC.
 * @author Anastasiya Kisel
 */
@Component("DAOJdbcGroup")
public class DAOJdbcGroup extends DAOJdbc implements IDAOGroup{
    private static final String GET_GROUPS_BY_SPORTTYPE_STATEMENT=
            "SELECT * FROM `groups` WHERE sporttype_id = (?)";
    private static final String ADMIN_SELECT_ALL_GROUPS_STATEMENT=
            "SELECT * FROM groups gr INNER JOIN sporttype sp ON gr.sporttype_id=sp.sporttype_id";
    private static final String SELECT_REGISTERED_PEOPLE_AND_CAPACITY_OF_GROUP_STATEMENT=
            "SELECT capacity, people_registered FROM `groups` WHERE group_id=(?)";
    private static final String UPDATE_PEOPLE_REGISTERED_IN_GROUP_STATEMENT=
            "UPDATE `groups` SET people_registered=(?) WHERE group_id=(?)";
    private static final String GET_COST_OF_GROUPS_STATEMENT=
            "SELECT cost FROM `groups` WHERE group_id=(?)";
    private static final String GET_CONCRETE_GROUP_STATEMENT="SELECT * FROM groups gr INNER JOIN sporttype sp ON gr.sporttype_id=sp.sporttype_id "
    		+ "WHERE group_id=(?)";
    
	/**
	 * Provides the groups of the concrete sport type.
	 * @param sporttypeId - id of the sport type
	 * @return the list of the groups for the specified sport type
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
    public List<Group> showAllGroups(int sporttypeId) throws DAOSQLException,
            MyLogicalInvalidParameterException, ResourceCreationException{
        List<Group> groups=new ArrayList<Group>();
        Connection connection = null;
        PreparedStatement statement =null;
        connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
        try {
            statement = connection.prepareStatement(GET_GROUPS_BY_SPORTTYPE_STATEMENT);
            statement.setInt(1, sporttypeId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Group group = new Group();
                group.setId(resultSet.getInt(DBConstants.GROUP_ID));
                Sporttype sp = new Sporttype();
                sp.setId(resultSet.getInt(DBConstants.SPORTTYPE_ID));
                group.setSporttype(sp);
                group.setCapacity(resultSet.getInt(DBConstants.GROUP_CAPACITY));
                String days = resultSet.getString(DBConstants.GROUP_DAY_OF_WEEK);
                group.setDaysOfWeek(days);
                group.setTimeStart(resultSet.getTime(DBConstants.GROUP_START_TIME));
                group.setDuration(resultSet.getInt(DBConstants.GROUP_DURATION));
                group.setCostAbonement(resultSet.getInt(DBConstants.GROUP_COST));
                group.setPeopleRegistered(resultSet.getInt(DBConstants.GROUP_PEOPLE_REGISTERED));
                groups.add(group);
            }         
        } catch (SQLException ex) {
            throw new DAOSQLException(ErrorConstants.DAO_SQL_EXCEPTION, ex);
        } finally{
        	JdbcUtil.closeStatement(statement);   
            connectionPool.releaseConnection(connection);
        }
        return groups;
    }
    
	/**
	 * Provides the information about all the groups in the fitness club.
	 * @return the list of all the groups in the fitness club
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
    public List<Group> adminShowAllGroups() throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
        List<Group> groups=new ArrayList<Group>();
        Connection connection = null;
        PreparedStatement statement =null;
        connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
        try {
            statement = connection.prepareStatement(ADMIN_SELECT_ALL_GROUPS_STATEMENT);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
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
                group.getSporttype().setSportType( SportType.valueOf(resultSet.getString(DBConstants.SPORTTYPE_TYPE).toUpperCase().trim()));
                groups.add(group);
            }
        } catch (SQLException ex) {
            throw new DAOSQLException(ErrorConstants.DAO_SQL_EXCEPTION, ex);
        } finally{
        	JdbcUtil.closeStatement(statement);   
        	connectionPool.releaseConnection(connection);
        }
        return groups;
    }
	/**
	 * Updates the number of attendees for the specified group. 
	 * @param groupNumber - unique id of the group
	 * @param numberOfUsers - number of attendees of the group
	 * @return boolean flag indicated if the group information was updated successfully
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 * @throws MyLogicalInvalidParameterException
	 */
    public boolean updatePeopleRegistered(int groupNumber, int numberOfUsers) throws DAOSQLException, ResourceCreationException{
        Connection connection = null;
        PreparedStatement statement =null;
        connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
        try {
            statement = connection.prepareStatement(UPDATE_PEOPLE_REGISTERED_IN_GROUP_STATEMENT);
            statement.setInt(1, numberOfUsers);
            statement.setInt(2, groupNumber);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOSQLException(ErrorConstants.DAO_SQL_EXCEPTION, ex);
        }
        finally{
        	JdbcUtil.closeStatement(statement);   
        	connectionPool.releaseConnection(connection);
        }
        return true;
    }
	/**
	 * Provides the groups (among specified set of group ids) which still have free spaces for sign up .
	 * @param ids - set of unique ids of random groups
	 * @return the list of available groups for sign up
	 * @throws MyLogicalInvalidParameterException
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
    public List<Group> getFreeGroups(Integer... ids) 
            throws MyLogicalInvalidParameterException, DAOSQLException, ResourceCreationException{
        List<Group> groups=new ArrayList<Group>();
        List<Group> resultGroups= new ArrayList<Group>();
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
                statement = connection.prepareStatement(SELECT_REGISTERED_PEOPLE_AND_CAPACITY_OF_GROUP_STATEMENT);
                statement.setInt(1, group.getId());
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                    group.setCapacity(rs.getInt(DBConstants.GROUP_CAPACITY));
                    group.setPeopleRegistered(rs.getInt(DBConstants.GROUP_PEOPLE_REGISTERED));
                }
                if (group.getCapacity()-group.getPeopleRegistered()>0){
                    resultGroups.add(group);
                }
            }
        } catch (SQLException ex) {
            throw new DAOSQLException(ErrorConstants.DAO_SQL_EXCEPTION, ex);
        } finally{
        	JdbcUtil.closeStatement(statement);   
        	connectionPool.releaseConnection(connection);
        }
        return resultGroups;
    }
    
	/**
	 * Count cost that user should pay for his abonements.
	 * @param groupIds - unique ids of user's groups
	 * @return cost that user should pay for fitness club service / trainings
	 * @throws MyLogicalInvalidParameterException
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
	@Override
	public int countCostOfAllUsersGroups(int [] groupIds)
			throws MyLogicalInvalidParameterException, DAOSQLException,
			ResourceCreationException {
		int generalCost=0;
        Connection connection = null;
        PreparedStatement st = null;
        try {
            connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
            int existSumm=0;
            for (int recId:groupIds){
                st=connection.prepareStatement(GET_COST_OF_GROUPS_STATEMENT);
                st.setInt(1, recId);
                ResultSet resultSet = st.executeQuery();
                resultSet.next();
                existSumm+=resultSet.getInt(1);
            }
            generalCost=existSumm;
        } catch (SQLException ex) {
            throw new DAOSQLException(ErrorConstants.DAO_SQL_EXCEPTION, ex);
        } finally{
        	JdbcUtil.closeStatement(st);
        	connectionPool.releaseConnection(connection);
        }
        return generalCost;
	}
	/**
	 * Provides the group based on its' unique id.
	 * @param groupId - specified group unique id
	 * @return group
	 * @throws MyLogicalInvalidParameterException
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
	@Override
	public Group getConcreteGroup(int groupId)
			throws MyLogicalInvalidParameterException, DAOSQLException, ResourceCreationException {
		Group group = new Group();
		Connection connection = null;
		PreparedStatement statement =null;
	    connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
	    try{
	    	statement = connection.prepareStatement(GET_CONCRETE_GROUP_STATEMENT);
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet!=null && resultSet.next()){
            	group.setId(resultSet.getInt(DBConstants.GROUP_ID));
            	Sporttype sp = new Sporttype();
            	sp.setId(resultSet.getInt(DBConstants.SPORTTYPE_ID));
                group.setSporttype(sp);
                group.setCapacity(resultSet.getInt(DBConstants.GROUP_CAPACITY));
                String days = resultSet.getString(DBConstants.GROUP_DAY_OF_WEEK);
                group.setDaysOfWeek(days);
                group.setTimeStart(resultSet.getTime(DBConstants.GROUP_START_TIME));
                group.setDuration(resultSet.getInt(DBConstants.GROUP_DURATION));
                group.setCostAbonement(resultSet.getInt(DBConstants.GROUP_COST));
                group.setPeopleRegistered(resultSet.getInt(DBConstants.GROUP_PEOPLE_REGISTERED));
                group.getSporttype().setCaloriesburned(resultSet.getInt(DBConstants.SPORTTYPE_CALORIES));
                group.getSporttype().setSportType(SportType.valueOf(resultSet.getString(DBConstants.SPORTTYPE_TYPE)));
            }
	    }catch (SQLException ex) {
            throw new DAOSQLException(ErrorConstants.DAO_SQL_EXCEPTION, ex);
        } finally{
        	JdbcUtil.closeStatement(statement);
        	connectionPool.releaseConnection(connection);
        }        
		return group;
	}



}
