package com.fclub.spring.security.custom.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.fclub.constants.ParameterConstants;
import com.fclub.persistence.model.Discount;
import com.fclub.persistence.repository.DiscountJpaRepository;

public class FitnesClubSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler  {
	
	@Autowired
	private DiscountJpaRepository discountDAO;
	
	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request,
            final HttpServletResponse response, final Authentication authentication)
            throws IOException, ServletException {
		super.onAuthenticationSuccess(request, response, authentication);

		final List<Discount> discounts = discountDAO.findAll();
		request.getSession().setAttribute(ParameterConstants.ALL_DISCOUNTS, discounts);		
    }
	
}
