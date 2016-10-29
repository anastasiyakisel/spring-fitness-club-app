package com.fclub.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fclub.persistence.model.Discount;
/**
 * This interface describes the operations that DAO classes for Discount will implement.
 * @author Anastasiya Kisel
 *
 */
@Repository
public interface DiscountJpaRepository extends JpaRepository<Discount, Long>{

	/**
	 * Calculates the discount percent for the user based on the number of abonements this user owns.
	 * @param numberOfAbonements - number of the abonements the user owns
	 * @return the discount percent for the user
	 */
	@Query("SELECT MAX(x.discountPercent) FROM Discount x WHERE x.numberOfAbonements <= :numberOfAbonements")
	int countDiscountPercentForUser(@Param("numberOfAbonements") int numberOfAbonements);
	
}
