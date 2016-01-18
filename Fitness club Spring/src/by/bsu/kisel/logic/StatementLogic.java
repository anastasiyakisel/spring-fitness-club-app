/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.logic;

import java.util.ArrayList;

import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.controller.Controller;
import by.bsu.kisel.dao.IDAODiscount;
import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.dao.IDAORegistration;
import by.bsu.kisel.dao.IDAOStatement;
import by.bsu.kisel.dao.jdbc.DAOJdbcStatement;
import by.bsu.kisel.entity.Group;
import by.bsu.kisel.entity.Statement;
import by.bsu.kisel.entity.User;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anastasia Kisel
 */
@Component("StatementLogic")
public class StatementLogic {
	
    @Autowired
    @Qualifier("DAOJpaStatement")
    private IDAOStatement statementDAO ;
    
	@Autowired
    @Qualifier("DAOJpaDiscount")
    private IDAODiscount discountDAO ;
    
    @Autowired
    @Qualifier("DAOJpaRegistration")
    private IDAORegistration registrationDAO ;
    
    @Autowired
    @Qualifier("DAOJpaGroup")
    private IDAOGroup groupDAO ;
	
    /**
     * update statement of the concrete user
     * @param ordIds 
     * @param request 
     * @return
     * @throws ResourceCreationException 
     */
    public final void updateOrAddUserStatement(HttpServletRequest request) 
            throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
        Statement statement = new Statement();
        User user = (User) request.getSession().getAttribute(ParameterConstants.PARAMETER_USER);
        statement.setUser(user);
        boolean isExist=statementDAO.isUserExistInStatement(user.getId());
        int numberAbonementsOfuser = registrationDAO.countNumberOfAbonementsForUser(user.getId());
        statement.setNumberOfAbonements(numberAbonementsOfuser);
        int [] userGroupIds = getGroupsOfUser(user.getId());
        int discountPercent = discountDAO.countDiscountPercentForUser(statement.getNumberOfAbonements()); 
        int generalCost = groupDAO.countCostOfAllUsersGroups(userGroupIds);
        if (isExist==true){                
            statementDAO.update(statement, numberAbonementsOfuser, discountPercent, generalCost);
        }else{
        	statement.setNumberOfAbonements(numberAbonementsOfuser);                
            addUserStatement(statement, discountPercent, generalCost);
        }
        statement=statementDAO.getAllFromStatement(statement.getUser().getId());
        request.getSession().setAttribute(ParameterConstants.PARAMETER_STATEMENT, statement);    
    }
    
    /**
     * get statement of the concrete user
     * @param user 
     * @return  statement
     * @throws ResourceCreationException 
     */
    public final Statement getStatementOfUser(User user) throws DAOSQLException, 
            MyLogicalInvalidParameterException, ResourceCreationException{
        Statement statement=new Statement();
        statement.setUser(user);
        boolean isExist = statementDAO.isUserExistInStatement(user.getId());
        int numberAbonementsOfUser = registrationDAO.countNumberOfAbonementsForUser(user.getId());
        int [] userGroupIds = getGroupsOfUser(user.getId());
        int discountPercent = discountDAO.countDiscountPercentForUser(numberAbonementsOfUser); 
        int generalCost = groupDAO.countCostOfAllUsersGroups(userGroupIds);
        if (isExist==true){  
            statementDAO.update(statement, numberAbonementsOfUser, discountPercent, generalCost);                
        } else {
            statementDAO.add(statement);
            statementDAO.update(statement, numberAbonementsOfUser, discountPercent, generalCost);
        }
        statement = statementDAO.getAllFromStatement(user.getId());
        return statement;
    }
    
    private int [] getGroupsOfUser(int userId) throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
	    ArrayList<Group> userGroupsList =registrationDAO.showUserGroups(userId);
	    int [] userGroupIds = new int [userGroupsList.size()];
		for (int i=0; i<userGroupIds.length; i++){
			userGroupIds[i] = userGroupsList.get(i).getId();
		}
		return userGroupIds;
    }
    
    private boolean addUserStatement (Statement st, int discountPercent, int generalCost) throws MyLogicalInvalidParameterException, DAOSQLException{
    	st.setDiscountPercent(discountPercent);
    	st.setGeneralCost(generalCost);
    	statementDAO.add(st);
    	return true;
    }

}
