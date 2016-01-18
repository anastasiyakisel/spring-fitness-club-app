package by.bsu.kisel.entity;

import by.bsu.kisel.enums.Post;
import by.bsu.kisel.exception.MyLogicalInvalidParameterException;

/**
 *
 * @author Kisel Anastasia
 */
public class User extends AbstractEntity{
    private String firstName;
    private String lastName;
    private String address;
    private String telephone;
    private String description;
    private String login;
    private String password;
    private Post post;

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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) throws MyLogicalInvalidParameterException {
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
