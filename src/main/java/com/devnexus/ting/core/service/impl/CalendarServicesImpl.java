/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.devnexus.ting.core.service.impl;

import com.devnexus.ting.core.dao.UserCalendarDao;
import com.devnexus.ting.core.model.User;
import com.devnexus.ting.core.model.UserCalendar;
import com.devnexus.ting.core.service.CalendarServices;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarServicesImpl implements CalendarServices{

    @Autowired
    UserCalendarDao calendarDao;
    
    @Override
    public List<UserCalendar> getCalendarTemplate(String eventKey) {
        return calendarDao.getTemplateCalendar(eventKey);
    }
    
    
    @Override
    public List<UserCalendar> getUserCalendar(User user, String eventKey) {
        List<UserCalendar> calendar = calendarDao.getUserCalendar(user, eventKey);
        if (calendar == null || calendar.isEmpty()) {
            List<UserCalendar> template = getCalendarTemplate(eventKey);
            calendar = new ArrayList<UserCalendar>();
            for (UserCalendar entry : template) {
                UserCalendar copy = entry.copy();
                calendar.add(copy);
                
                copy.setUsername(user.getUsername());
                calendarDao.save(copy);
            }
            
        }
        
        return calendar;
    }
    
}
