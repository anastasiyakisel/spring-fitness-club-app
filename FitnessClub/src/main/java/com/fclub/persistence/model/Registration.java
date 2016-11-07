package com.fclub.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * Business Object for Registration. 
 * @author Anastasiya Kisel
 */
@Entity
@Table(name="registration")
public class Registration  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "registration_id")
    private Long id;
	
	@ManyToOne
    @JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
    @JoinColumn(name="group_id")
	private Group group;
	
	public Registration(){}
	
	public Registration (User user, Group group){
		this.user=user;
		this.group=group;
	}
	
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
	 * Gets the user.
	 * @return user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * Sets the user.
	 * @param user - user
	 */
	public void setUser(final User user) {
		this.user = user;
	}
	/**
	 * Gets the group.
	 * @return group
	 */
	public Group getGroup() {
		return group;
	}
	/**
	 * Sets the group.
	 * @param group - group
	 */
	public void setGroup(final Group group) {
		this.group = group;
	}
		
}
