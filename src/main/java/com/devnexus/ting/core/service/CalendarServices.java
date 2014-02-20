/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.devnexus.ting.core.service;

import com.devnexus.ting.core.model.User;
import com.devnexus.ting.core.model.UserCalendar;
import java.util.List;

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
