/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
/**
 * Business Object for Discount 
 * @author Anastasiya Kisel
 */
@Entity
@Table(name="discount")
@NamedQueries({
	@NamedQuery (name="countDiscountForUser",
	        query="SELECT MAX(x.discountPercent) FROM Discount x WHERE x.numberOfAbonements <= (?)")	
})
public class Discount implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "discount_id")
	private int id;	
	
    @Column(name="number_of_abonements")
    private int numberOfAbonements;
    
    @Column(name="discount_percent")
    private int discountPercent;
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
     * Gets discount percent.
     * @return discount percent
     */
    public int getDiscountPercent() {
        return discountPercent;
    }
	/**
	 * Sets discount percent.
	 * @param discountPercent - discount percent
	 */
    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
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
     */
    public void setNumberOfAbonements(int numberOfAbonements) {
        this.numberOfAbonements = numberOfAbonements;
    }    
}
