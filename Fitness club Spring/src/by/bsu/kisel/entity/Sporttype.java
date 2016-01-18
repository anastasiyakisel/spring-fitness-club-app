package by.bsu.kisel.entity;

import java.io.Serializable;

import by.bsu.kisel.enums.SportType;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Anastasia Kisel
 */
@Entity
@Table(name="sporttype")
@NamedQueries({
	@NamedQuery (name="allForConcreteSporttype",
	        query="SELECT OBJECT(x) FROM Sporttype x WHERE x.id = ?"),
})
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
    public int getCaloriesburned() {
        return caloriesburned;
    }

    public void setCaloriesburned(int caloriesburned) throws MyLogicalInvalidParameterException {
        if (caloriesburned<=0)
            throw new MyLogicalInvalidParameterException("Calories burned can't be null !");
        this.caloriesburned = caloriesburned;
    }

    public SportType getSportType() {
    	return this.sportType;
    }

    public void setSportType(SportType sportType) throws MyLogicalInvalidParameterException {
        if (sportType==null){
        	throw new MyLogicalInvalidParameterException("Sporttype can't be null !");
        }
        this.sportType=sportType;
    } 
    
    
}
