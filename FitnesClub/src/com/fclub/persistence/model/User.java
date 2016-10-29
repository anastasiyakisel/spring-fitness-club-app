package com.fclub.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.fclub.annotation.PasswordMatches;
import com.fclub.constants.RoleConstants;
import com.fclub.exception.FClubInvalidParameterException;
/**
 * Business Object for User. 
 * @author Anastasiya Kisel
 */
@Entity
@Table(name="USERS")
@PasswordMatches
public class User  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	
	@Column(name = "firstname")
    @NotNull
    @NotEmpty
    private String firstName;
    
	@Column(name = "lastname")
    @NotNull
    @NotEmpty
    private String lastName;
    
	@Column(name = "address")
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[0-9]* [a-zA-Z]*$")
    private String address;
    
	@Column(name = "telephone")
    @NotNull
    @NotEmpty
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}")
    private String telephone;
    
	@Column(name = "description")
    private String description;
    
	@Column(name = "username")
    @NotNull
    @NotEmpty
    @Size(min = 4, max = 10, message="{Size.user.username}")
    private String username;
    
	@Column(name = "password")
    @NotNull
    @NotEmpty
    @Size(min = 4, max = 10, message="{Size.user.password}")
    private String password;
	
	@Transient
	@NotNull
	@NotEmpty
	@Size(min = 4, max = 20)
	private String matchingPassword;
	
	@Column(name="enabled", columnDefinition = "TINYINT default '1'")
	private boolean enabled=true;
	
	@Column(name="role", columnDefinition="enum('ROLE_ADMIN','ROLE_USER') default 'ROLE_USER'")
    private String role = RoleConstants.CLIENT;
    
	/**
	 * Gets the address.
	 * @return the address
	 */
    public String getAddress() {
        return address;
    }
    /**
     * Sets the address.
     * @param address - address
     */
    public void setAddress(final String address) {
        this.address = address;
    }
    /**
     * Gets the description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description.
     * @param description - description
     */
    public void setDescription(final String description) {
        this.description = description;
    }
    /**
     * Gets first name.
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Sets first name.
     * @param firstName - first name
     * @throws FClubInvalidParameterException
     */
    public void setFirstName(final String firstName) throws FClubInvalidParameterException {
        if (firstName==null)
            throw new FClubInvalidParameterException("FirstName can't be null !");
        this.firstName = firstName;
    }
    /**
     * Gets last name.
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Sets last name.
     * @param lastName - last name
     * @throws FClubInvalidParameterException
     */
    public void setLastName(final String lastName) throws FClubInvalidParameterException {
        if (lastName==null)
            throw new FClubInvalidParameterException("LastName can't be null !");
        this.lastName = lastName;
    }

    /**
     * Gets the password.
     * @return password
     */
    public String getPassword() {
        return password;
    }
    /**
     * Sets the password.
     * @param password - password
     * @throws FClubInvalidParameterException
     */
    public void setPassword(final String password) throws FClubInvalidParameterException {
        if (password==null)
            throw new FClubInvalidParameterException("Password can't be null !");
        this.password = password;
    }
    /**
     * Gets the matching password.
     * @return password
     */
    public String getMatchingPassword() {
        return matchingPassword;
    }
    /**
     * Sets the matching password.
     * @param matchingPassword - matching password
     * @throws FClubInvalidParameterException
     */
    public void setMatchingPassword(final String matchingPassword) throws FClubInvalidParameterException {
        if (matchingPassword==null)
            throw new FClubInvalidParameterException("Password can't be null !");
        this.matchingPassword = matchingPassword;
    }
    /**
     * Gets the post.
     * @return the post
     */
    public String getPost() {
        return role;
    }
    /**
     * Sets the post.
     * @param post - post
     * @throws FClubInvalidParameterException
     */
    public void setPost(final String post) throws FClubInvalidParameterException {
        if (post==null)
            throw new FClubInvalidParameterException("Post can't be null !");
        this.role = post;
    }
    /**
     * Gets the telephone number.
     * @return the telephone number
     */
    public String getTelephone() {
        return telephone;
    }
    /**
     * Sets the specified telephone number.
     * @param telephone - telephone number
     * @throws FClubInvalidParameterException
     */
    public void setTelephone(final String telephone) throws FClubInvalidParameterException {
        if (telephone==null)
            throw new FClubInvalidParameterException("Telephone can't be null !");
        this.telephone = telephone;
    }
    /**
     * Gets the id.
     * @return id
     */
	public Long getId() {
		return id;
	}
	/**
	 * Sets the id.
	 * @param id - id
	 */
	public void setId(final Long id) {
		this.id = id;
	}
	
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
    
    public String getUsername() {
		return username;
	}
	public void setUsername(final String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(final String role) {
		this.role = role;
	}
	/**
     * Overriden toString() method.	
     */
    @Override
    public String toString(){
        return super.toString()+"[ First name:"+ firstName+
                                ", Last name: "+lastName+
                                ", Address:"+address+
                                ", Telephone"+telephone+
                                ", Description"+description+
                                "]";
    }
}
