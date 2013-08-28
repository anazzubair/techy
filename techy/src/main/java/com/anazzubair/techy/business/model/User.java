package com.anazzubair.techy.business.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 8557283676339374475L;

	@Id
	@Column(name = "id", columnDefinition = "serial", updatable = false)
	@SequenceGenerator(name = "UserIdSequenceGenerator", sequenceName = "users_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserIdSequenceGenerator")
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "middlename")
	private String middleName;

	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "isactive")
	@Type(type="yes_no")
	private Boolean isActive;
	
	@Column(name = "createdon")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private LocalDateTime createdOn;
	
	@JoinColumn(name = "createdby")
	@ManyToOne(fetch=FetchType.LAZY)
	private User createdBy;
	
	
	@Column(name = "modifiedon")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private LocalDateTime modifiedOn;

	@JoinColumn(name = "modifiedBy")
	@ManyToOne(fetch=FetchType.LAZY)
	private User modifiedBy;
	
	@Version
	@Column(name = "rowversion")
	private Long version;
	
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Long getId() {
		return id;
	}

	protected User() {
	}

	public User(String username) {
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
