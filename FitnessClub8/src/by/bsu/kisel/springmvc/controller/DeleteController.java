package by.bsu.kisel.springmvc.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import by.bsu.kisel.constants.PageConstants;
import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.dao.IDAORegistration;
import by.bsu.kisel.dao.IDAOStatement;
import by.bsu.kisel.dao.IDAOUser;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.logic.AdminLogic;
import by.bsu.kisel.logic.GroupLogic;
import by.bsu.kisel.logic.StatementLogic;
import by.bsu.kisel.logic.ViewLogic;
import by.bsu.kisel.model.Group;
import by.bsu.kisel.model.Statement;
import by.bsu.kisel.model.User;
/**
 * This class implements business logic of Spring MVC Delete Controller.
 * @author Anastasiya Kisel
 */
@Controller
@SessionAttributes({
	ParameterConstants.ALL_STATEMENTS,
	ParameterConstants.PARAMETER_GR_SPORTTYPES, 
	ParameterConstants.PARAMETER_NUMBER, 
	ParameterConstants.PARAMETER_USER, 
	ParameterConstants.PARAMETER_USER_ID
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
  @RequestMapping(value = "/deleteUserFromTrainings.html", method=RequestMethod.POST) 
  public String deleteUserFromTrainings(@ModelAttribute(ParameterConstants.PARAMETER_GROUP) Group group, 
		  				@ModelAttribute(ParameterConstants.PARAMETER_USER_ID) Integer userId,
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
        User user = new User();
        user.setId(userId);
        try {
            user.setId(userId);
            registrationDAO.deleteUserFromGroups(user, deleteIds);
            adminLogic.showToAdminUserGroups(user.getId(), model);
            statementLogic.getStatementOfUser(user);
            List<Statement> allUserStatements=statementDAO.getAllUserStatements();
            model.addAttribute(ParameterConstants.ALL_STATEMENTS, allUserStatements);
            model.addAttribute(ParameterConstants.PARAMETER_STATEMENT, new Statement());
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
 @RequestMapping(value = "/deleteUsersFromGroup.html", method=RequestMethod.POST) 
 public String deleteUsersFromGroup(@ModelAttribute(ParameterConstants.PARAMETER_STATEMENT) Statement statement,
					@ModelAttribute(ParameterConstants.PARAMETER_NUMBER) Integer numberGroup,
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
            model.addAttribute(ParameterConstants.PARAMETER_GR_SPORTTYPES, allGroups);
            model.addAttribute(ParameterConstants.PARAMETER_GROUP, new Group());
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
@RequestMapping(value = "/userWantsToDeleteFromGroup.html", method=RequestMethod.POST) 
public String userWantsToDeleteFromGroup(@ModelAttribute(ParameterConstants.PARAMETER_GROUP) Group group,
		@ModelAttribute(ParameterConstants.PARAMETER_USER) User sessUser,
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
           try {
               registrationDAO.deleteUserFromGroups(sessUser, deleteIds);
               statementLogic.updateOrAddUserStatement(sessUser, model);
               List <Group> userGroups= registrationDAO.showUserGroups(sessUser.getId());
               model.addAttribute(ParameterConstants.PARAMETER_GR_SPORTTYPES, userGroups);
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

  
}
