/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.devnexus.ting.web.controller;

import com.devnexus.ting.core.model.User;
import com.devnexus.ting.core.model.UserCalendar;
import com.devnexus.ting.core.service.CalendarServices;
import com.devnexus.ting.web.JaxbJacksonObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import org.jboss.aerogear.unifiedpush.JavaSender;
import org.jboss.aerogear.unifiedpush.SenderClient;
import org.jboss.aerogear.unifiedpush.message.UnifiedMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * This class will service a users requests for their personalized calendar.
 * 
 * @author summers
 */
@Controller
public class CalendarController {

    private static final Gson GSON = new GsonBuilder().create();

    @Autowired
    private JaxbJacksonObjectMapper mapper;

    JavaSender defaultJavaSender =
            new SenderClient("http://localhost:8080/ag-push");

    @Autowired
    CalendarServices calendarService;
    
    @RequestMapping(value={"/{eventKey}/usercalendar"}, method=RequestMethod.GET)
    @ResponseBody 
    public ResponseEntity<List<UserCalendar>>  calendar(@PathVariable("eventKey") String eventKey) throws JsonProcessingException {
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<UserCalendar> calendar = calendarService.getUserCalendar(user, eventKey);
        return new ResponseEntity<List<UserCalendar>>(calendar, headers, HttpStatus.OK);
    }

    @RequestMapping(value="/{eventKey}/usercalendar/{id}", method={RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody 
    public ResponseEntity<UserCalendar>  updateCalendar(@PathVariable("eventKey") String eventKey, @PathVariable("id") String id, HttpServletRequest request) {
                
        HttpHeaders headers = new HttpHeaders();
        
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        
        
        UserCalendar calendar = null;
        try {
            calendar = GSON.fromJson(request.getReader(), UserCalendar.class);

            calendar = calendarService.updateEntry(calendar.getId(), user, calendar);

             UnifiedMessage unifiedMessage = new UnifiedMessage.Builder()
                .pushApplicationId("66ebc03c-55d7-428d-8dc5-95e800c03f1f")
                .masterSecret("f9bd9ff9-0d62-4882-949e-6351815817d8")
                .aliases(Arrays.asList(user.getEmail()))
                .attribute("org.devnexus.sync.UserCalendar", "true")
                .build();
            
            defaultJavaSender.send(unifiedMessage);
             
            return new ResponseEntity<UserCalendar>(calendar, headers, HttpStatus.OK);
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
        }

    }

}
