/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.dao.jdbc;

import by.bsu.kisel.dao.IDAOSporttype;
import by.bsu.kisel.entity.GroupSporttype;
import by.bsu.kisel.entity.Sporttype;
import by.bsu.kisel.enums.SportType;
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
@Component("DAOJdbcSporttype")
public class DAOJdbcSporttype extends DAOJdbc implements IDAOSporttype{
    private static final String SELECT_ALL_FROM_SPORTTYPE=
            "SELECT * FROM `sporttype` WHERE sporttype_id=(?)";
    private static final String SELECT_USER_GROUPS=
            "SELECT group_id FROM `registration` WHERE person_id=(?)";
    private static final String GET_USER_GROUP_SPORTTYPES="SELECT * FROM `groups` "
            + " INNER JOIN `sporttype` ON groups.sporttype_id=sporttype.sporttype_id "
            + " WHERE groups.group_id=(?)";
    
    /**
     * get information about the concrete sporttype
     * @param ids
     * @return sporttypes
     * @throws ResourceCreationException 
     */
    public ArrayList <Sporttype> showSporttypes (Integer... ids) throws DAOSQLException, 
            MyLogicalInvalidParameterException, ResourceCreationException{
        ArrayList <Sporttype> sporttypes = new ArrayList<Sporttype>();
        Connection connection = null;
        PreparedStatement st = null;
        try {
            connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
            for (int id:ids){
                st=connection.prepareStatement(SELECT_ALL_FROM_SPORTTYPE);
                st.setInt(1, id);
                ResultSet resultSet = st.executeQuery();
                resultSet.next();
                Sporttype sporttype = new Sporttype();
                sporttype.setId(resultSet.getInt(1));
                sporttype.setSportType(SportType.valueOf(resultSet.getString(2).toUpperCase()));
                sporttype.setCaloriesburned(resultSet.getInt(3));
                sporttypes.add(sporttype); 
            }
        } catch (SQLException ex) {
            throw new DAOSQLException("Can't execute query !", ex);
        } finally{
        	JdbcUtil.closeStatement(st);
        	connectionPool.releaseConnection(connection);
        }
        return sporttypes;        
    }
    /**
     * get all information about the group and sporttype where user signed up
     * @param userId
     * @return userGroupSporttypes
     * @throws ResourceCreationException 
     */
    public ArrayList<GroupSporttype> showuserGroupSporttypes(int userId) 
            throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
        ArrayList<GroupSporttype> userGroupSporttypes=null;
        Connection connection = null;
        PreparedStatement st = null;
        connection=connectionPool.getConnection(MAX_CONNECTION_WAIT);
        try {
            st = connection.prepareStatement(SELECT_USER_GROUPS);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            ArrayList <Integer> groupIds = new ArrayList<Integer>();
            while (rs.next()){
                groupIds.add(rs.getInt(1));
            }
            userGroupSporttypes=new ArrayList<GroupSporttype>();
            for (int groupId:groupIds){
                st=connection.prepareStatement(GET_USER_GROUP_SPORTTYPES);
                st.setInt(1, groupId);
                ResultSet resultSet = st.executeQuery();
                resultSet.next();
                GroupSporttype groupSporttype = new GroupSporttype();
                groupSporttype.getGroup().setId(resultSet.getInt(1));
                groupSporttype.getGroup().setSporttypeID(resultSet.getInt(2));
                groupSporttype.getGroup().setCapacity(resultSet.getInt(3));
                groupSporttype.getGroup().setDaysOfWeek(resultSet.getString(4));
                groupSporttype.getGroup().setTimeStart(resultSet.getTime(5));
                groupSporttype.getGroup().setDuration(resultSet.getInt(6));
                groupSporttype.getGroup().setCostAbonement(resultSet.getInt(7));
                groupSporttype.getGroup().setPeopleRegistered(resultSet.getInt(8));
                groupSporttype.getSporttype().setId(resultSet.getInt(9));
                groupSporttype.getSporttype().setSportType(SportType.valueOf(resultSet.getString(10).toUpperCase()));
                groupSporttype.getSporttype().setCaloriesburned(resultSet.getInt(11));
                userGroupSporttypes.add(groupSporttype);
            }
        } catch (SQLException ex) {
            throw new DAOSQLException("Can't execute query!", ex);
        } finally{
        	JdbcUtil.closeStatement(st);
        	connectionPool.releaseConnection(connection);
        }            
        return userGroupSporttypes;
    }

}
