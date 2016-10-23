/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclub.persistence.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Business Object for Discount 
 * @author Anastasiya Kisel
 */
@Entity
@Table(name="discount")
public class Discount implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "discount_id")
	private Long id;	
	
    @Column(name="number_of_abonements")
    private int numberOfAbonements;
    
    @Column(name="discount_percent")
    private int discountPercent;
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
    public void setDiscountPercent(final int discountPercent) {
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
    public void setNumberOfAbonements(final int numberOfAbonements) {
        this.numberOfAbonements = numberOfAbonements;
    }    
}
