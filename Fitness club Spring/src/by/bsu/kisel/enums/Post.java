/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.enums;

/**
 *
 * @author Kisel Anastasia
 */
public enum Post {
	
    ADMIN("Admin"), 
    CLIENT("Client");
    
    private String value;
    
    private Post(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public String toString(){
    	return value;
    }

}
