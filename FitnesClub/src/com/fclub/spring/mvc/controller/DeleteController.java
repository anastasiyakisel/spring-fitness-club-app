package com.fclub.spring.mvc.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fclub.busness.logic.AdminLogic;
import com.fclub.busness.logic.GroupLogic;
import com.fclub.busness.logic.StatementLogic;
import com.fclub.busness.logic.ViewLogic;
import com.fclub.constants.PageConstants;
import com.fclub.constants.ParameterConstants;
import com.fclub.constants.URLConstants;
import com.fclub.exception.DAOSQLException;
import com.fclub.exception.MyLogicalInvalidParameterException;
import com.fclub.exception.ResourceCreationException;
import com.fclub.persistence.dao.IDAOGroup;
import com.fclub.persistence.dao.IDAORegistration;
import com.fclub.persistence.dao.IDAOStatement;
import com.fclub.persistence.dao.IDAOUser;
import com.fclub.persistence.model.Group;
import com.fclub.persistence.model.Statement;
import com.fclub.persistence.model.User;
import com.fclub.util.SecurityUtil;
/**
 * This class implements business logic of Spring MVC Delete Controller.
 * @author Anastasiya Kisel
 */
@Controller
@SessionAttributes({
	ParameterConstants.ALL_STATEMENTS,
	ParameterConstants.GR_SPORTTYPES, 
	ParameterConstants.NUMBER, 
})
public class DeleteController {
	
  private Logger log = Logger.getLogger(DeleteController.class);
  
  @Autowired
  @Qualifier("DAOJpaGroup")
  private IDAOGroup groupDAO ;
  
  @Autowired
  @Qualifier("DAOJpaUser")
  private IDAOUser userDAO ;
  
  @Autowired
  @Qualifier("DAOJpaRegistration")
  private IDAORegistration registrationDAO ;
  
  @Autowired
  @Qualifier("DAOJpaStatement")
  private IDAOStatement statementDAO ;
  //Autowiring Logic classes
  @Autowired
  @Qualifier("GroupLogic")
  private GroupLogic groupLogic ;
  
  @Autowired
  @Qualifier("StatementLogic")
  private StatementLogic statementLogic ;
  
  @Autowired
  @Qualifier("ViewLogic")
  private ViewLogic viewLogic ;
  
  @Autowired
  @Qualifier("AdminLogic")
  private AdminLogic adminLogic ;
 
  /**
   * Used when admin wants to delete user from groups.
   * @param group - group
   * @param userId - user id
   * @param result - BindingResult
   * @param model - Model
   * @return page
   * @throws ResourceCreationException
   */
	@RequestMapping(value = { URLConstants.ADMIN_DELETE_USERS_FROM_TRAININGS,
			URLConstants.GENERIC_DELETE_USERS_FROM_TRAININGS }, method = RequestMethod.POST)
	public String deleteUserFromTrainings(@ModelAttribute(ParameterConstants.GROUP) Group group, 
		  				BindingResult result, Model model) throws ResourceCreationException {
    String page=null;
    List<String> deleteIdsString=null;
    Integer [] deleteIds;
    deleteIdsString= group.getSelectedIds();
    if (deleteIdsString!=null){
        deleteIds=new Integer[deleteIdsString.size()];
        for (int idx=0; idx< deleteIds.length; idx++){
            deleteIds[idx]=Integer.parseInt(deleteIdsString.get(idx).trim());
        }
        User loggedInUser = null;
        Authentication auth = SecurityUtil.getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)){
			 try {
				 loggedInUser = userDAO.findByUsername(auth.getName());
			} catch (ResourceCreationException | DAOSQLException | MyLogicalInvalidParameterException e) {
				page = PageConstants.ERROR_PAGE_PATH;
				return page;
			} 
		}
        User user = new User();
        user.setId(loggedInUser.getId());
        try {
            registrationDAO.deleteUserFromGroups(user.getId(), deleteIds);
            adminLogic.showToAdminUserGroups(user.getId(), model);
            statementLogic.getStatementOfUser(user.getId());
            List<Statement> allUserStatements=statementDAO.getAllUserStatements();
            model.addAttribute(ParameterConstants.ALL_STATEMENTS, allUserStatements);
            model.addAttribute(ParameterConstants.STATEMENT, new Statement());
            log.info("User № "+user.getId()+
            		" has been deleted from  "+deleteIds.length+" groups by administrator.");
        } catch (DAOSQLException | MyLogicalInvalidParameterException ex) {
                page=PageConstants.ERROR_PAGE_PATH;
                return page;
        }        
    }
    page=PageConstants.ADMIN_ALL_USERS_PATH;
    return page;
  }
  /**
   * Used when admin wants to delete users from specified group.
   * @param statement - Statement object
   * @param numberGroup - group's number
   * @param result - BindingResult
   * @param model - Model
   * @return page
   * @throws ResourceCreationException
   */
 @RequestMapping(value = URLConstants.ADMIN_DELETE_USERS_FROM_GROUP, method=RequestMethod.POST) 
 public String deleteUsersFromGroup(@ModelAttribute(ParameterConstants.STATEMENT) Statement statement,
					@ModelAttribute(ParameterConstants.NUMBER) Integer numberGroup,
					BindingResult result, Model model) throws ResourceCreationException{
    String page=null;
    List <String> userIdsStr=null;
    Integer[] userIds;
    userIdsStr=statement.getSelectedIds();
    
    if (userIdsStr!=null){
        userIds=new Integer[userIdsStr.size()];
        for (int idx=0; idx< userIdsStr.size(); idx++){
            userIds[idx]=Integer.parseInt(userIdsStr.get(idx).trim());
        }
        try {    
        	registrationDAO.deleteUsersFromGroup(numberGroup, userIds);
            List<Group> allGroups=groupDAO.adminShowAllGroups();
            allGroups=groupLogic.updatePeopleRegistered(allGroups);
            model.addAttribute(ParameterConstants.GR_SPORTTYPES, allGroups);
            model.addAttribute(ParameterConstants.GROUP, new Group());
            List<User> usersOfGroup=registrationDAO.getUsersFromGroup(numberGroup);
            viewLogic.showUserStatements(usersOfGroup, model);              
            log.info("Admin has deleted users № "+ userIdsStr +" from group № "+numberGroup);
        } catch (MyLogicalInvalidParameterException ex) {
                page=PageConstants.ERROR_PAGE_PATH;
                return page;
        } catch (DAOSQLException ex) {
                page=PageConstants.ERROR_PAGE_PATH;
                return page;}
    }
    page=PageConstants.ADMIN_PAGE_PATH;
    return page;
}
/**
 * Method is used when user wants to leave the specified groups 
 * @param group
 * @param sessUser
 * @param result
 * @param model
 * @return
 * @throws ResourceCreationException
 */
@RequestMapping(value = URLConstants.USER_DELETE_FROM_GROUP, method=RequestMethod.POST) 
public String userWantsToDeleteFromGroup(@ModelAttribute(ParameterConstants.GROUP) Group group,
		BindingResult result, Model model
		) throws ResourceCreationException{
   String page=null;
   List<String> deleteIdsString=null;
   Integer [] deleteIds;
   deleteIdsString=group.getSelectedIds();
   
   if (deleteIdsString!=null){
       deleteIds=new Integer[deleteIdsString.size()];
       for (int idx=0; idx< deleteIds.length; idx++){
           deleteIds[idx]=Integer.parseInt(deleteIdsString.get(idx).trim());
       }
       
   User sessUser=null;
   Authentication auth = SecurityUtil.getAuthentication();
	if (!(auth instanceof AnonymousAuthenticationToken)){
		 try {
			sessUser = userDAO.findByUsername(auth.getName());
		} catch (ResourceCreationException | DAOSQLException | MyLogicalInvalidParameterException e) {
			page = PageConstants.ERROR_PAGE_PATH;
			return page;
		} 
	}
           try {
               registrationDAO.deleteUserFromGroups(sessUser.getId(), deleteIds);
               statementLogic.updateOrAddUserStatement(sessUser, model);
               List <Group> userGroups= registrationDAO.showUserGroups(sessUser.getId());
               model.addAttribute(ParameterConstants.GR_SPORTTYPES, userGroups);
				log.info("User № " + sessUser.getId()
						+ " has left from "+ deleteIds.length+" groups.");
           } catch (MyLogicalInvalidParameterException ex) {
               page=PageConstants.ERROR_PAGE_PATH;
               return page;
           } catch (DAOSQLException ex) {
               page=PageConstants.ERROR_PAGE_PATH;
               return page;
           }
       
       page=PageConstants.USERCABINET_PAGE_PATH;
   }
   else {
       page=PageConstants.USERCABINET_PAGE_PATH;
   }
   return page;
}


@RequestMapping(value = URLConstants.USER_DELETE_FROM_GROUP, method = RequestMethod.GET) 
public ModelAndView userWantsToDeleteFromGroup(){
	Group group = new Group();
    return new ModelAndView(PageConstants.USERCABINET_PAGE_PATH, "group", group);
}
  
}
