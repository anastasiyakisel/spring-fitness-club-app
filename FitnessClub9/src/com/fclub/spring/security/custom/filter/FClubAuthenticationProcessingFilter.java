package com.fclub.spring.security.custom.filter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fclub.constants.ParameterConstants;
import com.fclub.exception.DAOSQLException;
import com.fclub.exception.MyLogicalInvalidParameterException;
import com.fclub.exception.ResourceCreationException;
import com.fclub.persistence.dao.IDAODiscount;
import com.fclub.persistence.model.Discount;

public class FClubAuthenticationProcessingFilter extends UsernamePasswordAuthenticationFilter{
	
	@Autowired
	@Qualifier("DAOJpaDiscount")
	private IDAODiscount discountDAO;
	
	@Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		try {
			List<Discount> discounts =  discountDAO.getInformationDiscount();
			request.getSession().setAttribute(ParameterConstants.ALL_DISCOUNTS, discounts);
			return super.attemptAuthentication(request, response); 
		} catch (DAOSQLException | MyLogicalInvalidParameterException | ResourceCreationException ex){
			throw new RuntimeException("Authentication error occured", ex);
		} 
		
	}
	
}
