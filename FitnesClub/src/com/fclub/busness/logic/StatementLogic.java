/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclub.busness.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fclub.constants.ParameterConstants;
import com.fclub.exception.DAOSQLException;
import com.fclub.exception.MyLogicalInvalidParameterException;
import com.fclub.exception.ResourceCreationException;
import com.fclub.persistence.dao.IDAODiscount;
import com.fclub.persistence.dao.IDAOGroup;
import com.fclub.persistence.dao.IDAORegistration;
import com.fclub.persistence.dao.IDAOStatement;
import com.fclub.persistence.dao.IDAOUser;
import com.fclub.persistence.model.Group;
import com.fclub.persistence.model.Statement;
import com.fclub.persistence.model.User;
/**
 * This class implements the logic related to statement actions.
 * @author Anastasiya Kisel
 */
@Component("StatementLogic")
@SessionAttributes({ParameterConstants.USERSTATEMENT})
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
    
    @Autowired
    @Qualifier("DAOJpaUser")
    private IDAOUser userDAO;
    
  
	/**
	 * Updates the statement of the user if it exists in DB ; otherwise creates a new one for him.
	 * @param user - user
	 * @param model - Spring Model object
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
    public final void updateOrAddUserStatement(final User user, final Model model) 
            throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
        Statement statement = new Statement();
        statement.setUser(user);
        final boolean isExist=statementDAO.isUserExistInStatement(user.getId());
        final int numberAbonementsOfuser = registrationDAO.countNumberOfAbonementsForUser(user.getId());
        statement.setNumberOfAbonements(numberAbonementsOfuser);
        final int [] userGroupIds = getGroupsOfUser(user.getId());
        final int discountPercent = discountDAO.countDiscountPercentForUser(statement.getNumberOfAbonements()); 
        final int generalCost = groupDAO.countCostOfAllUsersGroups(userGroupIds);
        if (isExist==true){                
            statementDAO.update(statement, numberAbonementsOfuser, discountPercent, generalCost);
        }else{
        	statement.setNumberOfAbonements(numberAbonementsOfuser);                
            addUserStatement(statement, discountPercent, generalCost);
        }
        statement=statementDAO.getAllFromStatement(statement.getUser().getId());
        model.addAttribute(ParameterConstants.USERSTATEMENT, statement);    
    }
	/**
	 * Provides the statement for the specified user.
	 * @param user - user
	 * @return statement of the user
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
    public final Statement getStatementOfUser(final Integer userId) throws DAOSQLException, 
            MyLogicalInvalidParameterException, ResourceCreationException{
        Statement statement=new Statement();
        statement.setUser(userDAO.findById(userId));
        final boolean isExist = statementDAO.isUserExistInStatement(userId);
        final int numberAbonementsOfUser = registrationDAO.countNumberOfAbonementsForUser(userId);
        final int [] userGroupIds = getGroupsOfUser(userId);
        final int discountPercent = discountDAO.countDiscountPercentForUser(numberAbonementsOfUser); 
        final int generalCost = groupDAO.countCostOfAllUsersGroups(userGroupIds);
        if (isExist==true){  
            statementDAO.update(statement, numberAbonementsOfUser, discountPercent, generalCost);                
        } else {
            statementDAO.add(statement);
            statementDAO.update(statement, numberAbonementsOfUser, discountPercent, generalCost);
        }
        statement = statementDAO.getAllFromStatement(userId);
        return statement;
    }
    /**
     * Provides the array of groups for the specified user.
     * @param userId - id of the user
     * @return array of user's groups
     * @throws DAOSQLException
     * @throws MyLogicalInvalidParameterException
     * @throws ResourceCreationException
     */
    private int [] getGroupsOfUser(final int userId) throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
	    final List<Group> userGroupsList =registrationDAO.showUserGroups(userId);
	    final int [] userGroupIds = new int [userGroupsList.size()];
		for (int i=0; i<userGroupIds.length; i++){
			userGroupIds[i] = userGroupsList.get(i).getId();
		}
		return userGroupIds;
    }
    /**
     * Adds the statement with specified discount percent and general cost to the database.
     * @param st - statement
     * @param discountPercent - discount percent
     * @param generalCost - general cost
     * @return
     * @throws MyLogicalInvalidParameterException
     * @throws DAOSQLException
     */
    private boolean addUserStatement (final Statement st, final int discountPercent, final int generalCost) throws MyLogicalInvalidParameterException, DAOSQLException{
    	st.setDiscountPercent(discountPercent);
    	st.setGeneralCost(generalCost);
    	statementDAO.add(st);
    	return true;
    }

}
