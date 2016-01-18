/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.entity;

import by.bsu.kisel.exception.MyLogicalInvalidParameterException;

/**
 *
 * @author Anastasia Kisel
 */
public class Statement extends AbstractEntity{
    private int personId;
    private int numberOfAbonements;
    private int discountPercent;
    private double generalCost;

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
            throw new MyLogicalInvalidParameterException("Number of amonements can't be null !");
        this.numberOfAbonements = numberOfAbonements;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) throws MyLogicalInvalidParameterException {
        if (personId<=0)
            throw new MyLogicalInvalidParameterException("PersonID can't be less than null !");
        this.personId = personId;
    }
    
    
}
