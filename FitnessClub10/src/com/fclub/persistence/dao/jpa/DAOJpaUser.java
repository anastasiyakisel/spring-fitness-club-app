package com.fclub.persistence.dao.jpa;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fclub.exception.AccountExistsException;
import com.fclub.exception.DAOSQLException;
import com.fclub.exception.MyLogicalInvalidParameterException;
import com.fclub.exception.ResourceCreationException;
import com.fclub.persistence.dao.IDAOUser;
import com.fclub.persistence.model.User;
/**
 * This class implements DAO User logic with the help of JPA.
 * @author Anastasiya Kisel
 */
@Repository("DAOJpaUser")
@Transactional
public class DAOJpaUser extends DAOJpa implements IDAOUser {
	/**
	 * Checks if the user with specified login and password exists in the database.
	 * @param login - login 
	 * @param password - password
	 * @return User object if login was successful or null if login failed
	 * @throws DAOSQLException
	 * @throws MyLogicalInvalidParameterException
	 * @throws ResourceCreationException
	 */
	@Override
	public User checkLogin(String login, String password)
			throws DAOSQLException, MyLogicalInvalidParameterException,
			ResourceCreationException {
		String queryString = "SELECT a FROM User a WHERE a.username = :login AND a.password = :password";
		Query query = em.createQuery(queryString);
		query.setParameter("login", login);
		query.setParameter("password", password);
		if (query.getResultList().size()==0){
			return null;
		}
		return (User)query.getResultList().get(0);		
	}


	@Override
	public User findByUsername(String login) throws ResourceCreationException,
			DAOSQLException, MyLogicalInvalidParameterException {
		String queryString = "SELECT a FROM User a WHERE a.username = :login";
		Query query = em.createQuery(queryString);
		query.setParameter("login", login);
		return (query.getResultList().size()==0) ? null : (User) query.getResultList().get(0);	
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public User registerNewUserAccount(User user) throws AccountExistsException, ResourceCreationException, DAOSQLException, MyLogicalInvalidParameterException {
		if (findByUsername(user.getUsername()) != null) {
			throw new AccountExistsException("User account with login name "+user.getUsername()+" already exists in the system. ");
		}
		em.persist(user);
		return user;
	}


	@Override
	public User findById(Integer id) {
		String queryString = "SELECT a FROM User a WHERE a.id = :id";
		Query query = em.createQuery(queryString);
		query.setParameter("id", id);
		return (query.getResultList().size()==0) ? null : (User) query.getResultList().get(0);	
	}
	
	
}
