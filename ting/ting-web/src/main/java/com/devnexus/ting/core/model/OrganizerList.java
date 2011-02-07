package com.devnexus.ting.core.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 */
@XmlRootElement(name="organizers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizerList implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement(name="organizer")
	private List<Organizer> organizers;

	/**
	 * @param organizers
	 */
	public OrganizerList(List<Organizer> organizers) {
		super();
		this.organizers = organizers;
	}

	public List<Organizer> getOrganizers() {
		return organizers;
	}

	public void setOrganizers(List<Organizer> organizers) {
		this.organizers = organizers;
	}

}