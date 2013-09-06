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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

	private static final long serialVersionUID = 8557283676339374475L;

	@Id
	@Column(name = "id", columnDefinition = "serial", updatable = false)
	@SequenceGenerator(name = "RoleIdSequenceGenerator", sequenceName = "roles_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RoleIdSequenceGenerator")
	private Long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name = "isactive", nullable = false)
	@Index(name = "roles_isactive_idx")
	@Type(type="yes_no")
	private Boolean isActive;
	
	@Column(name = "createdon", nullable = false)
	@Type(type = "org.jadira.roletype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime createdOn;
	
	@JoinColumn(name = "createdby", nullable = false)
	@ManyToOne(fetch=FetchType.LAZY)
	private User createdBy;
	
	@Column(name = "modifiedon", nullable = false)
	@Type(type = "org.jadira.roletype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime modifiedOn;

	@JoinColumn(name = "modifiedBy", nullable = false)
	@ManyToOne(fetch=FetchType.LAZY)
	private User modifiedBy;
	
	@Version
	@Column(name = "rowversion")
	private Long version;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	protected Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31)
					.append(name)
					.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Role))
			return false;
		
		Role otherRole = (Role) obj;
		return new EqualsBuilder()
					.append(this.name, otherRole.name)
					.isEquals();
	}	
}
