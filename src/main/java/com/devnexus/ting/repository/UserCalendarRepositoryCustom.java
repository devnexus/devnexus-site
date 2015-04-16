/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.devnexus.ting.repository;

import java.util.List;

import com.devnexus.ting.model.User;
import com.devnexus.ting.model.UserCalendar;

/**
 *
 * @author summers
 */
public interface UserCalendarRepositoryCustom {

	public List<UserCalendar> getUserCalendar(User user, String eventKey);
	public List<UserCalendar> getTemplateCalendar(String eventKey);

}
