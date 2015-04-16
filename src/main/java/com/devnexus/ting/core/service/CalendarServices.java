/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.devnexus.ting.core.service;

import java.util.List;

import com.devnexus.ting.model.User;
import com.devnexus.ting.model.UserCalendar;

/**
 *
 * This provides the custom schedule services.
 * 
 * @author summers
 */
public interface CalendarServices {
    
    public List<UserCalendar> getCalendarTemplate(String eventKey);
    
    public List<UserCalendar> getUserCalendar(User user, String eventKey);

    UserCalendar updateEntry(Long id, User user, UserCalendar newCalendar);
}
