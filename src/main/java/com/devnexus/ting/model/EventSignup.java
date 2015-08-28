package com.devnexus.ting.model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
* @author Summers Pittman
*/
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EventSignup extends BaseModelObject {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventSignup", targetEntity = TicketGroup.class, fetch = FetchType.EAGER)
    private Set<TicketGroup> groups = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventSignup", targetEntity = TicketAddOn.class, fetch = FetchType.EAGER)
    private Set<TicketAddOn> addOns = new HashSet<>();

    
    @ManyToOne
    //@JoinColumn(name="EVENT_ID")
    @NotNull
    @XmlTransient
    private Event event;

    public Set<TicketGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<TicketGroup> groups) {
        this.groups = groups;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
    public Optional<TicketGroup> getGroup(TicketGroup purchaseGroup) {
        for (TicketGroup group : groups) {
            if (group.getId().equals(purchaseGroup.getId())) {
                return Optional.of(group);
            }
        }
        return Optional.empty();
    }

    public Set<TicketAddOn> getAddOns() {
        return addOns;
    }

    public void setAddOns(Set<TicketAddOn> addOns) {
        this.addOns = addOns;
    }



}
