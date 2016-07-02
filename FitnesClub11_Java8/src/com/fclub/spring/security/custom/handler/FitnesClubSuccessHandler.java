package com.fclub.spring.security.custom.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.fclub.constants.ParameterConstants;
import com.fclub.exception.DAOSQLException;
import com.fclub.exception.MyLogicalInvalidParameterException;
import com.fclub.exception.ResourceCreationException;
import com.fclub.persistence.dao.IDAODiscount;
import com.fclub.persistence.model.Discount;

public class FitnesClubSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	@Autowired
	@Qualifier("DAOJpaDiscount")
	private IDAODiscount discountDAO;
	
	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request,
            final HttpServletResponse response, final Authentication authentication)
            throws IOException, ServletException {
        super.onAuthenticationSuccess(request, response, authentication);

        try {
			List<Discount> discounts =  discountDAO.getInformationDiscount();
			request.getSession().setAttribute(ParameterConstants.ALL_DISCOUNTS, discounts);			
		} catch (DAOSQLException | MyLogicalInvalidParameterException | ResourceCreationException ex){
			throw new RuntimeException("Authentication error occured", ex);
		} 
    }
	
}
