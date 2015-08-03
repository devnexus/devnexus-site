package com.devnexus.ting.model;

import javax.persistence.Entity;

@Entity
public class PayPalPayment extends BaseModelObject {
    
    private String paypalId;
    private String executeURL;
    private String acceptUrl;
    private String selfUrl;
    private String refundUrl;
    private String payerId;
    private String paymentId;
    
    
}
