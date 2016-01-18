/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.model.Statement;
import by.bsu.kisel.model.User;
/**
 * This class implements the logic related to view actions.
 * @author Anastasiya Kisel
 */
@Component("ViewLogic")
@SessionAttributes({ParameterConstants.USER_STATEMENTS_PARAMETER})
public class ViewLogic {
	
    @Autowired
    @Qualifier("StatementLogic")
    private StatementLogic statementLogic ;

	/**
	 * Provides the statements for the specified users.
	 * @param users - list of users
	 * @param model - Spring Model object
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
    public final void showUserStatements(List<User> users, Model model) throws DAOSQLException, 
            MyLogicalInvalidParameterException, ResourceCreationException{
		ArrayList<Statement> userStatements = new ArrayList<Statement>();

		if (users.size() != 0) {
			for (User user : users) {
				Statement statement = statementLogic.getStatementOfUser(user);
				userStatements.add(statement);
			}
		} else {
			model.addAttribute(ParameterConstants.EMPTY_GROUP, ParameterConstants.EMPTY_GROUP);
		}
		model.addAttribute(ParameterConstants.USER_STATEMENTS_PARAMETER, userStatements);
	}   
}
