package by.bsu.kisel.entity;

import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
import java.sql.Time;

/**
 *
 * @author Kisel Anastasia
 */
public class Group extends AbstractEntity{
    private int sporttypeID;
    private int capacity;
    private String daysOfWeek;
    private Time timeStart;
    private int duration;   //in hours
    private int costAbonement;
    private int peopleRegistered;

    public int getPeopleRegistered() {
        return peopleRegistered;
    }

    public void setPeopleRegistered(int peopleRegistered) throws MyLogicalInvalidParameterException {
        if (peopleRegistered<0)
            throw new MyLogicalInvalidParameterException("People registered in group can't be less than null !");
        this.peopleRegistered = peopleRegistered;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) throws MyLogicalInvalidParameterException {
        if (capacity<=0)
            throw new MyLogicalInvalidParameterException("Capacity can't be less than null !");
        this.capacity = capacity;
    }

    public int getCostAbonement() {
        return costAbonement;
    }

    public void setCostAbonement(int costAbonement) throws MyLogicalInvalidParameterException {
        if (costAbonement<=0)
            throw new MyLogicalInvalidParameterException("Cost of abonement can't be less than null !");
        this.costAbonement = costAbonement;
    }

    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) throws MyLogicalInvalidParameterException {
        if (duration<=0)
            throw new MyLogicalInvalidParameterException("Duration can't be less than null !");
        this.duration = duration;
    }

    public int getSporttypeID() {
        return sporttypeID;
    }

    public void setSporttypeID(int sporttypeID) throws MyLogicalInvalidParameterException {
        if (sporttypeID<=0)
            throw new MyLogicalInvalidParameterException("SporttypeID can't be less than null !");
        this.sporttypeID = sporttypeID;
    }

    public Time getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Time timeStart) {
        this.timeStart = timeStart;
    }
    
    @Override
    public String toString (){
        return super.toString()+" [capacity"+capacity+", "+
                               daysOfWeek.toString()+
                                ", Time: "+timeStart+
                                ", Duration  "+duration+
                                ", Cost of abonement  "+costAbonement                                               
                + "] ";
    }
}
