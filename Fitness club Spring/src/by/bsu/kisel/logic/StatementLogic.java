/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.dao.IDAODiscount;
import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.dao.IDAORegistration;
import by.bsu.kisel.dao.IDAOStatement;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.model.Group;
import by.bsu.kisel.model.Statement;
import by.bsu.kisel.model.User;
/**
 * This class implements the logic related to statement actions.
 * @author Anastasiya Kisel
 */
@Component("StatementLogic")
@SessionAttributes({ParameterConstants.PARAMETER_USERSTATEMENT})
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
	 * Updates the statement of the user if it exists in DB ; otherwise creates a new one for him.
	 * @param user - user
	 * @param model - Spring Model object
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
    public final void updateOrAddUserStatement(User user, Model model) 
            throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
        Statement statement = new Statement();
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
        model.addAttribute(ParameterConstants.PARAMETER_USERSTATEMENT, statement);    
    }
	/**
	 * Provides the statement for the specified user.
	 * @param user - user
	 * @return statement of the user
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
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
    /**
     * Provides the array of groups for the specified user.
     * @param userId - id of the user
     * @return array of user's groups
     * @throws DAOSQLException
     * @throws MyLogicalInvalidParameterException
     * @throws ResourceCreationException
     */
    private int [] getGroupsOfUser(int userId) throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
	    List<Group> userGroupsList =registrationDAO.showUserGroups(userId);
	    int [] userGroupIds = new int [userGroupsList.size()];
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
    private boolean addUserStatement (Statement st, int discountPercent, int generalCost) throws MyLogicalInvalidParameterException, DAOSQLException{
    	st.setDiscountPercent(discountPercent);
    	st.setGeneralCost(generalCost);
    	statementDAO.add(st);
    	return true;
    }

}
