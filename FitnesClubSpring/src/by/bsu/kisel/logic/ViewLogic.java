/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.logic;

import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.entity.Statement;
import by.bsu.kisel.entity.User;
import by.bsu.kisel.entity.UserStatement;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Anastasia Kisel
 */
public class ViewLogic {
/**
     * get information about the concrete users and their statements
     * @param users  
     * @param request 
     * @return
 * @throws ResourceCreationException 
     */
    public static final void showUserStatements(ArrayList<User> users, HttpServletRequest request) throws DAOSQLException, 
            MyLogicalInvalidParameterException, ResourceCreationException{
        ArrayList<UserStatement> userStatements=new ArrayList<UserStatement>();
        ArrayList<Statement> statements=new ArrayList<Statement>();
            
        if(users.size()!=0){
                    for (User user:users){
                        Statement statement = StatementLogic.getStatementOfUser(user);
                        statements.add(statement);
                        UserStatement userStatement=new UserStatement();
                        userStatement.setUser(user);
                        userStatement.setStatement(statement);
                        userStatements.add(userStatement);
                    }
                }else{
                    request.setAttribute(ParameterConstants.EMPTY_GROUP, ParameterConstants.EMPTY_GROUP);
                }
        request.getSession().setAttribute(ParameterConstants.USER_STATEMENTS_PARAMETER, userStatements);
        }    
}
