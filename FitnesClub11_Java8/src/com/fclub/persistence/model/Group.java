package com.fclub.persistence.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fclub.exception.MyLogicalInvalidParameterException;
/**
 * Business Object for Group 
 * @author Anastasiya Kisel
 */
@Entity
@Table(name="groups")
@NamedQueries({
	@NamedQuery (name="getGroupsBySporttype",
	        query="SELECT x FROM Group x WHERE x.sporttype.id = (?1)"),
	@NamedQuery (name="getGroupsByPersonId",
	        query="SELECT x FROM Group x WHERE x.sporttype.id = (?1)"),
	@NamedQuery (name="selectCapacityAndPeopleInTheGroup",
	        query="SELECT x.capacity, x.peopleRegistered FROM Group x WHERE x.id=(?1)"),
	@NamedQuery (name="selectGroupById",
	        query="SELECT gr FROM Group gr WHERE gr.id=(?1)"),
	@NamedQuery (name="getCostOfGroup", query="SELECT x.costAbonement FROM Group x WHERE x.id=(?1)")
})
public class Group  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "group_id")
	private int id;	
	
	//@Embedded
	@ManyToOne//(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="sporttype_id", referencedColumnName="sporttype_id")
	private Sporttype sporttype;

	@Column(name="capacity")
    private int capacity;
    
	@Column(name="day_of_week")
    private String daysOfWeek;
    
	@Column(name="start_time")
    private Time timeStart;
    
	@Column(name="duration")
    private int duration;   
    
	@Column(name="cost")
    private int costAbonement;

	@Column(name="people_registered")
    private int peopleRegistered;
	
	@Transient
	private List<String> selectedIds;
    /**
     * Gets id.
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
	 * Gets the number of registered people.
	 * @return the number of registered people
	 */
    public int getPeopleRegistered() {
        return peopleRegistered;
    }
	/**
	 * Sets the number of registered people.
	 * @param peopleRegistered - the number of registered people
	 * @throws MyLogicalInvalidParameterException
	 */	 
    public void setPeopleRegistered(int peopleRegistered) throws MyLogicalInvalidParameterException {
        if (peopleRegistered<0)
            throw new MyLogicalInvalidParameterException("People registered in group can't be less than null !");
        this.peopleRegistered = peopleRegistered;
    }
	/**
	 * Gets the capacity of the group.
	 * @return the capacity
	 */
    public int getCapacity() {
        return capacity;
    }
    /**
     * Sets the capacity of the group.
     * @param capacity - group's capacity
     * @throws MyLogicalInvalidParameterException
     */
    public void setCapacity(int capacity) throws MyLogicalInvalidParameterException {
        if (capacity<=0)
            throw new MyLogicalInvalidParameterException("Capacity can't be less than null !");
        this.capacity = capacity;
    }
    /**
     * Gets sport type.
     * @return sport type
     */
	public Sporttype getSporttype() {
		return sporttype;
	}
	/**
	 * Sets sport type.
	 * @param sporttype - sport type
	 */
	public void setSporttype(Sporttype sporttype) {
		this.sporttype = sporttype;
	}
	/**
	 * Gets the cost of abonement.
	 * @return the cost of abonement
	 */
	public int getCostAbonement() {
        return costAbonement;
    }
	/**
	 * Sets the cost of abonement.
	 * @param costAbonement- the cost of abonement
	 * @throws MyLogicalInvalidParameterException
	 */
    public void setCostAbonement(int costAbonement) throws MyLogicalInvalidParameterException {
        if (costAbonement<=0)
            throw new MyLogicalInvalidParameterException("Cost of abonement can't be less than null !");
        this.costAbonement = costAbonement;
    }
    /**
     * Gets days of week.
     * @return days of week
     */
    public String getDaysOfWeek() {
        return daysOfWeek;
    }
    /**
     * Sets days of week.
     * @param daysOfWeek - days of week
     */
    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
    /**
     * Gets duration (in hours).
     * @return duration (in hours)
     */
    public int getDuration() {
        return duration;
    }
    /**
     * Sets the duration in hours.
     * @param duration - duration in hours.
     * @throws MyLogicalInvalidParameterException
     */
    public void setDuration(int duration) throws MyLogicalInvalidParameterException {
        if (duration<=0)
            throw new MyLogicalInvalidParameterException("Duration can't be less than null !");
        this.duration = duration;
    }
    /**
     * Gets start time.
     * @return start time
     */
    public Time getTimeStart() {
        return timeStart;
    }
    /**
     * Sets start time.
     * @param timeStart - start time
     */
    public void setTimeStart(Time timeStart) {
        this.timeStart = timeStart;
    }
    /**
     * Gets selected ids.
     * @return selected ids.
     */
	public List<String> getSelectedIds() {
		return selectedIds;
	}
	/**
	 * Sets selected ids.
	 * @param selectedIds - selected ids
	 */
	public void setSelectedIds(List<String> selectedIds) {
		this.selectedIds = selectedIds;
	}
    /**
     * Overriden toString() method.
     */
    /*@Override
    public String toString (){
    	
        return super.toString()+" [capacity"+capacity+", "+
                               daysOfWeek.toString()+
                                ", Time: "+timeStart+
                                ", Duration  "+duration+
                                ", Cost of abonement  "+costAbonement                                               
                + "] ";
    }*/

}
