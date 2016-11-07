package com.fclub.spring.mvc.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.fclub.persistence.model.Discount;
import com.fclub.persistence.model.Group;
import com.fclub.persistence.model.Sporttype;
import com.fclub.persistence.model.Statement;
import com.fclub.persistence.model.User;
import com.fclub.persistence.repository.DiscountJpaRepository;
import com.fclub.persistence.repository.GroupJpaRepository;
import com.fclub.persistence.repository.RegistrationJpaRepository;
import com.fclub.persistence.repository.StatementJpaRepository;
import com.fclub.persistence.repository.UserJpaRepository;
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
	
	private final Logger log = Logger.getLogger(ViewController.class);	
	
    @Autowired
    private UserJpaRepository userDAO ;
    
    @Autowired
    private GroupJpaRepository groupDAO ;
    
    @Autowired
    private StatementJpaRepository statementDAO ;    
    
    @Autowired
    private RegistrationJpaRepository registrationDAO ;
    
    //Autowiring Logic classes
    @Autowired
    private GroupLogic groupLogic ;
    
    @Autowired
    private StatementLogic statementLogic ; 
    
    @Autowired
    private ViewLogic viewLogic ;
    
    @Autowired
    private AdminLogic adminLogic ;
    
    @Autowired
	private DiscountJpaRepository discountDAO;
    /**
     * This method is used when admin wants to see all the training groups in the fitness clubs.
     * @param group - group
     * @param result - BindingResult
     * @param model - Model
     * @return admin page for all training groups
     */
	@RequestMapping(value = URLConstants.ADMIN_VIEW_ALL_TRAININGS, method = RequestMethod.GET) 
	public String showAllTrainingsToAdmin(@ModelAttribute(ParameterConstants.GROUP) final Group group, final BindingResult result, final Model model){	
		List<Group> allGroups = groupDAO.findAll();
		allGroups = groupLogic.updatePeopleRegistered(allGroups);
		model.addAttribute(ParameterConstants.GR_SPORTTYPES, allGroups);

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
	public String showAllUserStatementsToAdmin(@ModelAttribute("statement") final Statement statement, final BindingResult result, final Model model){	
        List<Statement> allUserStatements=null;
        allUserStatements=statementDAO.findAll();            
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
	@RequestMapping(value = {URLConstants.USER_VIEW_GROUPS_OF_SPORTTYPE,
			URLConstants.ADMIN_VIEW_GROUPS_OF_SPORTTYPE}, method = RequestMethod.POST) 
	public String viewGroupsOfSporttype(@ModelAttribute (ParameterConstants.SPORTTYPE) final Sporttype sporttype, 
			@ModelAttribute (ParameterConstants.GROUP) final Group group,
			final BindingResult result, final Model model){
		final Long sporttypeId = sporttype.getId();
		List<Group> sportGroups = null;
		sportGroups = groupDAO.findBySporttypeId(sporttypeId);
		sportGroups = groupLogic.updatePeopleRegistered(sportGroups);
		model.addAttribute(ParameterConstants.SPORTGROUPS, sportGroups);

		return PageConstants.ORDER_PATH;
	}
	
	@RequestMapping(value = {URLConstants.USER_VIEW_GROUPS_OF_SPORTTYPE,
			URLConstants.ADMIN_VIEW_GROUPS_OF_SPORTTYPE}, method = RequestMethod.GET) 
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
	public String viewStatement(@ModelAttribute(ParameterConstants.GROUP) final Group group,
								final BindingResult result, final Model model){
		Statement userStatement=new Statement();
        final Authentication auth = SecurityUtil.getAuthentication();
        try {
        	final User user = userDAO.findByUsername(auth.getName());
         	statementLogic.updateOrAddUserStatement(user, model);
            userStatement=statementLogic.getStatementOfUser(user.getId());
            List <Group> userGroups= registrationDAO.findByUserId(user.getId()).stream().map(reg -> reg.getGroup()).collect(Collectors.toList());
            userGroups=groupLogic.updatePeopleRegistered(userGroups);
            model.addAttribute(ParameterConstants.GR_SPORTTYPES, userGroups);   
            log.info("User № "+user.getId()+" had been viewing his user cabinet.");
        } catch (FClubInvalidParameterException ex) {
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
	public String viewUsersOfGroup(@ModelAttribute(ParameterConstants.GROUP) final Group group, final Model model, final BindingResult res) {
        model.addAttribute(ParameterConstants.NUMBER, group.getId());
        model.addAttribute(ParameterConstants.STATEMENT, new Statement());
        try {
        	final List<User> usersOfGroup=registrationDAO.findByGroupId(group.getId()).stream().map(reg-> reg.getUser()).collect(Collectors.toList());
            viewLogic.showUserStatements(usersOfGroup, model);
            log.info("Admin had been viewing users of group № "+group.getId());
        } catch (FClubInvalidParameterException ex) {
        	return PageConstants.ERROR_PAGE_PATH;
        } 
        return PageConstants.ADMIN_PAGE_PATH;
	}
	
	@RequestMapping(value = URLConstants.ADMIN_VIEW_USERS_OF_GROUP, method = RequestMethod.GET) 
	public ModelAndView viewUsersOfGroup() {
		final Group group = new Group();
        return new ModelAndView(PageConstants.ADMIN_PAGE_PATH, "group", group);
	}
	
	/**
	 * Gives the ability for admin to view the groups of any specified user.
	 * @param statement - statement
	 * @param result - BindingResult
	 * @param model - Model
	 * @return page
	 */
	@RequestMapping(value = {URLConstants.ADMIN_VIEW_GROUPS_OF_USER, URLConstants.GENERIC_VIEW_GROUPS_OF_USER}, method = RequestMethod.POST) 
	public String viewAdminGroupsOfUser(@ModelAttribute("statement") final Statement statement, final BindingResult result, final Model model){
        final Long userId=statement.getId();
        model.addAttribute(ParameterConstants.GROUP, new Group());        
        adminLogic.showToAdminUserGroups(userId, model);
        log.info("Admin had been viewing groups of user № "+userId);       
        return PageConstants.ADMIN_ALL_USERS_PATH;
	}
	
	/**
	 * Shows main page.
	 * @return main page
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = {URLConstants.USER_HOME, URLConstants.ADMIN_HOME}, method = RequestMethod.GET)  
	public String showMainPage(final ModelMap map, final HttpServletRequest request){
		final List<Discount> discounts =  discountDAO.findAll();
		request.getSession().setAttribute(ParameterConstants.ALL_DISCOUNTS, discounts);			
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
	public ModelAndView showLoginPage(@RequestParam(value = "error", required = false) final String error){
		final User user = new User();
        return new ModelAndView(PageConstants.LOGIN_PAGE_PATH, "user", user);
	}

}
