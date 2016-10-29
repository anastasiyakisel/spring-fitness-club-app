/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclub.busness.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fclub.constants.ParameterConstants;
import com.fclub.exception.FClubInvalidParameterException;
import com.fclub.persistence.model.Group;
import com.fclub.persistence.model.Statement;
import com.fclub.persistence.model.User;
import com.fclub.persistence.repository.DiscountJpaRepository;
import com.fclub.persistence.repository.GroupJpaRepository;
import com.fclub.persistence.repository.RegistrationJpaRepository;
import com.fclub.persistence.repository.StatementJpaRepository;
/**
 * This class implements the logic related to statement actions.
 * @author Anastasiya Kisel
 */
@Component("StatementLogic")
@SessionAttributes({ParameterConstants.USERSTATEMENT})
public class StatementLogic {
	
    @Autowired
    private StatementJpaRepository statementRepository ;
    
	@Autowired
    private DiscountJpaRepository discountRepository ;
	
    @Autowired
    private GroupJpaRepository groupRepository ;
    
    @Autowired
    private RegistrationJpaRepository registrationRepository ;
  
	/**
	 * Updates the statement of the user if it exists in DB ; otherwise creates a new one for him.
	 * @param user - user
	 * @param model - Spring Model object
	 * @throws FClubInvalidParameterException
	 */
    public final void updateOrAddUserStatement(final User user, final Model model) throws FClubInvalidParameterException {
        
        Statement statement = statementRepository.findByUserId(user.getId());
        final int numberAbonementsOfuser = registrationRepository.countNumberOfAbonementsForUser(user.getId());
        final List<Group> userGroups = getGroupsOfUser(user.getId());  
        statement.setNumberOfAbonements(numberAbonementsOfuser);
        statement.setDiscountPercent(discountRepository.countDiscountPercentForUser(numberAbonementsOfuser));
        statement.setGeneralCost(countCostOfAllUsersGroups(userGroups));
        statementRepository.save(statement);
        statement=statementRepository.findByUserId(statement.getUser().getId());
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
        Statement statement = statementRepository.findByUserId(userId);
        int numberAbonementsOfUser = registrationRepository.countNumberOfAbonementsForUser(userId);
        statement.setNumberOfAbonements(numberAbonementsOfUser);
        statement.setGeneralCost(countCostOfAllUsersGroups(userGroups));        
        statement.setDiscountPercent(discountRepository.countDiscountPercentForUser(numberAbonementsOfUser)); 
        statementRepository.save(statement);
        statement = statementRepository.findByUserId(userId);
        return statement;
    }
    /**
     * Provides the array of groups for the specified user.
     * @param userId - id of the user
     * @return array of user's groups
     */
    private List<Group> getGroupsOfUser(final Long userId) {
	    return registrationRepository.findByUserId(userId).stream().map(reg -> reg.getGroup()).collect(Collectors.toList());
    }

    
    public int countCostOfAllUsersGroups(List<Group> groups){
    	int cost = 0;
    	for (Group group : groups){
    		cost+=groupRepository.findById(group.getId()).getCostAbonement();
    	}
    	return cost;
    }

}
