package com.devnexus.ting.core.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlAccessorType(value=XmlAccessType.FIELD)
public class Event {

    @XmlAttribute
    @Id @GeneratedValue(generator="hibseq")
    private Long id;

	@Column(unique=true)
	@NotEmpty
	@Size(max=25)
	private String eventKey;

	@NotEmpty
	@Column(length=255)
	private String title;

	private boolean current;

    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="event")
	private Set<Presentation>presentations = new HashSet<Presentation>(0);

    @ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	private Set<Speaker>speakers = new HashSet<Speaker>(0);

	@Override
	public String toString() {
		return String.valueOf(id);
	}


	/**
	 * @param id
	 */
	public Event() {
		super();
	}

	/**
	 * @param id
	 */
	public Event(Long id) {
		super();
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the eventKey
	 */
	public String getEventKey() {
		return eventKey;
	}

	/**
	 * @param eventKey the eventKey to set
	 */
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	/**
	 * @return the presentations
	 */
	public Set<Presentation> getPresentations() {
		return presentations;
	}

	/**
	 * @param presentations the presentations to set
	 */
	public void setPresentations(Set<Presentation> presentations) {
		this.presentations = presentations;
	}

	/**
	 * @return the speakers
	 */
	public Set<Speaker> getSpeakers() {
		return speakers;
	}

	/**
	 * @param speakers the speakers to set
	 */
	public void setSpeakers(Set<Speaker> speakers) {
		this.speakers = speakers;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @param current the current to set
	 */
	public void setCurrent(boolean current) {
		this.current = current;
	}


	/**
	 * @return the current
	 */
	public boolean isCurrent() {
		return current;
	}

}
