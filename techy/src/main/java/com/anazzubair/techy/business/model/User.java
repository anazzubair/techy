package com.anazzubair.techy.business.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User implements Serializable {

	private static final long serialVersionUID = 8557283676339374475L;

	@Id
	@Column(name = "Id", columnDefinition = "serial", updatable = false)
	@SequenceGenerator(name = "UserIdSequenceGenerator", sequenceName = "User_Id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserIdSequenceGenerator")
	private Long id;
	
	@Column(name = "Username")
	private String username;
	
	@Column(name = "FirstName")
	private String firstName;
	
	@Column(name = "MiddleName")
	private String middleName;
	
	@Column(name = "LastName")
	private String lastName;
	
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	protected User() {}
	
	public User(String username){
		this.username = username;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
