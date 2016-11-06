package com.fclub.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fclub.persistence.model.User;
import com.fclub.persistence.repository.UserJpaRepository;

@Service
public class FitnessClubUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserJpaRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(login);
		if (user == null)
            throw new UsernameNotFoundException("User with username " + login	+ " is not found.");
		return user;
	}

}
