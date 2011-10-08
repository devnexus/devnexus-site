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
@XmlRootElement(name="presentations")
@XmlAccessorType(XmlAccessType.FIELD)
public class PresentationList implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement(name="presentation")
	private List<Presentation> presentations;

	public List<Presentation> getPresentations() {
		return presentations;
	}

	public void setPresentations(List<Presentation> presentations) {
		this.presentations = presentations;
	}

}