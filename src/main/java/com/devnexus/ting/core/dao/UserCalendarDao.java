/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.devnexus.ting.core.dao;

import com.devnexus.ting.core.model.User;
import com.devnexus.ting.core.model.UserCalendar;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author summers
 */
public interface UserCalendarDao extends GenericDao<UserCalendar, Long>{
    
    public List<UserCalendar> getUserCalendar(User user, String eventKey);
    public List<UserCalendar> getTemplateCalendar(String eventKey);
    
    
}
