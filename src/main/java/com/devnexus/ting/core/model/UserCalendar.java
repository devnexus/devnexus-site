/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devnexus.ting.core.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author summers
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserCalendar extends BaseModelObject {

    private String username;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date fromTime;

    @ManyToOne
    @JoinColumn(name = "schedule_item_id")
    private ScheduleItem item;
    
    private boolean fixed = false;
    private boolean template = false;

    private String eventKey;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public ScheduleItem getItem() {
        return item;
    }

    public void setItem(ScheduleItem item) {
        this.item = item;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public boolean isTemplate() {
        return template;
    }

    public void setTemplate(boolean template) {
        this.template = template;
    }

    /**
     * Copies a template fields for use by a new user
     * @return 
     */
    public UserCalendar copy() {
        
        assert template;
        
        UserCalendar copy = new UserCalendar();
        copy.setFixed(fixed);
        copy.setFromTime(fromTime);
        copy.setItem(item);
        copy.setEventKey(eventKey);
        return copy;
        
    }
    
    
    
}
