/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.devnexus.ting.web.filter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Allows for setting HTTP Header
 * E.g. this is useful for example in order to set the expiration header
 * of image files (png, gif, jpg etc.) in order to promote client side caching.
 *
 * @see http://weblogs.java.net/blog/felipegaucho/archive/2007/08/expires_http_he.html
 *
 * @author Gunnar Hillert
 *
 */
public class ResponseAddHttpHeadersFilter implements Filter {

	/** Initialize Logging. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseAddHttpHeadersFilter.class);

	FilterConfig config;

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		final HttpServletResponse response;
		final HttpServletRequest  request;

		if (res instanceof HttpServletResponse) {
			response = (HttpServletResponse) res;
		} else {
			throw new IllegalStateException("Cannot cast ServletResponse to HttpServletResponse.");
		}

		if (req instanceof HttpServletRequest) {
			request  = (HttpServletRequest) req;
		} else {
			throw new IllegalStateException("Cannot cast ServletRequest to HttpServletRequest.");
		}

		LOGGER.debug("RequestURI = " + request.getRequestURI());

		final int secondsToCache = Integer.valueOf(config.getInitParameter("secondsToCache"));

		setCacheExpireDate(response, secondsToCache);
		// pass the request/response on
		chain.doFilter(req, response);
	}

	public void init(FilterConfig filterConfig) {
		this.config = filterConfig;
	}

	public void destroy() {
		this.config = null;
	}

	public static void setCacheExpireDate(final HttpServletResponse response,
			final int seconds) {
		if (response != null) {
			final Calendar cal = new GregorianCalendar();
			cal.add(Calendar.SECOND, seconds);
			response.setHeader("Cache-Control", "PUBLIC, max-age=" + seconds + ", must-revalidate");
			response.setHeader("Expires", htmlExpiresDateFormat().format(cal.getTime()));
		}
	}

	public static DateFormat htmlExpiresDateFormat() {
		final DateFormat httpDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		httpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		return httpDateFormat;
	}
}
