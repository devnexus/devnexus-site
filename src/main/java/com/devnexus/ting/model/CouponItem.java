package com.devnexus.ting.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * A purchase group is a collection if Items of which only one may be purchased.
 *
 * @author summers
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CouponItem extends BaseModelObject {

    @NotEmpty
    @Size(max = 255)
    private String couponCode;

    @ManyToOne
    //@JoinColumn(name="EVENT_ID")
    @NotNull
    @XmlTransient
    private Event event;

    @NotEmpty
    @Size(max = 255)
    protected String label;

    @ManyToOne
    @XmlTransient
    protected EventSignup eventSignup;

    @NotNull
    private BigDecimal price;

    @Temporal(TemporalType.DATE)
    private Date openDate;

    @Temporal(TemporalType.DATE)
    private Date closeDate;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public EventSignup getEventSignup() {
        return eventSignup;
    }

    public void setEventSignup(EventSignup eventSignup) {
        this.eventSignup = eventSignup;
    }

    @Override
    public String toString() {
        return getLabel();
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.couponCode);
        hash = 37 * hash + Objects.hashCode(this.label);
        hash = 37 * hash + Objects.hashCode(this.price);
        hash = 37 * hash + Objects.hashCode(this.openDate);
        hash = 37 * hash + Objects.hashCode(this.closeDate);
        if (eventSignup != null) {
            hash = 37 * hash + Objects.hashCode(this.eventSignup.id);
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CouponItem other = (CouponItem) obj;
        if (!Objects.equals(this.couponCode, other.couponCode)) {
            return false;
        }
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.openDate, other.openDate)) {
            return false;
        }
        if (!Objects.equals(this.closeDate, other.closeDate)) {
            return false;
        }

        if (this.eventSignup != null) {
            if (other.eventSignup == null) {
                return false;
            } else {
                if (!Objects.equals(this.eventSignup.id, other.eventSignup.id)) {
                    return false;
                }
            }
        }

        return true;
    }

}
