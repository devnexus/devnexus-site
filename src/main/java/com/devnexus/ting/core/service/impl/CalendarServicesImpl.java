/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.devnexus.ting.core.service.impl;

import com.devnexus.ting.core.dao.ScheduleItemDao;
import com.devnexus.ting.core.dao.UserCalendarDao;
import com.devnexus.ting.core.model.User;
import com.devnexus.ting.core.model.UserCalendar;
import com.devnexus.ting.core.service.CalendarServices;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CalendarServicesImpl implements CalendarServices{

    @Autowired
    UserCalendarDao calendarDao;

    @Autowired
    ScheduleItemDao scheduleDao;


    @Override
    public List<UserCalendar> getCalendarTemplate(String eventKey) {
        return calendarDao.getTemplateCalendar(eventKey);
    }
    
    
    @Override
    @Transactional
    public List<UserCalendar> getUserCalendar(User user, String eventKey) {
        List<UserCalendar> calendar = calendarDao.getUserCalendar(user, eventKey);
        if (calendar == null || calendar.isEmpty()) {
            List<UserCalendar> template = getCalendarTemplate(eventKey);
            calendar = new ArrayList<UserCalendar>();
            for (UserCalendar entry : template) {
                UserCalendar copy = entry.copy();
                
                copy.setUsername(user.getUsername());
                calendar.add(calendarDao.save(copy));
            }
            
        }
        
        return calendar;
    }

    @Override
    @Transactional    
    public UserCalendar updateEntry(Long id, User user, UserCalendar newCalendar) {

        UserCalendar storedCalendar = calendarDao.get(id);

        if (!storedCalendar.getUsername().equals(user.getUsername())) {
            throw new IllegalAccessError("User " + user.getUsername() + " does not have access to this calendar.");
        }

        if (storedCalendar.isFixed()) {
            return storedCalendar;
        }
        
        if (newCalendar.getItem() != null) {
            storedCalendar.setItem(scheduleDao.get(newCalendar.getItem().getId()));
        } else {
            storedCalendar.setItem(null);
        }

        calendarDao.save(storedCalendar);

        return storedCalendar;

    }

}
