package com.fclub.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fclub.enums.SportType;
import com.fclub.exception.FClubInvalidParameterException;
/**
 * Business Object for Sporttype. 
 * @author Anastasiya Kisel
 */
@Entity
@Table(name="SPORTTYPE")
@Embeddable
public class Sporttype  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "sporttype_id")
    private Long id;
	
	@Column(name = "sport_type", columnDefinition = "ENUM('EXERCISE_ROOM', 'AEROBICS', 'PILATES',  'YOGA', 'FITBALL',   'BELLY_DANCE', 'STRATCHING')")
	@Enumerated(EnumType.STRING)
    private SportType sportType;
    
	@Column(name = "calories_burned")
    private int caloriesburned;
	/**
	 * Gets the id.
	 * @return id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Sets id.
	 * @param id - id
	 */
	public void setId(final Long id) {
		this.id = id;
	}
	/**
	 * Gets burned calories.
	 * @return burned calories
	 */
    public int getCaloriesburned() {
        return caloriesburned;
    }
    /**
     * Sets burned calories.
     * @param caloriesburned - burned calories
     * @throws FClubInvalidParameterException
     */
    public void setCaloriesburned(final int caloriesburned) throws FClubInvalidParameterException {
        if (caloriesburned<=0)
            throw new FClubInvalidParameterException("Calories burned can't be null !");
        this.caloriesburned = caloriesburned;
    }
    /**
     * Gets sport type.
     * @return sport type
     */
    public SportType getSportType() {
    	return this.sportType;
    }
	/**
	 * Sets sport type.
	 * @param sportType - sport type
	 * @throws FClubInvalidParameterException
	 */
    public void setSportType(final SportType sportType) throws FClubInvalidParameterException {
        if (sportType==null){
        	throw new FClubInvalidParameterException("Sporttype can't be null !");
        }
        this.sportType=sportType;
    } 
    
    
}
