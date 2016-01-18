/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.entity.Group;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

/**
 *
 * @author Anastasia Kisel
 */
@Component("SignUpLogic")
public class SignUpLogic {
	
    @Autowired
    @Qualifier("DAOJpaGroup")
    private IDAOGroup groupDAO ;
	
 /**
     * get indexes of the groups where abonements still exist
     * @param ids 
     * @return ids
     * @throws ResourceCreationException 
     */
    public final Integer[] getIndexesOfFreeGroups(Integer... ids) 
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
