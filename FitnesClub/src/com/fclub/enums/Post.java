package com.fclub.enums;

/**
 * Enum for user roles in application.
 * @author Kisel Anastasia
 */
public enum Post {
	
    ADMIN("ROLE_ADMIN"), 
    CLIENT("ROLE_USER");
    
    private String value;
    /**
     * Constructs user role object with the specified string value.
     * @param value - user role name
     */
    private Post(String value) {
        this.value = value;
    }
	/**
	 * Gets the value of the user role
	 * @return text representation of the user role
	 */
    public String getValue() {
        return value;
    }
    /**
     * Casts user role object to String.
     */
    public String toString(){
    	return value;
    }

}
