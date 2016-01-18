package by.bsu.kisel.dao.jpa;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.bsu.kisel.dao.IDAOUser;
import by.bsu.kisel.entity.Group;
import by.bsu.kisel.entity.Registration;
import by.bsu.kisel.entity.User;
import by.bsu.kisel.exception.DAOSQLException;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import by.bsu.kisel.exception.ResourceCreationException;

@Repository("DAOJpaUser")
public class DAOJpaUser extends DAOJpa implements IDAOUser {

	@Override
	public User checkLogin(String login, String password)
			throws DAOSQLException, MyLogicalInvalidParameterException,
			ResourceCreationException {
		String queryString = "SELECT a FROM User a WHERE a.login = :login AND a.password = :password";
		Query query = em.createQuery(queryString);
		query.setParameter("login", login);
		query.setParameter("password", password);
		return (User)query.getResultList().get(0);		
	}


}
