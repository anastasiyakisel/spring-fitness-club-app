/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.logic;

import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.dao.IDAOStatement;
import by.bsu.kisel.entity.Statement;
import by.bsu.kisel.entity.User;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anastasia Kisel
 */
@Component("ViewLogic")
public class ViewLogic {
	
    @Autowired
    @Qualifier("StatementLogic")
    private StatementLogic statementLogic ;
    /**
     * get information about the concrete users and their statements
     * @param users  
     * @param request 
     * @return
 * @throws ResourceCreationException 
     */
    public final void showUserStatements(ArrayList<User> users, HttpServletRequest request) throws DAOSQLException, 
            MyLogicalInvalidParameterException, ResourceCreationException{
        ArrayList<Statement> userStatements=new ArrayList<Statement>();
            
        if(users.size()!=0){
                    for (User user:users){
                        Statement statement = statementLogic.getStatementOfUser(user);
                        userStatements.add(statement);
                    }
                }else{
                    request.setAttribute(ParameterConstants.EMPTY_GROUP, ParameterConstants.EMPTY_GROUP);
                }
        request.getSession().setAttribute(ParameterConstants.USER_STATEMENTS_PARAMETER, userStatements);
        }    
}
