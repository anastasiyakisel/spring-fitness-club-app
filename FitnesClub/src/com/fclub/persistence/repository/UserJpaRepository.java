package com.fclub.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fclub.persistence.model.User;
/**
 * This interface describes the operations that DAO classes for User will implement.
 * @author Anastasiya Kisel
 *
 */
@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>{

	User findByUsername (String login) ;

	User findById (Long id);
}
