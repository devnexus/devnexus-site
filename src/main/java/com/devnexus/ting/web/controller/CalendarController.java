/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.devnexus.ting.web.controller;

import com.devnexus.ting.core.model.User;
import com.devnexus.ting.core.model.UserCalendar;
import com.devnexus.ting.core.service.CalendarServices;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

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
    
    public List<UserCalendar> calendar(String eventKey) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<UserCalendar> calendar = calendarService.getUserCalendar(user, eventKey);
        return calendar;
    }
    
}
