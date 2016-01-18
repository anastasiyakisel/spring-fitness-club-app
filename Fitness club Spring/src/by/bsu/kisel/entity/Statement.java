/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import by.bsu.kisel.exception.MyLogicalInvalidParameterException;

/**
 *
 * @author Anastasia Kisel
 */
@Entity
@Table(name="STATEMENT")
@NamedQueries({
	@NamedQuery (name="isUserExistsInStatement",
			query="SELECT COUNT(x.id) FROM Statement x WHERE x.user.id=?"),
	@NamedQuery (name="selectInfoAboutStatement",
	        query="SELECT st FROM Statement st WHERE st.user.id=(?)"),
	@NamedQuery (name="selectConcreteStatement",
	        query="SELECT st FROM Statement st WHERE st.id=(?)")       
})
public class Statement implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "statement_id")
    private int id;
	
	@OneToOne(optional=false)
    @JoinColumn(name = "person_id") 
    private User user;
    
	@Column(name = "number_of_abonements")
    private int numberOfAbonements;
    
	@Column(name = "discount_percent")
    private int discountPercent;
    
	@Column(name = "general_cost")
    private double generalCost;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) throws MyLogicalInvalidParameterException {
        if (discountPercent<0)
            throw new MyLogicalInvalidParameterException("DiscontID can't be null !");
        this.discountPercent = discountPercent;
    }

    public double getGeneralCost() {
        return generalCost;
    }

    public void setGeneralCost(double generalCost) throws MyLogicalInvalidParameterException {
        if (generalCost<0)
            throw new MyLogicalInvalidParameterException("General cost can't be null !");
        this.generalCost = generalCost;
    }

    public int getNumberOfAbonements() {
        return numberOfAbonements;
    }

    public void setNumberOfAbonements(int numberOfAbonements) throws MyLogicalInvalidParameterException {
        if (numberOfAbonements<0)
            throw new MyLogicalInvalidParameterException("Number of abonements can't be null !");
        this.numberOfAbonements = numberOfAbonements;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}  
    
}
