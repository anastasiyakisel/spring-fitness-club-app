/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.dao.jdbc;

import by.bsu.kisel.constants.DBConstants;
import by.bsu.kisel.constants.LoggerConstants;
import by.bsu.kisel.dao.IDAOSporttype;
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
                sporttype.setId(resultSet.getInt(DBConstants.SPORTTYPE_ID));
                sporttype.setSportType(SportType.valueOf(resultSet.getString(DBConstants.SPORTTYPE_TYPE).toUpperCase()));
                sporttype.setCaloriesburned(resultSet.getInt(DBConstants.SPORTTYPE_CALORIES));
                sporttypes.add(sporttype); 
            }
        } catch (SQLException ex) {
            throw new DAOSQLException(LoggerConstants.DAO_SQL_EXCEPTION, ex);
        } finally{
        	JdbcUtil.closeStatement(st);
        	connectionPool.releaseConnection(connection);
        }
        return sporttypes;        
    }
    
}
