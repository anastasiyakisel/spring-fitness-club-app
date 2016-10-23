package com.fclub.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fclub.persistence.model.Group;
/**
 * This interface describes the operations that DAO classes for Group will implement.
 * @author Anastasiya Kisel
 *
 */
@Repository
public interface GroupJpaRepository extends JpaRepository<Group, Long>{
	/**
	 * Provides the groups of the concrete sport type.
	 * @param sporttypeId - id of the sport type
	 * @return the list of the groups for the specified sport type
	 */
	List<Group> findBySporttype(Long sporttypeId) ;

	/**
	 * Searches the groups with ids specified.
	 * @param ids - set of unique ids of random groups
	 * @return list of groups
	 */
	List<Group> findByIdIn(List<Integer> ids) ;
	
	Group findById(Long id);
	
}
