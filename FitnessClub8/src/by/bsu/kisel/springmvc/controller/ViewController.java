package by.bsu.kisel.springmvc.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import by.bsu.kisel.constants.PageConstants;
import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.dao.IDAORegistration;
import by.bsu.kisel.dao.IDAOStatement;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.logic.AdminLogic;
import by.bsu.kisel.logic.GroupLogic;
import by.bsu.kisel.logic.StatementLogic;
import by.bsu.kisel.logic.ViewLogic;
import by.bsu.kisel.model.Group;
import by.bsu.kisel.model.Sporttype;
import by.bsu.kisel.model.Statement;
import by.bsu.kisel.model.User;
/**
 * This class implements business logic of Spring MVC View Controller.
 * @author Anastasiya Kisel
 */
@Controller
@SessionAttributes({ParameterConstants.PARAMETER_GR_SPORTTYPES, 
					ParameterConstants.ALL_STATEMENTS,
					ParameterConstants.PARAMETER_GR_SPORTTYPES,
					ParameterConstants.PARAMETER_USERSTATEMENT,
					ParameterConstants.PARAMETER_USER, 
					ParameterConstants.PARAMETER_NUMBER, 
					ParameterConstants.PARAMETER_USER_ID,
					ParameterConstants.PARAMETER_USER
})
public class ViewController {
	
	private Logger log = Logger.getLogger(ViewController.class);
	
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
	@RequestMapping(value = "/viewAllTrainings.html", method = RequestMethod.GET) 
	public String showAllTrainingsToAdmin(@ModelAttribute(ParameterConstants.PARAMETER_GROUP) Group group, BindingResult result, Model model){	
		List<Group> allGroups=null;
        String page = null;
        try {
            allGroups=groupDAO.adminShowAllGroups();
            allGroups=groupLogic.updatePeopleRegistered(allGroups);
            model.addAttribute(ParameterConstants.PARAMETER_GR_SPORTTYPES, allGroups);
        } catch (DAOSQLException | MyLogicalInvalidParameterException | ResourceCreationException ex) {
                page=PageConstants.ERROR_PAGE_PATH;
                return page;
        } 
        log.info("Admin had been viewing all groups of the fitness club.");
        model.addAttribute(ParameterConstants.PARAMETER_ALLGROUPS, allGroups);
        page=PageConstants.ADMIN_PAGE_PATH;
        return page;
	}
	/**
	 * This method is used when admin wants to see all the user's statements in the fitness clubs.
	 * @param statement - Statement
	 * @param result - BindingResult
	 * @param model - Model
	 * @return page
	 */
	@RequestMapping(value = "/viewAllUserStatements.html", method = RequestMethod.GET) 
	public String showAllUserStatementsToAdmin(@ModelAttribute("statement") Statement statement, BindingResult result, Model model){	
		String page = null;
        List<Statement> allUserStatements=null;
        try {
            allUserStatements=statementDAO.getAllUserStatements();            
        } catch (DAOSQLException | MyLogicalInvalidParameterException | ResourceCreationException ex) {
                page=PageConstants.ERROR_PAGE_PATH;
                return page;
        } 
        log.info("Admin had been viewing all users of the fitness club.");
        page=PageConstants.ADMIN_ALL_USERS_PATH;
        model.addAttribute(ParameterConstants.ALL_STATEMENTS, allUserStatements);
        model.addAttribute(ParameterConstants.PARAMETER_GROUP, new Group());   
        return page;
	}
	/**
	 * Provides the groups of specified sport type.
	 * @param sporttype - sport type
	 * @param group - group
	 * @param result - BindingResult
	 * @param model - Model
	 * @return page
	 */
	@RequestMapping(value = "/viewGroupsOfSporttype.html", method = RequestMethod.POST) 
	public String viewGroupsOfSporttype(@ModelAttribute (ParameterConstants.PARAMETER_SPORTTYPE) Sporttype sporttype, 
			@ModelAttribute (ParameterConstants.PARAMETER_GROUP) Group group,
			BindingResult result, Model model){
		String page = null;
		int sporttypeId=sporttype.getId();
        List<Group> sportGroups=null;
        try {
            sportGroups=groupDAO.showAllGroups(sporttypeId);
            sportGroups=groupLogic.updatePeopleRegistered(sportGroups);                
            model.addAttribute(ParameterConstants.PARAMETER_SPORTGROUPS, sportGroups);
        } catch (MyLogicalInvalidParameterException | DAOSQLException | ResourceCreationException ex) {
        	page=PageConstants.ERROR_PAGE_PATH;
            return page;
        } 
        page=PageConstants.ORDER_PATH;
        return page;
	}
	
	@RequestMapping(value = "/viewGroupsOfSporttype.html", method = RequestMethod.GET) 
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
	@RequestMapping(value = "/usercabinet.html", method = RequestMethod.GET) 
	public String viewStatement(@ModelAttribute(ParameterConstants.PARAMETER_USER) User sessUser,
								@ModelAttribute(ParameterConstants.PARAMETER_GROUP) Group group,
								BindingResult result, Model model){
		Statement userStatement=new Statement();
        String page = null;
        User user = sessUser;
        try {
         	statementLogic.updateOrAddUserStatement(user, model);
            userStatement=statementLogic.getStatementOfUser(user);
            List <Group> userGroups= registrationDAO.showUserGroups(user.getId());
            userGroups=groupLogic.updatePeopleRegistered(userGroups);
            //added this line temporary
            model.addAttribute(ParameterConstants.PARAMETER_USER, user);
            //added above line temporary
            model.addAttribute(ParameterConstants.PARAMETER_GR_SPORTTYPES, userGroups);            
        } catch (MyLogicalInvalidParameterException | DAOSQLException | ResourceCreationException  ex) {
            page=PageConstants.ERROR_PAGE_PATH;
            return page;
        } 
        log.info("User № "+user.getId()+" had been viewing his user cabinet.");
        model.addAttribute(ParameterConstants.PARAMETER_USERSTATEMENT, userStatement);
        page=PageConstants.USERCABINET_PAGE_PATH;
        return page;
	}
	
	/**
	 * Gives the ability for admin to view the attendees of any specified group.
	 * @param group - group
	 * @param model - Model
	 * @param res - BindingResult
	 * @return admin page
	 * @throws ResourceCreationException
	 */
	@RequestMapping(value = "/viewUsersOfGroup.html", method = RequestMethod.POST) 
	public String viewUsersOfGroup(@ModelAttribute(ParameterConstants.PARAMETER_GROUP) Group group, Model model, BindingResult res) throws ResourceCreationException{
        String page = null;
        int number=group.getId();
        model.addAttribute(ParameterConstants.PARAMETER_NUMBER, number);
        model.addAttribute(ParameterConstants.PARAMETER_STATEMENT, new Statement());
        try {
        	List<User> usersOfGroup=registrationDAO.getUsersFromGroup(number);
            viewLogic.showUserStatements(usersOfGroup, model);
            log.info("Admin had been viewing users of group № "+number);
        } catch (DAOSQLException |MyLogicalInvalidParameterException ex) {
        	page=PageConstants.ERROR_PAGE_PATH;
            return page;
        } 
        page=PageConstants.ADMIN_PAGE_PATH;
        return page;
	}
	
	@RequestMapping(value = "/viewUsersOfGroup.html", method = RequestMethod.GET) 
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
	@RequestMapping(value = "/viewAdminGroupsOfUser.html", method = RequestMethod.POST) 
	public String viewAdminGroupsOfUser(@ModelAttribute("statement") Statement statement, BindingResult result, Model model){
        String page = null;
        int userId=statement.getId();
        model.addAttribute(ParameterConstants.PARAMETER_USER_ID, userId);
        model.addAttribute(ParameterConstants.PARAMETER_GROUP, new Group());
        
        try {
        	adminLogic.showToAdminUserGroups(userId, model);
        } catch (DAOSQLException | MyLogicalInvalidParameterException | ResourceCreationException ex) {
        	page=PageConstants.ERROR_PAGE_PATH;
            return page;
        }
        log.info("Admin had been viewing groups of user № "+userId);       
        page=PageConstants.ADMIN_ALL_USERS_PATH;
        return page;
	}
	
	/**
	 * Shows main page.
	 * @return main page
	 */
	@RequestMapping(value = "/home.html", method = RequestMethod.GET)  
	public String showMainPage(){
		return  PageConstants.MAIN_PAGE_PATH;
	}
	
	@RequestMapping(value = "/start.html", method = RequestMethod.GET)  
	public String showStartPage(){
		return  PageConstants.START_PAGE_PATH;
	}
}
