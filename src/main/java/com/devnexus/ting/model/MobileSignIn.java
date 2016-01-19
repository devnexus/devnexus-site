package com.devnexus.ting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class MobileSignIn extends BaseModelObject implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    
    /**
     * Android tokens sidestep the normal login-auth system.
     *
     * @see AndroidLoginController. //TODO:remove this by DevNexus 2017
     */
    @JsonIgnore
    @Size(max = 120)
    @Column(unique = true)
    @XmlTransient
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    

}
