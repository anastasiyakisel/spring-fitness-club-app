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
import org.springframework.web.servlet.ModelAndView;

import by.bsu.kisel.constants.PageConstants;
import by.bsu.kisel.constants.ParameterConstants;
import by.bsu.kisel.dao.IDAOGroup;
import by.bsu.kisel.dao.IDAORegistration;
import by.bsu.kisel.dao.IDAOUser;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.logic.GroupLogic;
import by.bsu.kisel.logic.SignUpLogic;
import by.bsu.kisel.logic.StatementLogic;
import by.bsu.kisel.model.Group;
import by.bsu.kisel.model.Sporttype;
import by.bsu.kisel.model.User;
/**
 * This class implements business logic of Spring MVC Sign up Controller.
 * @author Anastasiya Kisel
 */
@Controller
@SessionAttributes({ ParameterConstants.PARAMETER_USER,
		ParameterConstants.PARAMETER_GR_SPORTTYPES })
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
	@RequestMapping(value = "/order.html", method = RequestMethod.GET)
	public ModelAndView showPage() {
		Sporttype sporttype = new Sporttype();
		return new ModelAndView(PageConstants.ORDER_PATH, "sporttype",
				sporttype);
	}
	/**
	 * Performs sign up logic.
	 * @param sessUser - user
	 * @param group - group
	 * @param result - BindingResult
	 * @param model - Model
	 * @return user's cabinet page if signup was successful; otherwise - order page
	 */
	@RequestMapping(value = "/signup.html", method = RequestMethod.POST)
	public String signUpToTheTraining(
			@ModelAttribute(ParameterConstants.PARAMETER_USER) User sessUser,
			@ModelAttribute(ParameterConstants.PARAMETER_GROUP) Group group, BindingResult result,
			Model model) {
		List<String> selectedIds = group.getSelectedIds();
		String page = null;
		Integer[] ordIds;
		User user = sessUser;
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
				model.addAttribute(ParameterConstants.PARAMETER_GR_SPORTTYPES, userGroups);
				log.info("User № "+sessUser.getId()+" has signed up for "+ordIds.length+" training groups.");
			} catch (MyLogicalInvalidParameterException | DAOSQLException
					| ResourceCreationException ex) {
				page = PageConstants.ERROR_PAGE_PATH;
				return page;
			}
			page = PageConstants.USERCABINET_PAGE_PATH;

			if (ordIds.length > 0) {
				model.addAttribute(ParameterConstants.PARAMETER_GREETING,
						ParameterConstants.PARAMETER_GREETING);

			}
		} else {
			page = PageConstants.ORDER_PATH;
			
		}
		return page;
	}
}
