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
import java.util.List;

import org.springframework.stereotype.Component;

import by.bsu.kisel.constants.DBConstants;
import by.bsu.kisel.constants.ErrorConstants;
import by.bsu.kisel.dao.IDAODiscount;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.model.Discount;
import by.bsu.kisel.util.JdbcUtil;
/**
 * This class implements DAO Discount logic with the help of JDBC.
 * @author Anastasiya Kisel
 */
@Component("DAOJdbcDiscount")
public class DAOJdbcDiscount extends DAOJdbc implements IDAODiscount{
	
    private static final String SELECT_ALL_FROM_DISCOUNT_STATEMENT="SELECT * FROM `discount`";
    
    private static final String CHOOSE_DISCOUNT_PERCENT_STATEMENT=
            "SELECT MAX(discount_percent) FROM `discount` WHERE number_of_abonements <= (?) ";

	/**
	 * Provides the information about all available discounts of the fitness club.
	 * @return the list of all available discounts 
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
    public List<Discount> getInformationDiscount() throws DAOSQLException, 
            MyLogicalInvalidParameterException, ResourceCreationException{
        List<Discount> discounts=new ArrayList<Discount>();
        Connection connection = null;
        PreparedStatement st = null;
        connection = connectionPool.getConnection(MAX_CONNECTION_WAIT);
        try {
            st=connection.prepareStatement(SELECT_ALL_FROM_DISCOUNT_STATEMENT);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                Discount discount = new Discount();
                discount.setId(rs.getInt(DBConstants.DISCOUNT_ID));                
                discount.setNumberOfAbonements(rs.getInt(DBConstants.DISCOUNT_ABONEMENTS));
                discount.setDiscountPercent(rs.getInt(DBConstants.DISCOUNT_PERCENT));
                discounts.add(discount);
            }
        } catch (SQLException ex) {
            throw new DAOSQLException(ErrorConstants.DAO_SQL_EXCEPTION, ex);
        } finally{
            JdbcUtil.closeStatement(st);
            connectionPool.releaseConnection(connection);
        }
        return discounts;
    }
    
	/**
	 * Calculates the discount percent for the user based on the number of abonements this user owns.
	 * @param numberOfAbonements - number of the abonements the user owns
	 * @return the discount percent for the user
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
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
	            st=connection.prepareStatement(CHOOSE_DISCOUNT_PERCENT_STATEMENT);
	            st.setInt(1, numberOfAbonements);
	            ResultSet rs = st.executeQuery();
	            rs.next();
	            discountPercent = rs.getInt(1);	                       
	        }  catch (SQLException ex) {
	            throw new DAOSQLException(ErrorConstants.DAO_SQL_EXCEPTION, ex);
	        }finally{
	        	JdbcUtil.closeStatement(st);
	        	connectionPool.releaseConnection(connection);
	        }
	        return discountPercent;
	}
	
    
}
