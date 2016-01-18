package by.bsu.kisel.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="registration")
@NamedQueries({
	@NamedQuery (name="deleteFromGroupInRegistration",
	        query="DELETE FROM Registration x WHERE x.user.id=(?) AND x.group.id=(?)"),
	@NamedQuery (name="countClientsInGroup",
	        query="SELECT COUNT(x.id) FROM Registration x WHERE x.group.id=(?)"),
	@NamedQuery (name="countNumberOfAbonementsForUser",
	        query="SELECT COUNT(x.id) FROM Registration x WHERE x.user.id=(?)"),
	@NamedQuery (name="selectGroupsFromRegistrationByUserId",
	        query="SELECT x.group FROM Registration x WHERE x.user.id=(?)"),
	@NamedQuery (name="selectGroupsFromRegistrationByGroup",
	        query="SELECT x.group FROM Registration x WHERE x.group.id=(?)"), 
	@NamedQuery (name="selectUsersFromGroup", query="SELECT x.user FROM Registration x WHERE x.group.id=?")
})
public class Registration  implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "registration_id")
    private int id;
	
	@ManyToOne
    @JoinColumn(name="person_id")
	private User user;
	
	@ManyToOne
    @JoinColumn(name="group_id")
	private Group group;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
		
}
