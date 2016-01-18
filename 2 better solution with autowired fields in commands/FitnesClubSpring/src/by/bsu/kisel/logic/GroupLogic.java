/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.logic;

import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.controller.Controller;
import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.dao.IDAOSporttype;
import by.bsu.kisel.dao.jdbc.DAOJdbcGroup;
import by.bsu.kisel.dao.jdbc.DAOJdbcSporttype;
import by.bsu.kisel.entity.Group;
import by.bsu.kisel.entity.GroupSporttype;
import by.bsu.kisel.entity.Sporttype;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Anastasia Kisel
 */
public class GroupLogic {
	
	private static IDAOSporttype sporttypeDAO = (IDAOSporttype) Controller.webContext.getBean("DAOJdbcSporttype");
	
	private static IDAOGroup groupDAO = (IDAOGroup) Controller.webContext.getBean("DAOJdbcGroup");

    /**
     * shows information about sporttypes of the concrete groups
     * @param groups 
     * @param request 
     * @return  groupSporttypes
     * @throws ResourceCreationException 
     */
    public static final ArrayList<GroupSporttype> groupSporttypesShow(ArrayList<Group> groups, HttpServletRequest request) 
            throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
        ArrayList <GroupSporttype> groupSporttypes = new ArrayList<GroupSporttype>();
        
        if (groups!=null){
                    Integer [] ids  = new Integer[groups.size()];
                    int i=0;
                    for (Group group:groups){
                        ids [i]=group.getSporttypeID();
                        ++i;
                    }
                    ArrayList <Sporttype> userSporttypes = sporttypeDAO.showSporttypes(ids); 
                int k=0;
                for (Group group:groups){
                    GroupSporttype groupSporttype = new GroupSporttype();
                    groupSporttype.setGroup(group);
                    groupSporttype.setSporttype(userSporttypes.get(k));
                    ++k;
                    groupSporttypes.add(groupSporttype);
                }
        }
        request.getSession().setAttribute(ParameterConstants.PARAMETER_GR_SPORTTYPES, groupSporttypes);
        return groupSporttypes;
    }
    
    /**
     * update people ragistered in the concrete groups
     * @param groups  
     * @return groups
     * @throws ResourceCreationException 
     */
    public static final ArrayList<Group> updatePeopleRegistered( ArrayList<Group> groups) 
            throws DAOSQLException, MyLogicalInvalidParameterException, ResourceCreationException{
        ArrayList<Integer> numbers=new ArrayList<Integer>();
        for (Group group:groups){
            int number=groupDAO.countPeopleRegisteredInGroup(group.getId());
            numbers.add(number);
        }
        int i=0;
        for(Group group:groups){
            group.setPeopleRegistered(numbers.get(i));
            groupDAO.updatePeopleRegistered(group.getId(), group.getPeopleRegistered());
            ++i;
        }
        return groups;
    }   
        
}
