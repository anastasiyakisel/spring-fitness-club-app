package by.bsu.kisel.entity;

import by.bsu.kisel.exception.MyLogicalInvalidParameterException;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

/**
 *
 * @author Kisel Anastasia
 */	
@Entity
@Table(name="groups")
@NamedQueries({
	@NamedQuery (name="getGroupsBySporttype",
	        query="SELECT x FROM Group x WHERE x.sporttype.id = (?)"),
	@NamedQuery (name="getGroupsByPersonId",
	        query="SELECT x FROM Group x WHERE x.sporttype.id = (?)"),
	@NamedQuery (name="selectCapacityAndPeopleInTheGroup",
	        query="SELECT x.capacity, x.peopleRegistered FROM Group x WHERE x.id=?"),
	@NamedQuery (name="selectGroupById",
	        query="SELECT gr FROM Group gr WHERE gr.id=(?)"),
	@NamedQuery (name="getCostOfGroup", query="SELECT x.costAbonement FROM Group x WHERE x.id=(?)")
})
public class Group  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_id")
	private int id;	
	
	@ManyToOne//optional=false(fetch=FetchType.LAZY)
    @JoinColumn(name="sporttype_id")//,referencedColumnName="sporttype_id"
	private Sporttype sporttype;

	@Column(name="capacity")
    private int capacity;
    
	@Column(name="day_of_week")
    private String daysOfWeek;
    
	@Column(name="start_time")
    private Time timeStart;
    
	@Column(name="duration")
    private int duration;   //in hours
    
	@Column(name="cost")
    private int costAbonement;

	@Column(name="people_registered")
    private int peopleRegistered;
 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
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

	public Sporttype getSporttype() {
		return sporttype;
	}

	public void setSporttype(Sporttype sporttype) {
		this.sporttype = sporttype;
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
