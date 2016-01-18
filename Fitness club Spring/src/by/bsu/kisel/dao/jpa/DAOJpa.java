package by.bsu.kisel.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.stereotype.Component;

@Component
public class DAOJpa {
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	protected EntityManager em; // inject Entity Manager
}
