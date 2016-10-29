package com.fclub.persistence.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fclub.exception.FClubInvalidParameterException;
/**
 * Business Object for Group 
 * @author Anastasiya Kisel
 */
@Entity
@Table(name="groups")
public class Group  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "group_id")
	private Long id;	
	
	@ManyToOne
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
	private List<String> selectedIds ;
    /**
     * Gets id.
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
	 * Gets the number of registered people.
	 * @return the number of registered people
	 */
    public int getPeopleRegistered() {
        return peopleRegistered;
    }
	/**
	 * Sets the number of registered people.
	 * @param peopleRegistered - the number of registered people
	 * @throws FClubInvalidParameterException
	 */	 
    public void setPeopleRegistered(final int peopleRegistered) throws FClubInvalidParameterException {
        if (peopleRegistered<0)
            throw new FClubInvalidParameterException("People registered in group can't be less than null !");
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
     * @throws FClubInvalidParameterException
     */
    public void setCapacity(final int capacity) throws FClubInvalidParameterException {
        if (capacity<=0)
            throw new FClubInvalidParameterException("Capacity can't be less than null !");
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
	public void setSporttype(final Sporttype sporttype) {
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
	 * @throws FClubInvalidParameterException
	 */
    public void setCostAbonement(final int costAbonement) throws FClubInvalidParameterException {
        if (costAbonement<=0)
            throw new FClubInvalidParameterException("Cost of abonement can't be less than null !");
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
    public void setDaysOfWeek(final String daysOfWeek) {
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
     * @throws FClubInvalidParameterException
     */
    public void setDuration(final int duration) throws FClubInvalidParameterException {
        if (duration<=0)
            throw new FClubInvalidParameterException("Duration can't be less than null !");
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
    public void setTimeStart(final Time timeStart) {
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
	public void setSelectedIds(final List<String> selectedIds) {
		this.selectedIds = selectedIds;
	}
	
	@PostLoad
	public void calculateSelectedIds(){
		this.selectedIds = new ArrayList<String>();
	}

}
