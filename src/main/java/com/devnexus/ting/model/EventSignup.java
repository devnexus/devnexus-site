package com.devnexus.ting.model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EventSignup extends BaseModelObject {
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventSignup", targetEntity = PurchaseGroup.class)
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

    public Optional<PurchaseGroup> getGroup(PurchaseGroup purchaseGroup) {
        for (PurchaseGroup group : groups) {
            if (group.getId().equals(purchaseGroup.getId())) {
                return Optional.of(group);
            }
        }
        return Optional.empty();
    }
    
    
    
}
