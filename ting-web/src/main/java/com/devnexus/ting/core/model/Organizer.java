package com.devnexus.ting.core.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the speakers database table.
 *
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Organizer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	@NotEmpty
	@Size(max=10000)
	private String bio;

	@Temporal(value=TemporalType.TIMESTAMP)
	@XmlTransient
	private Date createdAt;

	@NotEmpty
	@Size(max=255)
	private String firstName;

	@NotEmpty
	@Size(max=255)
	private String lastName;

	@Size(max=255)
	@XmlTransient
	private String picture;

	@Temporal(value=TemporalType.TIMESTAMP)
	@XmlTransient
	private Date updatedAt;

	@XmlAnyAttribute
	private Integer sortOrder;

    public Organizer() {
    }

	public Organizer(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getFullName() {
		return this.lastName + ", " + this.firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Organizer [firstName=" + firstName + ", id=" + id + ", lastName="
				+ lastName + "]";
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer order) {
		this.sortOrder = order;
	}

}