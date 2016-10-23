package com.fclub.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fclub.exception.FClubInvalidParameterException;
import com.fclub.persistence.model.Statement;
/**
 * This interface describes the operations that DAO classes for Statement will implement.
 * @author Anastasiya Kisel
 *
 */
@Repository
public interface StatementJpaRepository extends JpaRepository<Statement, Long> {
	/**
	 * Indicates if user's statement exists in the database.
	 * @param personId - id of the person
	 * @return boolean flag which indicates if the statement for the user already exists in the database.
	 */
	Statement findByUserId(Long personId) ;


	/**
	 * Provides all statements.
	 * @return the list of all the statements 
	 * @throws FClubInvalidParameterException
	 */
	List<Statement> getAllUserStatements();

}
