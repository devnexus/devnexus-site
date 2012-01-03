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

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Index;

import com.devnexus.ting.common.TingUtil;


/**
 * The persistent class for the presentations database table.
 *
 */
@Entity
@Cacheable()
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Table(appliesTo="PRESENTATIONS", indexes = { @Index(name="PRESENTATION_IDX", columnNames = { "TITLE" } ) } )
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Presentation extends BaseModelObject {
    private static final long serialVersionUID = 1L;

    @Size(max=255)
    private String audioLink;

    @Size(max=10000)
    private String description;

    @ManyToOne
    @NotNull
    @XmlTransient
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @XmlTransient
    @Cascade(CascadeType.ALL)
    @Valid
    private FileData presentationFile;

    @Size(max=255)
    private String presentationLink;

    @ManyToOne
    @JoinColumn(name="SPEAKER_ID")
    private Speaker speaker;

    @Size(max=255)
    private String title;

    //~~~~Constructors~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public Presentation() {
    }

    //~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public String getAudioLink() {
        return audioLink;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDescriptionAsHtml() {
        return TingUtil.getMarkDownProcessor().markdownToHtml(this.description);
    }

    public Event getEvent() {
        return event;
    }

    public FileData getPresentationFile() {
        return presentationFile;
    }

    public String getPresentationLink() {
        return this.presentationLink;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public String getTitle() {
        return this.title;
    }

    public void setAudioLink(String audioLink) {
        this.audioLink = audioLink;
    }

    public void setDescription(String abstract_) {
        this.description = abstract_;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setPresentationFile(FileData presentationFile) {
        this.presentationFile = presentationFile;
    }

    public void setPresentationLink(String presentationLink) {
        this.presentationLink = presentationLink;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}