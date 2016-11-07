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

import com.fclub.business.logic.GroupLogic;
import com.fclub.business.logic.SignUpLogic;
import com.fclub.business.logic.StatementLogic;
import com.fclub.constants.PageConstants;
import com.fclub.constants.ParameterConstants;
import com.fclub.constants.URLConstants;
import com.fclub.exception.FClubInvalidParameterException;
import com.fclub.persistence.model.Group;
import com.fclub.persistence.model.Registration;
import com.fclub.persistence.model.Sporttype;
import com.fclub.persistence.model.User;
import com.fclub.persistence.repository.GroupJpaRepository;
import com.fclub.persistence.repository.RegistrationJpaRepository;
import com.fclub.persistence.repository.UserJpaRepository;
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

	private static final Logger log = Logger.getLogger(SignUpController.class);

	@Autowired
	private UserJpaRepository userDAO;

	@Autowired
	private GroupJpaRepository groupDAO;

	@Autowired
	private RegistrationJpaRepository registrationDAO;
	
	// Autowiring Logic classes
	@Autowired
	private GroupLogic groupLogic;

	@Autowired
	private SignUpLogic signUpLogic;

	@Autowired
	private StatementLogic statementLogic;
	/**
	 * Shows order page.
	 * @return order page
	 */
	@RequestMapping(value = {URLConstants.USER_ORDER, URLConstants.ADMIN_ORDER} , method = RequestMethod.GET)
	public String showPage(final Model model) {	
		final List<Group> groups = groupDAO.findAll();
		model.addAttribute(ParameterConstants.SPORTTYPE, new Sporttype());
		model.addAttribute(ParameterConstants.SPORTGROUPS, groups);
		model.addAttribute(ParameterConstants.GROUP, new Group());
		return PageConstants.ORDER_PATH;
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
			@ModelAttribute(ParameterConstants.GROUP) final Group group, final BindingResult result,
			final Model model) {
		final List<String> selectedIds = group.getSelectedIds();
		String page = null;
		final Authentication auth = SecurityUtil.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)){
			 try {
				User user = userDAO.findByUsername(auth.getName());
				if (selectedIds != null) {
					List<Long> initialOrderIds = selectedIds.stream().map(stringId -> Long.parseLong(stringId.trim())).collect(Collectors.toList());
					List<Group> availableGroups  = signUpLogic.getAvailableGroups(initialOrderIds);
					availableGroups.stream().forEach(gr -> {
						Registration reg = new Registration(user, gr);
						registrationDAO.save(reg);
					});
					statementLogic.updateOrAddUserStatement(user, model);
					List<Group> userGroups = registrationDAO.findByUserId(user.getId()).stream().map(reg -> reg.getGroup()).collect(Collectors.toList());
					userGroups = groupLogic.updatePeopleRegistered(userGroups);
					model.addAttribute(ParameterConstants.GR_SPORTTYPES, userGroups);
					log.info("User № "+user.getId()+" has signed up for "+availableGroups.size()+" training groups.");
					
					page = PageConstants.USERCABINET_PAGE_PATH;
					if (!availableGroups.isEmpty()) {
						model.addAttribute(ParameterConstants.GREETING, ParameterConstants.GREETING);
					}
				} else {
					page = PageConstants.ORDER_PATH;
					
				}
			} catch (FClubInvalidParameterException e) {
				page = PageConstants.ERROR_PAGE_PATH;
				return page;
			} 
		}		
		return page;
	}
}
