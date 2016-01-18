package by.bsu.kisel.entity;

import by.bsu.kisel.enums.SportType;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;

/**
 *
 * @author Anastasia Kisel
 */
public class Sporttype extends AbstractEntity{
    private SportType sportType;
    private int caloriesburned;

    public int getCaloriesburned() {
        return caloriesburned;
    }

    public void setCaloriesburned(int caloriesburned) throws MyLogicalInvalidParameterException {
        if (caloriesburned<=0)
            throw new MyLogicalInvalidParameterException("Calories burned can't be null !");
        this.caloriesburned = caloriesburned;
    }

    public SportType getSportType() {
        return sportType;
    }

    public void setSportType(SportType sportType) throws MyLogicalInvalidParameterException {
        if (sportType==null){
            throw new MyLogicalInvalidParameterException("Sporttype can't be null !");
        }
        this.sportType = sportType;
    }    
}
