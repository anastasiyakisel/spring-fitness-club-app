/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.model.Group;

/**
 * This class implements the logic related to sign up actions.
 * @author Anastasiya Kisel
 */
@Component("SignUpLogic")
public class SignUpLogic {
	
    @Autowired
    @Qualifier("DAOJpaGroup")
    private IDAOGroup groupDAO ;
	
	/**
	 * Provides the ids of groups where it is possible to signup among the specified group ids.
	 * @param ids - array of random groups' ids
	 * @return array of free groups
	 * @throws MyLogicalInvalidParameterException
	 * @throws DAOSQLException
	 * @throws ResourceCreationException
	 */
    public final Integer[] getIndexesOfFreeGroups(Integer... ids) 
            throws MyLogicalInvalidParameterException, DAOSQLException, ResourceCreationException{
        List<Group> groups=groupDAO.getFreeGroups(ids);
        ids=new Integer[groups.size()];
        int i=0;
        for(Group group:groups){
            ids[i]=group.getId();
            ++i;
        }
        return ids;
    }    
}
