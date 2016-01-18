package by.bsu.kisel.entity;

import java.io.Serializable;

import by.bsu.kisel.enums.Post;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Enumerated;


/**
 *
 * @author Kisel Anastasia
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
    private String login;
    
	@Column(name = "password")
    private String password;
    
	@Column(name="post", columnDefinition="enum('Admin','Client')")
    private String post;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws MyLogicalInvalidParameterException {
        if (firstName==null)
            throw new MyLogicalInvalidParameterException("FirstName can't be null !");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws MyLogicalInvalidParameterException {
        if (lastName==null)
            throw new MyLogicalInvalidParameterException("LastName can't be null !");
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws MyLogicalInvalidParameterException {
       if (login==null)
            throw new MyLogicalInvalidParameterException("Login can't be null !");
       this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws MyLogicalInvalidParameterException {
        if (password==null)
            throw new MyLogicalInvalidParameterException("Password can't be null !");
        this.password = password;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) throws MyLogicalInvalidParameterException {
        if (post==null)
            throw new MyLogicalInvalidParameterException("Post can't be null !");
        this.post = post;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) throws MyLogicalInvalidParameterException {
        if (telephone==null)
            throw new MyLogicalInvalidParameterException("Telephone can't be null !");
        this.telephone = telephone;
    }
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    @Override
    public String toString(){
        return super.toString()+"[ firstName:"+ firstName+
                                ", lastName: "+lastName+
                                ", Address:"+address+
                                ", Telephone"+telephone+
                                ", Description"+description+
                                "]";
    }
}
