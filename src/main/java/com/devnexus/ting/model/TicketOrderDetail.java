package com.devnexus.ting.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketOrderDetail extends BaseModelObject implements Comparable<TicketOrderDetail>{

    @NotNull
    @Size(max = 255)
    private String firstName;
    @NotNull
    @Size(max = 255)
    private String lastName;
    @NotNull
    @Size(max = 255)
    private String emailAddress;

    @Size(max = 255)
    private String city;
    @Size(max = 255)
    private String state;
    @Size(max = 255)
    private String country;

    @Size(max = 255)
    private String jobTitle;
    @Size(max = 255)
    private String company;

    @Size(max = 255)
    private String tShirtSize;
    @Size(max = 255)
    private String vegetarian;

    private Long ticketAddOn;
    
    @ManyToOne
    @XmlTransient
    private RegistrationDetails registration;
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String gettShirtSize() {
        return tShirtSize;
    }

    public void settShirtSize(String tShirtSize) {
        this.tShirtSize = tShirtSize;
    }

    public String getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(String vegetarian) {
        this.vegetarian = vegetarian;
    }

    public RegistrationDetails getRegistration() {
        return registration;
    }

    public void setRegistration(RegistrationDetails registration) {
        this.registration = registration;
    }

    @Override
    public int compareTo(TicketOrderDetail o) {
        if (lastName.equals(o.lastName)) {
            return firstName.compareTo(o.firstName);
        }
        return lastName.compareTo(o.lastName);
    }

    public Long getTicketAddOn() {
        return ticketAddOn;
    }

    public void setTicketAddOn(Long ticketAddOn) {
        this.ticketAddOn = ticketAddOn;
    }

}
