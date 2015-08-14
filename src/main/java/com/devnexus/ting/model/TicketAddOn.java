package com.devnexus.ting.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
/**
 * This is per ticket add ons, things like Workshops etc.
 */
public class TicketAddOn extends BaseModelObject {

    @NotEmpty
    @Size(max = 255)
    protected String label;

    @NotNull
    private BigDecimal price;
    
    @NotNull
    private Integer maxAvailableTickets = 5000;

    @ManyToOne
    @XmlTransient
    @NotNull
    private TicketGroup ticketGroup;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getMaxAvailableTickets() {
        return maxAvailableTickets;
    }

    public void setMaxAvailableTickets(Integer maxAvailableTickets) {
        this.maxAvailableTickets = maxAvailableTickets;
    }

    public TicketGroup getTicketGroup() {
        return ticketGroup;
    }

    public void setTicketGroup(TicketGroup ticketGroup) {
        this.ticketGroup = ticketGroup;
    }
    
    public String getDisplayString() {
        return String.format("%s - %s", label, price.setScale(2).toString());
    }
    

}
