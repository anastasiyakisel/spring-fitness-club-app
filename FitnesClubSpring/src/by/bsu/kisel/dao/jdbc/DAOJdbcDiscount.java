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
                discount.setId(rs.getInt(1));                
                discount.setNumberOfAbonements(rs.getInt(2));
                discount.setDiscountPercent(rs.getInt(3));
                discounts.add(discount);
            }
        } catch (SQLException ex) {
            throw new DAOSQLException("Can't execute query!", ex);
        } finally{
            JdbcUtil.closeStatement(st);
            connectionPool.releaseConnection(connection);
        }
        return discounts;
    }
    
}
