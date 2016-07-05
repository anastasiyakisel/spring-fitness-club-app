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

import com.fclub.busness.logic.GroupLogic;
import com.fclub.busness.logic.SignUpLogic;
import com.fclub.busness.logic.StatementLogic;
import com.fclub.constants.PageConstants;
import com.fclub.constants.ParameterConstants;
import com.fclub.constants.URLConstants;
import com.fclub.exception.DAOSQLException;
import com.fclub.exception.MyLogicalInvalidParameterException;
import com.fclub.exception.ResourceCreationException;
import com.fclub.persistence.dao.IDAOGroup;
import com.fclub.persistence.dao.IDAORegistration;
import com.fclub.persistence.dao.IDAOUser;
import com.fclub.persistence.model.Group;
import com.fclub.persistence.model.Sporttype;
import com.fclub.persistence.model.User;
import com.fclub.util.SecurityUtil;
/**
 * This class implements business logic of Spring MVC Sign up Controller.
 * @author Anastasiya Kisel
 */
@Controller
@SessionAttributes({ 
		ParameterConstants.GR_SPORTTYPES, 
		ParameterConstants.SPORTGROUPS})
public class SignUpController {

	private Logger log = Logger.getLogger(SignUpController.class);

	@Autowired
	@Qualifier("DAOJpaUser")
	private IDAOUser userDAO;

	@Autowired
	@Qualifier("DAOJpaGroup")
	private IDAOGroup groupDAO;

	@Autowired
	@Qualifier("DAOJpaRegistration")
	private IDAORegistration registrationDAO;
	
	// Autowiring Logic classes
	@Autowired
	@Qualifier("GroupLogic")
	private GroupLogic groupLogic;

	@Autowired
	@Qualifier("SignUpLogic")
	private SignUpLogic signUpLogic;

	@Autowired
	@Qualifier("StatementLogic")
	private StatementLogic statementLogic;
	/**
	 * Shows order page.
	 * @return order page
	 */
	@RequestMapping(value = {URLConstants.USER_ORDER, URLConstants.ADMIN_ORDER} , method = RequestMethod.GET)
	public String showPage(Model model) {	
		try {
			List<Group> groups = groupDAO.adminShowAllGroups();
			model.addAttribute(ParameterConstants.SPORTTYPE, new Sporttype());
			model.addAttribute(ParameterConstants.SPORTGROUPS, groups);
			model.addAttribute(ParameterConstants.GROUP, new Group());
			return PageConstants.ORDER_PATH;
		} catch (DAOSQLException | MyLogicalInvalidParameterException |ResourceCreationException e) {
			return PageConstants.ERROR_PAGE_PATH;
		} 
		
	}
	/**
	 * Performs sign up logic.
	 * @param sessUser - user
	 * @param group - group
	 * @param result - BindingResult
	 * @param model - Model
	 * @return user's cabinet page if signup was successful; otherwise - order page
	 */
	@RequestMapping(value = {URLConstants.USER_SIGNUP, URLConstants.ADMIN_SIGNUP}, method = RequestMethod.POST)
	public String signUpToTheTraining(
			@ModelAttribute(ParameterConstants.GROUP) Group group, BindingResult result,
			Model model) {
		List<String> selectedIds = group.getSelectedIds();
		String page = null;
		Integer[] ordIds;		
		User user = null;
		Authentication auth = SecurityUtil.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)){
			 try {
				user = userDAO.findByUsername(auth.getName());
			} catch (ResourceCreationException | DAOSQLException | MyLogicalInvalidParameterException e) {
				page = PageConstants.ERROR_PAGE_PATH;
				return page;
			} 
		}
		if (selectedIds != null) {
			ordIds = new Integer[selectedIds.size()];
			for (int idx = 0; idx < selectedIds.size(); idx++) {
				ordIds[idx] = Integer.parseInt(selectedIds.get(idx).trim());
			}
			try {
				ordIds = signUpLogic.getIndexesOfFreeGroups(ordIds);
				registrationDAO.addUserToGroups(user, ordIds);
				statementLogic.updateOrAddUserStatement(user, model);
				List<Group> userGroups = registrationDAO.showUserGroups(user.getId());
				userGroups = groupLogic.updatePeopleRegistered(userGroups);
				model.addAttribute(ParameterConstants.GR_SPORTTYPES, userGroups);
				log.info("User № "+user.getId()+" has signed up for "+ordIds.length+" training groups.");
			} catch (MyLogicalInvalidParameterException | DAOSQLException
					| ResourceCreationException ex) {
				page = PageConstants.ERROR_PAGE_PATH;
				return page;
			}
			page = PageConstants.USERCABINET_PAGE_PATH;

			if (ordIds.length > 0) {
				model.addAttribute(ParameterConstants.GREETING,
						ParameterConstants.GREETING);

			}
		} else {
			page = PageConstants.ORDER_PATH;
			
		}
		return page;
	}
}
