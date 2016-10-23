/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclub.busness.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fclub.constants.ParameterConstants;
import com.fclub.persistence.dao.GroupJpaRepository;
import com.fclub.persistence.dao.RegistrationJpaRepository;
import com.fclub.persistence.model.Group;
/**
 * This class implements the logic related to admin actions.
 * @author Anastasiya Kisel
 */
@Component("AdminLogic")
@SessionAttributes({ParameterConstants.ADMIN_GR_SPORTTYPES})
public class AdminLogic { // NOPMD - Bean is managed by Spring so no evident constructor is required
	
    @Autowired
    @Qualifier("DAOJpaRegistration")
    private RegistrationJpaRepository registrationDAO ;
    
    @Autowired
    @Qualifier("DAOJpaGroup")
    private GroupJpaRepository groupDAO ; 
    
	/**
	 * Shows to admin groups of the specified user.
	 * @param userId - user's id
	 * @param model - Spring Model object
	 */
    public final void showToAdminUserGroups(final Long userId, final Model model) {
        final List<Group> userGroups = showUserGroups(userId);
        if (userGroups.isEmpty()){
            model.addAttribute(ParameterConstants.PASSIVE_USER,
                                 ParameterConstants.PASSIVE_USER);
        }
        model.addAttribute(ParameterConstants.ADMIN_GR_SPORTTYPES, userGroups); 
    }
	/**
	 * Shows to admin groups of the specified user.
	 * @param userId
	 * @return the list of user's groups
	 */
    private List<Group> showUserGroups(final Long userId) {
    	return registrationDAO.findByUserId(userId).stream().map(reg -> reg.getGroup()).collect(Collectors.toList());
   }
}
