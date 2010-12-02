package com.devnexus.ting.core.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Index;


/**
 * The persistent class for the presentations database table.
 *
 */
@Entity
@org.hibernate.annotations.Table(appliesTo="PRESENTATIONS", indexes = { @Index(name="PRESENTATION_IDX", columnNames = { "TITLE" } ) } )
public class Presentation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Size(max=10000)
	private String description;

	@Size(max=255)
	private String presentationLink;

	@Size(max=255)
	private String audioLink;

    @ManyToOne
    @JoinColumn(name="SPEAKER_ID")
	private Speaker speaker;

	@Size(max=255)
	private String title;

	@ManyToOne
	@NotNull
    private Event event;

	//~~~~Constructors~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public Presentation() {
    }

    //~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
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

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getAudioLink() {
		return audioLink;
	}

	public void setAudioLink(String audioLink) {
		this.audioLink = audioLink;
	}

}