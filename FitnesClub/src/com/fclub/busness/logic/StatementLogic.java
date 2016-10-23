/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclub.busness.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fclub.constants.ParameterConstants;
import com.fclub.exception.FClubInvalidParameterException;
import com.fclub.persistence.dao.DiscountJpaRepository;
import com.fclub.persistence.dao.GroupJpaRepository;
import com.fclub.persistence.dao.RegistrationJpaRepository;
import com.fclub.persistence.dao.StatementJpaRepository;
import com.fclub.persistence.dao.UserJpaRepository;
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
    private StatementJpaRepository statementDAO ;
    
	@Autowired
    @Qualifier("DAOJpaDiscount")
    private DiscountJpaRepository discountDAO ;
    
    @Autowired
    @Qualifier("DAOJpaRegistration")
    private RegistrationJpaRepository registrationDAO ;
    
    @Autowired
    @Qualifier("DAOJpaGroup")
    private GroupJpaRepository groupDAO ;
    
    @Autowired
    @Qualifier("DAOJpaUser")
    private UserJpaRepository userDAO;
    
  
	/**
	 * Updates the statement of the user if it exists in DB ; otherwise creates a new one for him.
	 * @param user - user
	 * @param model - Spring Model object
	 * @throws FClubInvalidParameterException
	 */
    public final void updateOrAddUserStatement(final User user, final Model model) throws FClubInvalidParameterException {
        
        Statement statement = statementDAO.findByUserId(user.getId());
        final int numberAbonementsOfuser = registrationDAO.countNumberOfAbonementsForUser(user.getId());
        final List<Group> userGroups = getGroupsOfUser(user.getId());  
        statement.setNumberOfAbonements(numberAbonementsOfuser);
        statement.setDiscountPercent(discountDAO.countDiscountPercentForUser(numberAbonementsOfuser));
        statement.setGeneralCost(countCostOfAllUsersGroups(userGroups));
        statementDAO.save(statement);
        statement=statementDAO.findByUserId(statement.getUser().getId());
        model.addAttribute(ParameterConstants.USERSTATEMENT, statement);    
    }
	/**
	 * Provides the statement for the specified user.
	 * @param user - user
	 * @return statement of the user
	 * @throws DAOSQLException
	 * @throws FClubInvalidParameterException
	 * @throws ResourceCreationException
	 */
    public final Statement getStatementOfUser(final Long userId) throws FClubInvalidParameterException{
        final List<Group> userGroups = getGroupsOfUser(userId);
        Statement statement = statementDAO.findByUserId(userId);
        int numberAbonementsOfUser = registrationDAO.countNumberOfAbonementsForUser(userId);
        statement.setNumberOfAbonements(numberAbonementsOfUser);
        statement.setGeneralCost(countCostOfAllUsersGroups(userGroups));        
        statement.setDiscountPercent(discountDAO.countDiscountPercentForUser(numberAbonementsOfUser)); 
        statementDAO.save(statement);
        statement = statementDAO.findByUserId(userId);
        return statement;
    }
    /**
     * Provides the array of groups for the specified user.
     * @param userId - id of the user
     * @return array of user's groups
     */
    private List<Group> getGroupsOfUser(final Long userId) {
	    return registrationDAO.findByUserId(userId).stream().map(reg -> reg.getGroup()).collect(Collectors.toList());
    }

    
    public int countCostOfAllUsersGroups(List<Group> groups){
    	int cost = 0;
    	for (Group group : groups){
    		cost+=groupDAO.findById(group.getId()).getCostAbonement();
    	}
    	return cost;
    }

}
