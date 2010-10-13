package com.devnexus.ting.core.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the presentations database table.
 *
 */
@Entity
@Table(name="presentations")
public class Presentation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="abstract")
	private String description;

	@Column(name="presentation_link", length=255)
	private String presentationLink;

    @ManyToOne
    @JoinColumn(name="speaker_id", unique=false, nullable=false, insertable=true, updatable=true)
	private Speaker speaker;

	@Column(length=255)
	private String title;

	private Integer year;

    public Presentation() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String abstract_) {
		this.description = abstract_;
	}

	public String getPresentationLink() {
		return this.presentationLink;
	}

	public void setPresentationLink(String presentationLink) {
		this.presentationLink = presentationLink;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

}