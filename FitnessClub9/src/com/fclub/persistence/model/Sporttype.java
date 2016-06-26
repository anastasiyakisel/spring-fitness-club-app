package com.fclub.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fclub.enums.SportType;
import com.fclub.exception.MyLogicalInvalidParameterException;
/**
 * Business Object for Sporttype. 
 * @author Anastasiya Kisel
 */
@Entity
@Table(name="sporttype")
@NamedQueries({
	@NamedQuery (name="allForConcreteSporttype",
	        query="SELECT OBJECT(x) FROM Sporttype x WHERE x.id = (?1)"),
})
@Embeddable
public class Sporttype  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sporttype_id")
    private int id;
	
	@Column(name = "sport_type", columnDefinition = "ENUM('EXERCISE_ROOM', 'AEROBICS', 'PILATES',  'YOGA', 'FITBALL',   'BELLY_DANCE', 'STRATCHING')")
	@Enumerated(EnumType.STRING)
    private SportType sportType;
    
	@Column(name = "calories_burned")
    private int caloriesburned;
	/**
	 * Gets the id.
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * Sets id.
	 * @param id - id
	 */
	public void setId(int id) {
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
     * @throws MyLogicalInvalidParameterException
     */
    public void setCaloriesburned(int caloriesburned) throws MyLogicalInvalidParameterException {
        if (caloriesburned<=0)
            throw new MyLogicalInvalidParameterException("Calories burned can't be null !");
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
	 * @throws MyLogicalInvalidParameterException
	 */
    public void setSportType(SportType sportType) throws MyLogicalInvalidParameterException {
        if (sportType==null){
        	throw new MyLogicalInvalidParameterException("Sporttype can't be null !");
        }
        this.sportType=sportType;
    } 
    
    
}
