package by.bsu.kisel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import by.bsu.kisel.exception.MyLogicalInvalidParameterException;
/**
 * Business Object for User. 
 * @author Anastasiya Kisel
 */
@Entity
@Table(name="person")
public class User  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id")
    private int id;
	
	@Column(name = "firstname")
    private String firstName;
    
	@Column(name = "lastname")
    private String lastName;
    
	@Column(name = "address")
    private String address;
    
	@Column(name = "telephone")
    private String telephone;
    
	@Column(name = "description")
    private String description;
    
	@Column(name = "login")
	@NotEmpty
	@Size(min = 1, max = 50)
    private String login;
    
	@NotEmpty
	@Size(min = 1, max = 20)
	@Column(name = "password")
    private String password;
    
	@Column(name="post", columnDefinition="enum('Admin','Client')")
    private String post;
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
    public void setAddress(String address) {
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
    public void setDescription(String description) {
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
     * @throws MyLogicalInvalidParameterException
     */
    public void setFirstName(String firstName) throws MyLogicalInvalidParameterException {
        if (firstName==null)
            throw new MyLogicalInvalidParameterException("FirstName can't be null !");
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
     * @throws MyLogicalInvalidParameterException
     */
    public void setLastName(String lastName) throws MyLogicalInvalidParameterException {
        if (lastName==null)
            throw new MyLogicalInvalidParameterException("LastName can't be null !");
        this.lastName = lastName;
    }
    /**
     * Gets the login.
     * @return login
     */
    public String getLogin() {
        return login;
    }
    /**
     * Sets the login.
     * @param login - login
     * @throws MyLogicalInvalidParameterException
     */
    public void setLogin(String login) throws MyLogicalInvalidParameterException {
       if (login==null)
            throw new MyLogicalInvalidParameterException("Login can't be null !");
       this.login = login;
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
     * @throws MyLogicalInvalidParameterException
     */
    public void setPassword(String password) throws MyLogicalInvalidParameterException {
        if (password==null)
            throw new MyLogicalInvalidParameterException("Password can't be null !");
        this.password = password;
    }
    /**
     * Gets the post.
     * @return the post
     */
    public String getPost() {
        return post;
    }
    /**
     * Sets the post.
     * @param post - post
     * @throws MyLogicalInvalidParameterException
     */
    public void setPost(String post) throws MyLogicalInvalidParameterException {
        if (post==null)
            throw new MyLogicalInvalidParameterException("Post can't be null !");
        this.post = post;
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
     * @throws MyLogicalInvalidParameterException
     */
    public void setTelephone(String telephone) throws MyLogicalInvalidParameterException {
        if (telephone==null)
            throw new MyLogicalInvalidParameterException("Telephone can't be null !");
        this.telephone = telephone;
    }
    /**
     * Gets the id.
     * @return id
     */
	public int getId() {
		return id;
	}
	/**
	 * Sets the id.
	 * @param id - id
	 */
	public void setId(int id) {
		this.id = id;
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
