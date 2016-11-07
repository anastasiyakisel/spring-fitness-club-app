package com.fclub.spring.mvc.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.fclub.business.logic.AdminLogic;
import com.fclub.business.logic.GroupLogic;
import com.fclub.business.logic.StatementLogic;
import com.fclub.business.logic.ViewLogic;
import com.fclub.constants.PageConstants;
import com.fclub.constants.ParameterConstants;
import com.fclub.constants.URLConstants;
import com.fclub.exception.FClubInvalidParameterException;
import com.fclub.persistence.model.Group;
import com.fclub.persistence.model.Statement;
import com.fclub.persistence.model.User;
import com.fclub.persistence.repository.GroupJpaRepository;
import com.fclub.persistence.repository.RegistrationJpaRepository;
import com.fclub.persistence.repository.StatementJpaRepository;
import com.fclub.persistence.repository.UserJpaRepository;
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
	
  private final Logger log = Logger.getLogger(DeleteController.class);
  
  @Autowired
  private GroupJpaRepository groupDAO ;
  
  @Autowired
  private UserJpaRepository userDAO ;
  
  @Autowired
  private RegistrationJpaRepository registrationDAO ;
  
  @Autowired
  private StatementJpaRepository statementDAO ;
  //Autowiring Logic classes
  @Autowired
  private GroupLogic groupLogic ;
  
  @Autowired
  private StatementLogic statementLogic ;
  
  @Autowired
  private ViewLogic viewLogic ;
  
  @Autowired
  private AdminLogic adminLogic ;
 
  /**
   * Used when admin wants to delete user from groups.
   * @param group - group
   * @param userId - user id
   * @param result - BindingResult
   * @param model - Model
   * @return page
   * @throws FClubInvalidParameterException 
   */
	@RequestMapping(value = { URLConstants.ADMIN_DELETE_USERS_FROM_TRAININGS,
			URLConstants.GENERIC_DELETE_USERS_FROM_TRAININGS }, method = RequestMethod.POST)
	public String deleteUserFromTrainings(@ModelAttribute(ParameterConstants.GROUP) final Group group, 
		  				final BindingResult result, final Model model) throws FClubInvalidParameterException{
    String page=null;
    List<Long> deleteIds = group.getSelectedIds().stream().map(id -> Long.parseLong(id.trim())).collect(Collectors.toList());
    if (!deleteIds.isEmpty()){
        User loggedInUser = null;
        final Authentication auth = SecurityUtil.getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)){
			 loggedInUser = userDAO.findByUsername(auth.getName());
		}
        final User user = new User();
        user.setId(loggedInUser.getId());
        registrationDAO.deleteUserFromGroups(user.getId(), deleteIds);
        adminLogic.showToAdminUserGroups(user.getId(), model);
        statementLogic.getStatementOfUser(user.getId());
        final List<Statement> allUserStatements=statementDAO.findAll();
        model.addAttribute(ParameterConstants.ALL_STATEMENTS, allUserStatements);
        model.addAttribute(ParameterConstants.STATEMENT, new Statement());
        log.info("User № "+user.getId()+
            		" has been deleted from  "+deleteIds.size()+" groups by administrator.");    
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
 * @throws FClubInvalidParameterException 
   * @throws ResourceCreationException
   */
 @RequestMapping(value = URLConstants.ADMIN_DELETE_USERS_FROM_GROUP, method=RequestMethod.POST) 
 public String deleteUsersFromGroup(@ModelAttribute(ParameterConstants.STATEMENT) final Statement statement,
					@ModelAttribute(ParameterConstants.NUMBER) final Long groupId,
					final BindingResult result, final Model model) throws FClubInvalidParameterException {
    String page=null;
    List<Long> userIds =statement.getSelectedIds().stream().map(userId -> Long.parseLong(userId.trim())).collect(Collectors.toList());
    
    if (!userIds.isEmpty()){
    	registrationDAO.deleteUsersFromGroup(groupId, userIds);
        List<Group> allGroups=groupDAO.findAll();
        allGroups=groupLogic.updatePeopleRegistered(allGroups);
        model.addAttribute(ParameterConstants.GR_SPORTTYPES, allGroups);
        model.addAttribute(ParameterConstants.GROUP, new Group());
        final List<User> usersOfGroup=registrationDAO.findByGroupId(groupId).stream().map(reg -> reg.getUser()).collect(Collectors.toList());
        viewLogic.showUserStatements(usersOfGroup, model);              
        log.info("Admin has deleted users № "+ userIds.size() +" from group № "+groupId);
        
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
 * @throws FClubInvalidParameterException 
 * @throws ResourceCreationException
 */
@RequestMapping(value = {URLConstants.USER_DELETE_FROM_GROUP, 
		URLConstants.GENERIC_USER_DELETE_FROM_GROUP, URLConstants.ADMIN_USER_DELETE_FROM_GROUP}, method=RequestMethod.POST) 
public String userWantsToDeleteFromGroup(@ModelAttribute(ParameterConstants.GROUP) final Group group,
		final BindingResult result, final Model model
		) throws FClubInvalidParameterException {
		String page = null;
		List<Long> deleteIds = group.getSelectedIds().stream().map(id -> Long.parseLong(id.trim()))
				.collect(Collectors.toList());
		User sessUser = null;

		final Authentication auth = SecurityUtil.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			sessUser = userDAO.findByUsername(auth.getName());
		}
			registrationDAO.deleteUserFromGroups(sessUser.getId(), deleteIds);
			statementLogic.updateOrAddUserStatement(sessUser, model);
			final List<Group> userGroups = registrationDAO.findByUserId(sessUser.getId()).stream()
					.map(reg -> reg.getGroup()).collect(Collectors.toList());
			model.addAttribute(ParameterConstants.GR_SPORTTYPES, userGroups);
			log.info("User № " + sessUser.getId() + " has left from " + deleteIds.size() + " groups.");
		
       page=PageConstants.USERCABINET_PAGE_PATH;
       return page;
}


@RequestMapping(value = URLConstants.USER_DELETE_FROM_GROUP, method = RequestMethod.GET) 
public ModelAndView userWantsToDeleteFromGroup(){
	final Group group = new Group();
    return new ModelAndView(PageConstants.USERCABINET_PAGE_PATH, "group", group);
}
  
}
