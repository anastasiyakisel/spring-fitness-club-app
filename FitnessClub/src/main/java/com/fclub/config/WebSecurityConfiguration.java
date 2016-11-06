package com.fclub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.fclub.constants.URLConstants;
import com.fclub.spring.security.FitnessClubUserDetailsService;
import com.fclub.spring.security.handler.FitnesClubSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private FitnessClubUserDetailsService userDetailsService;
	
	@Autowired
	private FitnesClubSuccessHandler successHandler;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.userDetailsService(userDetailsService)
			.authorizeRequests()
			.antMatchers("/user/**", "/generic/**").authenticated()
        	.antMatchers("/admin/**").hasRole("ADMIN")
        .and()
        .formLogin()
        	.loginPage("/login.html")
        	.loginProcessingUrl("/j_spring_security_check")
        	.usernameParameter("j_username").passwordParameter("j_password")
        	.successHandler(successHandler)
            .failureUrl(URLConstants.REGISTRATION)
        	.permitAll()
        .and()
        .logout().logoutUrl("/logout").logoutSuccessUrl("/login.html").deleteCookies("JSESSIONID")
            .permitAll()
        .and().csrf().csrfTokenRepository(csrfTokenRepository());
    }
	
	private CsrfTokenRepository csrfTokenRepository() 
	{ 
	    HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository(); 
	    repository.setSessionAttributeName("_csrf");
	    return repository; 
	}
	
}
