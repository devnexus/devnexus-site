package com.devnexus.ting.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.service.BusinessService;

public class GlobalDataInterceptor implements HandlerInterceptor {

	@Autowired
	private BusinessService businessService;

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception exception)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {

		final List<Event>events = businessService.getAllNonCurrentEvents();
		request.setAttribute("eventsForMenu", events);
		return true;
	}

}
