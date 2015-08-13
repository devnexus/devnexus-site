
package com.devnexus.ting.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.commons.codec.digest.Md5Crypt;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CouponCode extends BaseModelObject {

    @XmlTransient
    @Transient
    public static final CouponCode EMPTY = new CouponCode();
    
    @XmlTransient
    @NotNull
    private String code;
    
    @ManyToOne
    @XmlTransient
    @NotNull
    private TicketGroup ticketGroup;
    
    
    @NotNull
    private BigDecimal price;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TicketGroup getTicketGroup() {
        return ticketGroup;
    }

    public void setTicketGroup(TicketGroup ticketGroup) {
        this.ticketGroup = ticketGroup;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    
    
}
