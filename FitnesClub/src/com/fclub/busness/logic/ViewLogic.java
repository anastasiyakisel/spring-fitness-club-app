/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclub.busness.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fclub.constants.ParameterConstants;
import com.fclub.exception.FClubInvalidParameterException;
import com.fclub.persistence.model.Statement;
import com.fclub.persistence.model.User;
/**
 * This class implements the logic related to view actions.
 * @author Anastasiya Kisel
 */
@Component("ViewLogic")
@SessionAttributes({ParameterConstants.USER_STATEMENTS})
public class ViewLogic {
	
	private static final Logger LOGGER = Logger.getLogger(ViewLogic.class);
	
    @Autowired
    private StatementLogic statementRepository ;

	/**
	 * Provides the statements for the specified users.
	 * @param users - list of users
	 * @param model - Spring Model object
	 * @throws FClubInvalidParameterException
	 */
    public final void showUserStatements(final List<User> users, final Model model) throws FClubInvalidParameterException{
		if (!users.isEmpty()) {
			List<Statement> userStatements =users.stream().map(user -> {
				try {
					return statementRepository.getStatementOfUser(user.getId());
				} catch (FClubInvalidParameterException e) {
					LOGGER.error("Error retrieving user's statements"+e.getMessage());
				}
				return null;
		}).collect(Collectors.toList());
			model.addAttribute(ParameterConstants.USER_STATEMENTS, userStatements);
		} else {
			model.addAttribute(ParameterConstants.EMPTY_GROUP, ParameterConstants.EMPTY_GROUP);
		}
		
	}   
}
