package com.fclub.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
/**
 * Business Object for Registration. 
 * @author Anastasiya Kisel
 */
@Entity
@Table(name="registration")
@NamedQueries({
	@NamedQuery (name="deleteFromGroupInRegistration",
	        query="DELETE FROM Registration x WHERE x.user.id=(?1) AND x.group.id=(?1)"),
	@NamedQuery (name="countClientsInGroup",
	        query="SELECT COUNT(x.id) FROM Registration x WHERE x.group.id=(?1)"),
	@NamedQuery (name="countNumberOfAbonementsForUser",
	        query="SELECT COUNT(x.id) FROM Registration x WHERE x.user.id=(?1)"),
	@NamedQuery (name="selectGroupsFromRegistrationByUserId",
	        query="SELECT x.group FROM Registration x WHERE x.user.id=(?1)"),
	@NamedQuery (name="selectGroupsFromRegistrationByGroup",
	        query="SELECT x.group FROM Registration x WHERE x.group.id=(?1)"), 
	@NamedQuery (name="selectUsersFromGroup", query="SELECT x.user FROM Registration x WHERE x.group.id=?")
})
public class Registration  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "registration_id")
    private int id;
	
	@ManyToOne
    @JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
    @JoinColumn(name="group_id")
	private Group group;
	/**
	 * Gets id.
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * Sets id.
	 * @param id - id
	 */
	public void setId(int id) {
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
	public void setUser(User user) {
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
	public void setGroup(Group group) {
		this.group = group;
	}
		
}
