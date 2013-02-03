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
import java.util.Date;
import java.util.List;
import java.util.SortedSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 */
@XmlRootElement(name="schedule")
@XmlAccessorType(XmlAccessType.FIELD)
public class ScheduleItemList implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer numberOfSessions;
	private Integer numberOfKeynoteSessions;
	private Integer numberOfBreakoutSessions;
	private Integer numberOfSpeakersAssigned;
	private Integer numberOfUnassignedSessions;

	private Integer numberOfBreaks;
	private Integer numberOfTracks;
	private SortedSet<Date> days;

	@XmlElement(name="scheduleItems")
	private List<ScheduleItem> scheduleItems;

	public SortedSet<Date> getDays() {
		return days;
	}

	public void setDays(SortedSet<Date> days) {
		this.days = days;
	}

	public Integer getNumberOfSessions() {
		return numberOfSessions;
	}

	public void setNumberOfSessions(Integer numberOfSessions) {
		this.numberOfSessions = numberOfSessions;
	}

	public Integer getNumberOfKeynoteSessions() {
		return numberOfKeynoteSessions;
	}

	public void setNumberOfKeynoteSessions(Integer numberOfKeynoteSessions) {
		this.numberOfKeynoteSessions = numberOfKeynoteSessions;
	}

	public Integer getNumberOfBreakoutSessions() {
		return numberOfBreakoutSessions;
	}

	public void setNumberOfBreakoutSessions(Integer numberOfBreakoutSessions) {
		this.numberOfBreakoutSessions = numberOfBreakoutSessions;
	}

	public Integer getNumberOfSpeakersAssigned() {
		return numberOfSpeakersAssigned;
	}

	public void setNumberOfSpeakersAssigned(Integer numberOfSpeakersAssigned) {
		this.numberOfSpeakersAssigned = numberOfSpeakersAssigned;
	}

	public Integer getNumberOfUnassignedSessions() {
		return numberOfUnassignedSessions;
	}

	public void setNumberOfUnassignedSessions(Integer numberOfUnassignedSessions) {
		this.numberOfUnassignedSessions = numberOfUnassignedSessions;
	}

	public Integer getNumberOfBreaks() {
		return numberOfBreaks;
	}

	public void setNumberOfBreaks(Integer numberOfBreaks) {
		this.numberOfBreaks = numberOfBreaks;
	}

	public Integer getNumberOfTracks() {
		return numberOfTracks;
	}

	public void setNumberOfTracks(Integer numberOfTracks) {
		this.numberOfTracks = numberOfTracks;
	}

	public List<ScheduleItem> getScheduleItems() {
		return scheduleItems;
	}

	public void setScheduleItems(List<ScheduleItem> scheduleItems) {
		this.scheduleItems = scheduleItems;
	}

}
