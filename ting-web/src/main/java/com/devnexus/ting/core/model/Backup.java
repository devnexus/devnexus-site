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

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.devnexus.ting.common.CollectionUtils;

/**
 *
 * @author Gunnar Hillert
 * @since 2.0
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Backup {

    @XmlElementWrapper(name = "events")
    @XmlElement(name = "event")
    private List<Event> events = CollectionUtils.getArrayList();

    @XmlElementWrapper(name = "organizers")
    @XmlElement(name = "organizer")
    private List<Organizer>   organizers    = CollectionUtils.getArrayList();

    @XmlElementWrapper(name = "presentations")
    @XmlElement(name = "presentation")
    private List<Presentation>     presentations      = CollectionUtils.getArrayList();

    @XmlElementWrapper(name = "speakers")
    @XmlElement(name = "speaker")
    private List<Speaker>     speakers     = CollectionUtils.getArrayList();

    @XmlElementWrapper(name = "users")
    @XmlElement(name = "user")
    private List<User>      users       = CollectionUtils.getArrayList();


    //~~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Organizer> getOrganizers() {
        return organizers;
    }

    public void setOrganizers(List<Organizer> organizers) {
        this.organizers = organizers;
    }

    public List<Presentation> getPresentations() {
        return presentations;
    }

    public void setPresentations(List<Presentation> presentations) {
        this.presentations = presentations;
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
