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
@XmlRootElement(name="speakers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpeakerList implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement(name="speaker")
	private List<Speaker> speakers;

	public List<Speaker> getSpeakers() {
		return speakers;
	}

	public void setSpeakers(List<Speaker> speakers) {
		this.speakers = speakers;
	}

}