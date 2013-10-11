package com.devnexus.ting.core.model;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

import com.devnexus.ting.common.TingUtil;

/**
 *
 * @author Gunnar Hillert
 * @since 2.0
 *
 */
@MappedSuperclass
public abstract class Person extends BaseModelObject {

	/** serialVersionUID. */
	private static final long serialVersionUID = -6456631481903155317L;

	@NotEmpty
	@Size(max=10000)
	protected String bio;

	@NotEmpty
	@Size(max=255)
	protected String firstName;

	@NotEmpty
	@Size(max=255)
	protected String lastName;

	@ManyToOne(fetch = FetchType.LAZY)
	@XmlTransient
	@Cascade(CascadeType.ALL)
	protected FileData picture;

	@Size(max=255)
	protected String twitterId;

	@Size(max=255)
	protected String googlePlusId;

	@Size(max=255)
	protected String linkedInId;

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public String getBio() {
		return bio;
	}

	public String getBioAsHtml() {
		return TingUtil.getMarkDownProcessor().markdownToHtml(this.bio);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getFullName() {
		return this.lastName + ", " + this.firstName;
	}

	public String getFirstLastName() {
		return this.firstName + " " + this.lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public FileData getPicture() {
		return picture;
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPicture(FileData picture) {
		this.picture = picture;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public String getGooglePlusId() {
		return googlePlusId;
	}

	public void setGooglePlusId(String googlePlusId) {
		this.googlePlusId = googlePlusId;
	}

	public String getLinkedInId() {
		return linkedInId;
	}

	public void setLinkedInId(String linkedInId) {
		this.linkedInId = linkedInId;
	}
}
