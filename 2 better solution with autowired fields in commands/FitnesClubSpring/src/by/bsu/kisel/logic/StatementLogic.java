/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.logic;

import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.controller.Controller;
import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.dao.IDAOStatement;
import by.bsu.kisel.dao.jdbc.DAOJdbcStatement;
import by.bsu.kisel.entity.Statement;
import by.bsu.kisel.entity.User;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Anastasia Kisel
 */
public class StatementLogic {
	
	private static IDAOStatement statementDAO = (IDAOStatement) Controller.webContext.getBean("DAOJdbcStatement");
	
    /**
     * update statement of the concrete user
     * @param ordIds 
     * @param request 
     * @return
     * @throws ResourceCreationException 
     */
    public static final void updateUserStatement(HttpServletRequest request, Integer... ordIds) 
            throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
        Statement statement = new Statement();
        User user = (User) request.getSession().getAttribute(ParameterConstants.PARAMETER_USER);
        statement.setPersonId(user.getId());        
        boolean isExist=statementDAO.isUserExistInStatement(user.getId());
        if (isExist==true){
                statement = statementDAO.getAllFromStatement(statement.getPersonId());
                statementDAO.update(statement, ordIds);
                statement=statementDAO.getAllFromStatement(statement.getPersonId());
            }else{
                statementDAO.add(statement);
                statementDAO.update(statement, ordIds);
                statement=statementDAO.getAllFromStatement(statement.getPersonId());
            }
        request.getSession().setAttribute(ParameterConstants.PARAMETER_STATEMENT, statement);    
    }
    
    /**
     * get statement of the concrete user
     * @param user 
     * @return  statement
     * @throws ResourceCreationException 
     */
    public static final Statement getStatementOfUser(User user) throws DAOSQLException, 
            MyLogicalInvalidParameterException, ResourceCreationException{
        Statement statement=new Statement();
        statement.setPersonId(user.getId());
        boolean isExist = statementDAO.isUserExistInStatement(user.getId());
        if (isExist==true){
            statementDAO.update(statement, 0);
            statement = statementDAO.getAllFromStatement(user.getId());                
        } else {
            statementDAO.add(statement);
            statementDAO.update(statement, 0);
            statementDAO.getAllFromStatement(user.getId()); 
        }
        return statement;
    }
}
