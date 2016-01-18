/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import by.bsu.kisel.constants.DBConstants;
import by.bsu.kisel.constants.LoggerConstants;
import by.bsu.kisel.dao.IDAODiscount;
import by.bsu.kisel.entity.Discount;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.util.JdbcUtil;


/**
 *
 * @author Anastasia Kisel
 */
@Component("DAOJdbcDiscount")
public class DAOJdbcDiscount extends DAOJdbc implements IDAODiscount{
	
    private static final String SELECT_ALL_FROM_DISCOUNT="SELECT * FROM `discount`";
    
    private static final String CHOOSE_DISCOUNT_PERCENT=
            "SELECT MAX(discount_percent) FROM `discount` WHERE number_of_abonements <= (?) ";

    /**
     *get information about discounts in the fitness-club
     * @return discounts
     * @throws ResourceCreationException 
     */
    public ArrayList<Discount> getInformationDiscount() throws DAOSQLException, 
            MyLogicalInvalidParameterException, ResourceCreationException{
        ArrayList<Discount> discounts=new ArrayList<Discount>();
        Connection connection = null;
        PreparedStatement st = null;
        connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
        try {
            st=connection.prepareStatement(SELECT_ALL_FROM_DISCOUNT);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                Discount discount = new Discount();
                discount.setId(rs.getInt(DBConstants.DISCOUNT_ID));                
                discount.setNumberOfAbonements(rs.getInt(DBConstants.DISCOUNT_ABONEMENTS));
                discount.setDiscountPercent(rs.getInt(DBConstants.DISCOUNT_PERCENT));
                discounts.add(discount);
            }
        } catch (SQLException ex) {
            throw new DAOSQLException(LoggerConstants.DAO_SQL_EXCEPTION, ex);
        } finally{
            JdbcUtil.closeStatement(st);
            connectionPool.releaseConnection(connection);
        }
        return discounts;
    }
    
	/**
     * counts discount percent of the concrete user
     * @param entity 
     * @return discountPercent
     * @throws ResourceCreationException 
     */
	@Override
	public int countDiscountPercentForUser(int numberOfAbonements)
			throws DAOSQLException, MyLogicalInvalidParameterException,
			ResourceCreationException {
		 int discountPercent=0;
	        Connection connection = null;
	        PreparedStatement st = null;
	        try {
	            connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
	            st=connection.prepareStatement(CHOOSE_DISCOUNT_PERCENT);
	            st.setInt(1, numberOfAbonements);
	            ResultSet rs = st.executeQuery();
	            rs.next();
	            discountPercent = rs.getInt(1);	                       
	        }  catch (SQLException ex) {
	            throw new DAOSQLException(LoggerConstants.DAO_SQL_EXCEPTION, ex);
	        }finally{
	        	JdbcUtil.closeStatement(st);
	        	connectionPool.releaseConnection(connection);
	        }
	        return discountPercent;
	}
	
    
}
