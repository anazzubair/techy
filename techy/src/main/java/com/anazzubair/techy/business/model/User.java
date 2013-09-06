package com.anazzubair.techy.business.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Index;
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

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "firstname", nullable = false)
	private String firstName;

	@Column(name = "middlename", nullable = true)
	private String middleName;

	@Column(name = "lastname", nullable = false)
	private String lastName;
	
	@Column(name = "isactive", nullable = false)
	@Index(name = "users_isactive_idx")
	@Type(type="yes_no")
	private Boolean isActive;
	
	@Column(name = "language", nullable = true)
	private String language;
	
	@ManyToMany
	@JoinTable(name = "userroles", joinColumns = {@JoinColumn(name = "userid", referencedColumnName = "id")},
								   inverseJoinColumns = {@JoinColumn(name = "roleid", referencedColumnName = "id")})
	private List<Role> roles;
	
	@Column(name = "createdon", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime createdOn;
	
	@JoinColumn(name = "createdby", nullable = false)
	@ManyToOne(fetch=FetchType.LAZY)
	private User createdBy;
	
	@Column(name = "modifiedon", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime modifiedOn;

	@JoinColumn(name = "modifiedBy", nullable = false)
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
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
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
		return new HashCodeBuilder(17, 31)
					.append(username)
					.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		
		User otherUser = (User) obj;
		return new EqualsBuilder()
					.append(this.username, otherUser.username)
					.isEquals();
	}	
}
