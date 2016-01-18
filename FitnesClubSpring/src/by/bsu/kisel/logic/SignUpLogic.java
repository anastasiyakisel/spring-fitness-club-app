/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.logic;

import by.bsu.kisel.controller.Controller;
import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.dao.jdbc.DAOJdbcGroup;
import by.bsu.kisel.entity.Group;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

import java.util.ArrayList;

/**
 *
 * @author Anastasia Kisel
 */
public class SignUpLogic {
	
	private static IDAOGroup groupDAO = (IDAOGroup) Controller.webContext.getBean("DAOJdbcGroup");
	
 /**
     * get indexes of the groups where abonements still exist
     * @param ids 
     * @return ids
     * @throws ResourceCreationException 
     */
    public static final Integer[] getIndexesOfFreeGroups(Integer... ids) 
            throws MyLogicalInvalidParameterException, DAOSQLException, ResourceCreationException{
        ArrayList<Group> groups=groupDAO.getFreeGroups(ids);
        ids=new Integer[groups.size()];
        int i=0;
        for(Group group:groups){
            ids[i]=group.getId();
            ++i;
        }
        return ids;
    }    
}
