/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.devnexus.ting.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.devnexus.ting.web.form.RegisterForm;
import java.math.BigDecimal;

/**
 * @author Summers Pittman
 */
@Entity
@Table(name = "registration")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RegistrationDetails extends BaseModelObject {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1071233976549394025L;

    public enum PaymentState {

        NONE, REQUIRES_INVOICE, PAYPAL_CREATED, PAID, REFUNDED, CANCELLED, ERROR, INVOICED
    };

    @NotNull
    private String contactName;

    @NotNull
    private String contactPhoneNumber;

    private BigDecimal finalCost = BigDecimal.ZERO;
    
    @NotNull
    private String contactEmailAddress;

    @NotNull
    private Integer ticketCount;

    @NotNull
    private Long ticketGroup;

    @ManyToOne
    //@JoinColumn(name="EVENT_ID")
    @XmlTransient
    private Event event;

    @Column(unique = true)
    private String registrationFormKey;
    private String couponCode;

    @Transient
    private String invoice;

    @Enumerated(EnumType.STRING)
    private PaymentState paymentState = PaymentState.NONE;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registration", targetEntity = TicketOrderDetail.class, fetch = FetchType.EAGER)
    private List<TicketOrderDetail> orderDetails = new ArrayList<>();

    public Integer getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }

    public Long getTicketGroup() {
        return ticketGroup;
    }

    public void setTicketGroup(Long ticketGroup) {
        this.ticketGroup = ticketGroup;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public List<TicketOrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<TicketOrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getRegistrationFormKey() {
        return registrationFormKey;
    }

    public void setRegistrationFormKey(String registrationFormKey) {
        this.registrationFormKey = registrationFormKey;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public PaymentState getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(PaymentState paymentState) {
        this.paymentState = paymentState;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmailAddress() {
        return contactEmailAddress;
    }

    public void setContactEmailAddress(String contactEmailAddress) {
        this.contactEmailAddress = contactEmailAddress;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public BigDecimal getFinalCost() {
        return finalCost == null?BigDecimal.ZERO:finalCost;
    }

    public void setFinalCost(BigDecimal finalCost) {
        this.finalCost = finalCost;
    }

    
    
    public void copyPageOne(RegisterForm registerForm) {
        setCouponCode(registerForm.getCouponCode());
        setTicketCount(registerForm.getTicketCount());
        setTicketGroup(registerForm.getTicketGroup());
        setContactEmailAddress(registerForm.getContactEmailAddress());
        setContactPhoneNumber(registerForm.getContactPhoneNumber());
        setContactName(registerForm.getContactName());
        IntStream.range(0, registerForm.getTicketCount()).forEach(ignore -> {
            orderDetails.add(new TicketOrderDetail());
        });
    }

}
