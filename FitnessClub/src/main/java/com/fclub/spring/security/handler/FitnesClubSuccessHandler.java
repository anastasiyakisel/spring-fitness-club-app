package com.fclub.spring.security.handler;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fclub.constants.ParameterConstants;
import com.fclub.constants.RoleConstants;
import com.fclub.constants.URLConstants;
import com.fclub.persistence.model.Discount;
import com.fclub.persistence.repository.DiscountJpaRepository;

@Component
public class FitnesClubSuccessHandler implements AuthenticationSuccessHandler  {
	
	@Autowired
	private DiscountJpaRepository discountDAO;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request,
            final HttpServletResponse response, final Authentication authentication)
            throws IOException, ServletException {
		final List<Discount> discounts = discountDAO.findAll();
		request.getSession().setAttribute(ParameterConstants.ALL_DISCOUNTS, discounts);
		String targetUrl = determineTargetUrl(authentication);
		redirectStrategy.sendRedirect(request, response, targetUrl);		
    }
	
	protected String determineTargetUrl(Authentication authentication) {
		boolean isUser = false;
        boolean isAdmin = false;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals(RoleConstants.CLIENT)) {
            	isUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals(RoleConstants.ADMIN)) {
            	isAdmin = true;
                break;
            }
        }
        return (isAdmin == true) ?  URLConstants.ADMIN_HOME : URLConstants.USER_HOME;
	 }

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
	
}
