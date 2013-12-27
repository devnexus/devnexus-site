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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

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
	private Integer numberOfRooms;
	private SortedSet<Date> days;

    @XmlElement(name="scheduleItems")
	private List<ScheduleItem> scheduleItems;

    @XmlElement(name="headerItems")
    private List<ScheduleItem> headerItems;
    private transient Map<Date, List<ScheduleItem>> headerItemsByDate;

    @XmlElement(name="breakoutItems")
    private List<ScheduleItem> breakoutItems;


    private transient Map<Date, List<ScheduleItem>> breakoutItemsByDate;

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

	public Integer getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(Integer numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public List<ScheduleItem> getScheduleItems() {
		return scheduleItems;
	}

	public void setScheduleItems(List<ScheduleItem> scheduleItems) {
		this.scheduleItems = scheduleItems;
	}

    public List<ScheduleItem> getHeaderItems() {
        if (headerItems == null) {
            headerItems = new ArrayList<ScheduleItem>();
            for (ScheduleItem item : scheduleItems) {
                if (isHeaderItem(item)) {
                 headerItems.add(item);
                }
            }
        }
        return headerItems;
    }

    public List<ScheduleItem> getBreakoutItems() {
        if (breakoutItems == null) {
            breakoutItems = new ArrayList<ScheduleItem>();
            for (ScheduleItem item : scheduleItems) {
                if (isBreakoutItem(item)) {
                    breakoutItems.add(item);
                }
            }
        }
        return breakoutItems;
    }

    public boolean isHeaderItem(ScheduleItem item) {
        return !item.getScheduleItemType().equals(ScheduleItemType.SESSION) && !item.getScheduleItemType().equals(ScheduleItemType.BREAK);
    }

    public boolean isBreakoutItem(ScheduleItem item) {
        return item.getScheduleItemType().equals(ScheduleItemType.SESSION);
    }

    public List<ScheduleItem> findHeaderItemsOnDate(Date search) {
        if (headerItemsByDate == null) {
            headerItemsByDate = new HashMap<Date, List<ScheduleItem>>(scheduleItems.size());
            for (Date date : days) {
                for (ScheduleItem item : getHeaderItems()) {
                    List<ScheduleItem> items = headerItemsByDate.get(date);
                    if (items == null) {
                        items = new ArrayList<ScheduleItem>(getHeaderItems().size());
                        headerItemsByDate.put(date, items);
                    }

                    if (item.getFromTime().getTime() == date.getTime()) {
                        items.add(item);
                    }
                }
            }
        }
        return headerItemsByDate.get(search);
    }

    public List<ScheduleItem> findBreakoutItemsOnDate(Date search) {
        if (breakoutItemsByDate == null) {
            breakoutItemsByDate = new HashMap<Date, List<ScheduleItem>>(scheduleItems.size());
            for (Date date : days) {
                for (ScheduleItem item : getBreakoutItems()) {
                    List<ScheduleItem> items = breakoutItemsByDate.get(date);
                    if (items == null) {
                        items = new ArrayList<ScheduleItem>(getBreakoutItems().size());
                        breakoutItemsByDate.put(date, items);
                    }

                    if (item.getFromTime().getTime() == date.getTime()) {
                        items.add(item);
                    }
                }
            }
        }
        return breakoutItemsByDate.get(search);
    }

    public SortedSet<Room> findRooms(Date date) {

        SortedSet<Room>rooms = new TreeSet<Room>();
        for (ScheduleItem item : findBreakoutItemsOnDate(date)) {
            rooms.add(item.getRoom());
        }

        return rooms;
    }

    public List<ScheduleItem> findBreakoutItemsOnDateInRoom(Date date, Room room) {
        ArrayList<ScheduleItem> items = new ArrayList<ScheduleItem>();
        for (ScheduleItem item : findBreakoutItemsOnDate(date)) {
            if (item.getRoom().equals(room)) {
                items.add(item);
            }
        }
        return items;
    }

}
