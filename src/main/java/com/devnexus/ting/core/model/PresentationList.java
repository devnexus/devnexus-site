/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.devnexus.ting.core.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	public Set<String> getTrackIdsAsString() {
		final Set<String> trackIds = new HashSet<>();

		for (Presentation presentation : presentations) {
			if (presentation.getTrack() == null) {
				trackIds.add("na");
			}
			else {
				trackIds.add(String.valueOf(presentation.getTrack().getId()));
			}
		}

		return trackIds;
	}

	public Set<String> getRoomIdsAsString() {
		final Set<String> roomIds = new HashSet<>();

		for (Presentation presentation : presentations) {
			if (presentation.getRoom() == null) {
				roomIds.add("na");
			}
			else {
				roomIds.add(String.valueOf(presentation.getRoom().getId()));
			}
		}

		return roomIds;
	}

}
