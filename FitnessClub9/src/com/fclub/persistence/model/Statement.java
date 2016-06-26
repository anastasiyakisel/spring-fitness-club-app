package com.fclub.persistence.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fclub.exception.MyLogicalInvalidParameterException;
/**
 * Business Object for Statement. 
 * @author Anastasiya Kisel
 */
@Entity
@Table(name="STATEMENT")
@NamedQueries({
	@NamedQuery (name="isUserExistsInStatement",
			query="SELECT COUNT(x.id) FROM Statement x WHERE x.user.id=(?1)"),
	@NamedQuery (name="selectInfoAboutStatement",
	        query="SELECT st FROM Statement st WHERE st.user.id=(?1)"),
	@NamedQuery (name="selectConcreteStatement",
	        query="SELECT st FROM Statement st WHERE st.id=(?1)")       
})
public class Statement implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "statement_id")
    private int id;
	
	@OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id") 
    private User user;
    
	@Column(name = "number_of_abonements")
    private int numberOfAbonements;
    
	@Column(name = "discount_percent")
    private int discountPercent;
    
	@Column(name = "general_cost")
    private double generalCost;
		
	@Transient
	private List<String> selectedIds;
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
	 * Gets discount percent.
	 * @return discount percent
	 */
    public int getDiscountPercent() {
        return discountPercent;
    }
    /**
     * Sets discount percent.
     * @param discountPercent - discount percent 
     * @throws MyLogicalInvalidParameterException
     */
    public void setDiscountPercent(int discountPercent) throws MyLogicalInvalidParameterException {
        if (discountPercent<0)
            throw new MyLogicalInvalidParameterException("DiscontID can't be null !");
        this.discountPercent = discountPercent;
    }
    /**
     * Gets general cost.
     * @return general cost
     */
    public double getGeneralCost() {
        return generalCost;
    }
    /**
     * Sets general cost.
     * @param generalCost - general cost
     * @throws MyLogicalInvalidParameterException
     */
    public void setGeneralCost(double generalCost) throws MyLogicalInvalidParameterException {
        if (generalCost<0)
            throw new MyLogicalInvalidParameterException("General cost can't be null !");
        this.generalCost = generalCost;
    }
    /**
     * Gets the number of abonements.
     * @return the number of abonements
     */
    public int getNumberOfAbonements() {
        return numberOfAbonements;
    }
    /**
     * Sets the number of abonements.
     * @param numberOfAbonements - the number of abonements
     * @throws MyLogicalInvalidParameterException
     */
    public void setNumberOfAbonements(int numberOfAbonements) throws MyLogicalInvalidParameterException {
        if (numberOfAbonements<0)
            throw new MyLogicalInvalidParameterException("Number of abonements can't be null !");
        this.numberOfAbonements = numberOfAbonements;
    }
    /**
     * Gets the user.
     * @return user
     */
	public User getUser() {
		return user;
	}
	/**
	 * Sets the user.
	 * @param user - user
	 */
	public void setUser(User user) {
		this.user = user;
	}  
	/**
	 * Gets selected ids.    
	 * @return selected ids
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
    
}
