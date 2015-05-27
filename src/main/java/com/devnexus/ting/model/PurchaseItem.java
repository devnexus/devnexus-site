package com.devnexus.ting.model;


import java.math.BigDecimal;
import java.util.Date;
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

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PurchaseItem extends BaseModelObject {

    @ManyToOne
    @XmlTransient
    private PurchaseGroup purchaseGroup;
    
    @ManyToOne
    //@JoinColumn(name="EVENT_ID")
    @NotNull
    @XmlTransient
    private Event event;

    @NotEmpty
    @Size(max = 255)
    protected String label;

    @NotNull
    private BigDecimal price;
    
    @Temporal(TemporalType.DATE)
    private Date openDate;
        
    @Temporal(TemporalType.DATE)
    private Date closeDate;

    public PurchaseGroup getPurchaseGroup() {
        return purchaseGroup;
    }

    public void setPurchaseGroup(PurchaseGroup purchaseGroup) {
        this.purchaseGroup = purchaseGroup;
    }

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

    
    
}
