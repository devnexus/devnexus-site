package com.devnexus.ting.core.model.registration;

import com.devnexus.ting.core.model.BaseModelObject;
import com.devnexus.ting.core.model.Event;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable()
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE) //, include="non-lazy"
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EventSignup extends BaseModelObject {
    
    @ManyToMany
    private Set<PurchaseGroup> groups = new HashSet<>();

    @ManyToOne
    //@JoinColumn(name="EVENT_ID")
    @NotNull
    @XmlTransient
    private Event event;

    public Set<PurchaseGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<PurchaseGroup> groups) {
        this.groups = groups;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
    
    
    
}
