/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.devnexus.ting.web.controller;

import com.devnexus.ting.core.model.User;
import com.devnexus.ting.core.model.UserCalendar;
import com.devnexus.ting.core.service.CalendarServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * This class will service a users requests for their personalized calendar.
 * 
 * @author summers
 */
@Controller
public class CalendarController {
   
    @Autowired
    CalendarServices calendarService;
    
    @RequestMapping(value="/{eventKey}/calendar", method=RequestMethod.GET)
    public List<UserCalendar> calendar(@PathVariable("eventKey") String eventKey) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<UserCalendar> calendar = calendarService.getUserCalendar(user, eventKey);
        return calendar;
    }
    
}
