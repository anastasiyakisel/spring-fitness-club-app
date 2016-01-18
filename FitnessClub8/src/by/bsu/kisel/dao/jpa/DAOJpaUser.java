package by.bsu.kisel.dao.jpa;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import by.bsu.kisel.dao.IDAOUser;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;
import by.bsu.kisel.model.User;
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
		String queryString = "SELECT a FROM User a WHERE a.login = :login AND a.password = :password";
		Query query = em.createQuery(queryString);
		query.setParameter("login", login);
		query.setParameter("password", password);
		if (query.getResultList().size()==0){
			return null;
		}
		return (User)query.getResultList().get(0);		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public User create(User user)  {
		em.persist(user);
		return user;
	}


}
