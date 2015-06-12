/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devnexus.ting.web.form;

import com.devnexus.ting.model.EventSignup;
import com.devnexus.ting.model.TicketGroup;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author summers
 */
public class SignupRegisterView {

    private final EventSignup signUp;
    private final Date today = new Date();

    public SignupRegisterView(EventSignup signUp) {
        this.signUp = signUp;
    }

    public List<TicketGroup> getGroups() {
        return new ArrayList<TicketGroup>(
                signUp.getGroups().stream()
                        .filter(
                                item -> {return item.getCloseDate().after(today) && item.getOpenDate().before(today);})
                        .sorted(
                                (ticket, other) -> {return ticket.getLabel().compareTo(other.getLabel());})
                        .collect(Collectors.toList())
                        
                );
    }

    
    
}
