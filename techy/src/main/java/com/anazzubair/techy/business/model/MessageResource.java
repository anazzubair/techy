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
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

@Entity
@Table(name = "messageresources")
public class MessageResource implements Serializable {

	private static final long serialVersionUID = 8557283676339374475L;

	@Id
	@Column(name = "id", columnDefinition = "serial", updatable = false)
	@SequenceGenerator(name = "MessageResourceIdSequenceGenerator", sequenceName = "messageresources_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MessageResourceIdSequenceGenerator")
	private Long id;

	@Column(name = "code", nullable = false, unique = true)
	private String code;

	@Column(name = "en", nullable = false)
	private String english;

	@Column(name = "fr", nullable = false)
	private String french;
	
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
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getFrench() {
		return french;
	}

	public void setFrench(String french) {
		this.french = french;
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

	protected MessageResource() {
	}

	public MessageResource(String code, String english, String french) {
		this.code = code;
		this.english = english;
		this.french = french;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31)
					.append(code)
					.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MessageResource))
			return false;
		
		MessageResource otherUser = (MessageResource) obj;
		return new EqualsBuilder()
					.append(this.code, otherUser.code)
					.isEquals();
	}	
}
