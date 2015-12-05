/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devnexus.ting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author summers
 */
@Entity
public class PaypalLink extends BaseModelObject {
    private String href;
    private String rel;
    private String httpMethod;
    
    @ManyToOne
    @XmlTransient
    @JsonIgnore
    private PayPalPayment payment;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String method) {
        this.httpMethod = method;
    }

    public PayPalPayment getPayment() {
        return payment;
    }

    public void setPayment(PayPalPayment payment) {
        this.payment = payment;
    }
    
    
    
}
