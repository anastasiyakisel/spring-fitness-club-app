/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.entity;

/**
 *
 * @author Anastasia Kisel
 */
public class Discount extends AbstractEntity {
    private int numberOfAbonements;
    private int discountPercent;

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getNumberOfAbonements() {
        return numberOfAbonements;
    }

    public void setNumberOfAbonements(int numberOfAbonements) {
        this.numberOfAbonements = numberOfAbonements;
    }    
}
