package com.fclub.spring.mvc.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.fclub.persistence.model.Sporttype;
import com.fclub.persistence.model.Statement;
import com.fclub.persistence.model.User;
import com.fclub.util.SecurityUtil;
/**
 * This class implements business logic of Spring MVC View Controller.
 * @author Anastasiya Kisel
 */
@Controller
@SessionAttributes({ParameterConstants.GR_SPORTTYPES, 
					ParameterConstants.ALL_STATEMENTS,
					ParameterConstants.GR_SPORTTYPES,
					ParameterConstants.USERSTATEMENT,
					ParameterConstants.NUMBER, 
})
public class ViewController {
	
	private Logger log = Logger.getLogger(ViewController.class);	
	
    @Autowired
    @Qualifier("DAOJpaUser")
    private IDAOUser userDAO ;
    
    @Autowired
    @Qualifier("DAOJpaGroup")
    private IDAOGroup groupDAO ;
    
    @Autowired
    @Qualifier("DAOJpaStatement")
    private IDAOStatement statementDAO ;    
    
    @Autowired
    @Qualifier("DAOJpaRegistration")
    private IDAORegistration registrationDAO ;
    
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
     * This method is used when admin wants to see all the training groups in the fitness clubs.
     * @param group - group
     * @param result - BindingResult
     * @param model - Model
     * @return admin page for all training groups
     */
	@RequestMapping(value = URLConstants.ADMIN_VIEW_ALL_TRAININGS, method = RequestMethod.GET) 
	public String showAllTrainingsToAdmin(@ModelAttribute(ParameterConstants.GROUP) Group group, BindingResult result, Model model){	
		List<Group> allGroups=null;
        try {
            allGroups=groupDAO.adminShowAllGroups();
            allGroups=groupLogic.updatePeopleRegistered(allGroups);
            model.addAttribute(ParameterConstants.GR_SPORTTYPES, allGroups);
        } catch (DAOSQLException | MyLogicalInvalidParameterException | ResourceCreationException ex) {
            return PageConstants.ERROR_PAGE_PATH;
        } 
        log.info("Admin had been viewing all groups of the fitness club.");
        model.addAttribute(ParameterConstants.ALLGROUPS, allGroups);
        return PageConstants.ADMIN_PAGE_PATH;
	}
	/**
	 * This method is used when admin wants to see all the user's statements in the fitness clubs.
	 * @param statement - Statement
	 * @param result - BindingResult
	 * @param model - Model
	 * @return page
	 */
	@RequestMapping(value = URLConstants.ADMIN_VIEW_ALL_USER_STATEMENTS, method = RequestMethod.GET) 
	public String showAllUserStatementsToAdmin(@ModelAttribute("statement") Statement statement, BindingResult result, Model model){	
        List<Statement> allUserStatements=null;
        try {
            allUserStatements=statementDAO.getAllUserStatements();            
        } catch (DAOSQLException | MyLogicalInvalidParameterException | ResourceCreationException ex) {
            return PageConstants.ERROR_PAGE_PATH;
        } 
        log.info("Admin had been viewing all users of the fitness club.");
        model.addAttribute(ParameterConstants.ALL_STATEMENTS, allUserStatements);
        model.addAttribute(ParameterConstants.GROUP, new Group());   
        return PageConstants.ADMIN_ALL_USERS_PATH;
	}
	/**
	 * Provides the groups of specified sport type.
	 * @param sporttype - sport type
	 * @param group - group
	 * @param result - BindingResult
	 * @param model - Model
	 * @return page
	 */
	@RequestMapping(value = URLConstants.USER_VIEW_GROUPS_OF_SPORTTYPE, method = RequestMethod.POST) 
	public String viewGroupsOfSporttype(@ModelAttribute (ParameterConstants.SPORTTYPE) Sporttype sporttype, 
			@ModelAttribute (ParameterConstants.GROUP) Group group,
			BindingResult result, Model model){
		int sporttypeId=sporttype.getId();
        List<Group> sportGroups=null;
        try {
            sportGroups=groupDAO.showAllGroups(sporttypeId);
            sportGroups=groupLogic.updatePeopleRegistered(sportGroups);                
            model.addAttribute(ParameterConstants.SPORTGROUPS, sportGroups);
        } catch (MyLogicalInvalidParameterException | DAOSQLException | ResourceCreationException ex) {
        	return PageConstants.ERROR_PAGE_PATH;
        } 
        return PageConstants.ORDER_PATH;
	}
	
	@RequestMapping(value = URLConstants.USER_VIEW_GROUPS_OF_SPORTTYPE, method = RequestMethod.GET) 
	public String viewGroupsOfSporttype(){
		return "redirect:/order.html";
	}

	/**
	 * Provides the ability for the user to enter his cabinet in order to view his statement.
	 * @param sessUser - user
	 * @param group - group
	 * @param result - BindingResult
	 * @param model - Model
	 * @return user's cabinet page
	 */
	@RequestMapping(value = {URLConstants.USER_CABINET, URLConstants.ADMIN_CABINET}, method = RequestMethod.GET) 
	public String viewStatement(@ModelAttribute(ParameterConstants.GROUP) Group group,
								BindingResult result, Model model){
		Statement userStatement=new Statement();
        Authentication auth = SecurityUtil.getAuthentication();
        try {
        	User user = userDAO.findByUsername(auth.getName());
         	statementLogic.updateOrAddUserStatement(user, model);
            userStatement=statementLogic.getStatementOfUser(user.getId());
            List <Group> userGroups= registrationDAO.showUserGroups(user.getId());
            userGroups=groupLogic.updatePeopleRegistered(userGroups);
            model.addAttribute(ParameterConstants.GR_SPORTTYPES, userGroups);   
            log.info("User № "+user.getId()+" had been viewing his user cabinet.");
        } catch (MyLogicalInvalidParameterException | DAOSQLException | ResourceCreationException  ex) {
            return PageConstants.ERROR_PAGE_PATH;
        }         
        model.addAttribute(ParameterConstants.USERSTATEMENT, userStatement);
        return PageConstants.USERCABINET_PAGE_PATH;
	}
	
	/**
	 * Gives the ability for admin to view the attendees of any specified group.
	 * @param group - group
	 * @param model - Model
	 * @param res - BindingResult
	 * @return admin page
	 * @throws ResourceCreationException
	 */
	@RequestMapping(value = URLConstants.ADMIN_VIEW_USERS_OF_GROUP, method = RequestMethod.POST) 
	public String viewUsersOfGroup(@ModelAttribute(ParameterConstants.GROUP) Group group, Model model, BindingResult res) throws ResourceCreationException{
        int number=group.getId();
        model.addAttribute(ParameterConstants.NUMBER, number);
        model.addAttribute(ParameterConstants.STATEMENT, new Statement());
        try {
        	List<User> usersOfGroup=registrationDAO.getUsersFromGroup(number);
            viewLogic.showUserStatements(usersOfGroup, model);
            log.info("Admin had been viewing users of group № "+number);
        } catch (DAOSQLException |MyLogicalInvalidParameterException ex) {
        	return PageConstants.ERROR_PAGE_PATH;
        } 
        return PageConstants.ADMIN_PAGE_PATH;
	}
	
	@RequestMapping(value = URLConstants.ADMIN_VIEW_USERS_OF_GROUP, method = RequestMethod.GET) 
	public ModelAndView viewUsersOfGroup() throws ResourceCreationException{
		Group group = new Group();
        return new ModelAndView(PageConstants.ADMIN_PAGE_PATH, "group", group);
	}
	
	/**
	 * Gives the ability for admin to view the groups of any specified user.
	 * @param statement - statement
	 * @param result - BindingResult
	 * @param model - Model
	 * @return page
	 */
	@RequestMapping(value = URLConstants.ADMIN_VIEW_GROUPS_OF_USER, method = RequestMethod.POST) 
	public String viewAdminGroupsOfUser(@ModelAttribute("statement") Statement statement, BindingResult result, Model model){
        int userId=statement.getId();
        model.addAttribute(ParameterConstants.GROUP, new Group());        
        try {
        	adminLogic.showToAdminUserGroups(userId, model);
        } catch (DAOSQLException | MyLogicalInvalidParameterException | ResourceCreationException ex) {
        	return PageConstants.ERROR_PAGE_PATH;
        }
        log.info("Admin had been viewing groups of user № "+userId);       
        return PageConstants.ADMIN_ALL_USERS_PATH;
	}
	
	/**
	 * Shows main page.
	 * @return main page
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = {URLConstants.USER_HOME, URLConstants.ADMIN_HOME}, method = RequestMethod.GET)  
	public String showMainPage(){
		return  PageConstants.MAIN_PAGE_PATH;
	}
	
	@RequestMapping(value = URLConstants.START, method = RequestMethod.GET)  
	public String showStartPage(){
		return  PageConstants.START_PAGE_PATH;
	}
	
	
	@RequestMapping(value = URLConstants.INDEX, method = RequestMethod.GET)  
	public String showIndexPage(){
		return  PageConstants.INDEX_PAGE_PATH;
	}	
	
	@RequestMapping(value = URLConstants.LOGIN, method = RequestMethod.GET)  
	public ModelAndView showLoginPage(@RequestParam(value = "error", required = false) String error){
		User user = new User();
        return new ModelAndView(PageConstants.LOGIN_PAGE_PATH, "user", user);
	}

}
