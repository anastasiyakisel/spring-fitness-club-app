package com.fclub.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fclub.persistence.model.Registration;
/**
 * This interface describes the operations that DAO classes for Registration will implement.
 * @author Anastasiya Kisel
 *
 */
@Repository
public interface RegistrationJpaRepository extends JpaRepository<Registration, Long>{
	/**
	 * Deletes the user from specified groups.
	 * @param userId - user's id
	 * @param recIds - ids of the groups from which user should be deleted
	 * @return 
	 */
	@Modifying
	@Transactional
	@Query("DELETE FROM Registration x WHERE x.user.id=:userId AND x.group.id IN :recIds")
	void deleteUserFromGroups(@Param("userId") Long userId, @Param("recIds") List<Long> recIds);
	
	/**
	 * Deletes users from specified group.
	 * @param groupId - id of the group
	 * @param userIds - ids of users
	 * @return boolean flag which indicates if the deletion was successful
	 */
	@Modifying
	@Transactional
	@Query("DELETE FROM Registration x WHERE x.group.id=:groupId AND x.user.id IN :userIds")
	void deleteUsersFromGroup(@Param("groupId") Long groupId, @Param("userIds") List<Long> userIds);
	
	/**
	 * Provides the number of attendees of the specified group.
	 * @param groupNumber - number of the group
	 * @return number of attendees in the group
	 */
	@Query("SELECT COUNT(x.id) FROM Registration x WHERE x.group.id=:groupId")
	int countPeopleRegisteredInGroup(@Param("groupId") Long groupId) ;
	
	/**
	 * Calculates the number of user's abonements.
	 * @param personId - id of the user
	 * @return the number of user's abonements
	 */
	@Query("SELECT COUNT(x.id) FROM Registration x WHERE x.user.id=:personId")
	int countNumberOfAbonementsForUser(@Param("personId") Long personId) ;
	/**
	 * Provides the groups of the specified user.
	 * @param userId - unique id of the user
	 * @return list of user's groups
	 */
	List<Registration> findByUserId(Long userId);
	/**
	 * Provides the list of group's attendees.
	 * @param groupId - number of the group
	 * @return list of the group's attendees
	 */
	List<Registration> findByGroupId(Long groupId) ;

}
