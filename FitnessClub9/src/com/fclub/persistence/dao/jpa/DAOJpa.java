package com.fclub.persistence.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
/**
 * Parent of all DAO JPA classes in the application.
 * @author Anastasiya Kisel
 *
 */
@Component
public class DAOJpa {

	@PersistenceContext//(type = PersistenceContextType.EXTENDED)
	protected EntityManager em; 	

}
