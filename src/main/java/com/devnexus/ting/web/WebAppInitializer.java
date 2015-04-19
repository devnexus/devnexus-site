/*
 * Copyright 2002-2014 the original author or authors.
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
package com.devnexus.ting.web;

import java.io.File;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import net.sf.ehcache.constructs.web.filter.GzipFilter;

import org.apache.commons.lang.StringUtils;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import com.devnexus.ting.common.Apphome;
import com.devnexus.ting.common.SpringContextMode;
import com.devnexus.ting.common.SystemInformationUtils;
import com.devnexus.ting.web.config.DefaultApplicationContextInitializer;
import com.devnexus.ting.web.config.MainConfig;
import com.devnexus.ting.web.filter.JSONPRequestFilter;
import com.devnexus.ting.web.filter.LoggingFilter4Logback;
import com.devnexus.ting.web.filter.ResponseAddHttpHeadersFilter;


/**
 * @author Gunnar Hillert
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebAppInitializer.class);

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		final DefaultApplicationContextInitializer initializer = new DefaultApplicationContextInitializer();
		initializer.initialize(context);
		context.register(MainConfig.class);
		return context;
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {MainConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected void registerContextLoaderListener(ServletContext servletContext) {
		servletContext.addListener(HttpSessionEventPublisher.class);
		super.registerContextLoaderListener(servletContext);
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/s/*", "/ws/*", "/api/*" };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.setInitParameter("javax.servlet.jsp.jstl.fmt.localizationContext", "messages");

		//UrlRewriteFilter
		final FilterRegistration.Dynamic urlRewriteFilterRegistration = servletContext.addFilter("UrlRewriteFilter", new UrlRewriteFilter());
		urlRewriteFilterRegistration.setAsyncSupported(true);
		urlRewriteFilterRegistration.addMappingForUrlPatterns(null, true, "/proxy/*");

		// UTF-8 Encoding
		FilterRegistration.Dynamic encodingFilterRegistration = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
		encodingFilterRegistration.setInitParameter("encoding", "UTF-8");
		encodingFilterRegistration.setInitParameter("forceEncoding", "true");
		encodingFilterRegistration.addMappingForUrlPatterns(null, true, "/s/*");

		// gzipFilter
		FilterRegistration.Dynamic gzipFilterRegistration = servletContext.addFilter("gzipFilter", GzipFilter.class);
		gzipFilterRegistration.addMappingForUrlPatterns(null, true, "/s/*", "*.html", "/api/*");

		// jsonPRequestFilter
		FilterRegistration.Dynamic jsonPRequestFilterRegistration = servletContext.addFilter("JSONPRequestFilter", JSONPRequestFilter.class);
		jsonPRequestFilterRegistration.addMappingForUrlPatterns(null, true, "*.json");

		// lazyLoadingFilter
		FilterRegistration.Dynamic lazyLoadingFilterRegistration = servletContext.addFilter("lazyLoadingFilter", OpenEntityManagerInViewFilter.class);
		lazyLoadingFilterRegistration.addMappingForUrlPatterns(null, true, "*.html", "/s/*");

		// springSecurityFilterChain
		FilterRegistration.Dynamic springSecurityFilterChainRegistration = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
		springSecurityFilterChainRegistration.addMappingForUrlPatterns(null, true, "/*");

		// loggingFilter4Logback
		FilterRegistration.Dynamic loggingFilter4LogbackRegistration = servletContext.addFilter("loggingFilter4Logback", LoggingFilter4Logback.class);
		loggingFilter4LogbackRegistration.addMappingForUrlPatterns(null, true, "/s/*", "/api/*");

		// etagFilter
		FilterRegistration.Dynamic etagFilterRegistration = servletContext.addFilter("etagFilter", ShallowEtagHeaderFilter.class);
		etagFilterRegistration.addMappingForUrlPatterns(null, true, "/s/*");

		// sitemeshFilter
		FilterRegistration.Dynamic sitemeshRegistration = servletContext.addFilter("sitemesh", ConfigurableSiteMeshFilter.class);
		sitemeshRegistration.addMappingForUrlPatterns(null, true, "/s/*", "/index.jsp");

		// responseAddHttpHeadersFilter
		FilterRegistration.Dynamic responseAddHttpHeadersFilterRegistration = servletContext.addFilter("responseAddHttpHeadersFilter", ResponseAddHttpHeadersFilter.class);
		responseAddHttpHeadersFilterRegistration.setInitParameter("secondsToCache", "2592000");
		responseAddHttpHeadersFilterRegistration.addMappingForUrlPatterns(null, true, "*.css", "*.gif", "*.ico", "*.jpg", "*.png", "*.js");

		setupContext(servletContext);
		super.onStartup(servletContext);
	}

	public void setupContext(final ServletContext servletContext) {

		final String         contextPath      = "";
		final String         server           = servletContext.getServerInfo();

		final Apphome apphome = SystemInformationUtils.retrieveBasicSystemInformation();

		final String outMessage;
		SpringContextMode springContextMode = null;

		switch (apphome.getAppHomeSource()) {

			case SYSTEM_PROPERTY:
				outMessage = "System Property '" + Apphome.APP_HOME_DIRECTORY
									  + "' found: " + apphome.getAppHomePath();
				break;

			case ENVIRONMENT_VARIABLE:
				System.setProperty(Apphome.APP_HOME_DIRECTORY, apphome.getAppHomePath());

				outMessage = "Environment Variable '" + Apphome.APP_HOME_DIRECTORY
									  + "' found: " + apphome.getAppHomePath()
									  + ". Using it to set system property.";
				break;

			case USER_DIRECTORY:

				outMessage = "'" + Apphome.APP_HOME_DIRECTORY
									  + "' not found. Please set '" + Apphome.APP_HOME_DIRECTORY
									  + "' as a system property or as an environment variable. DEMO Mode, using embedded database.";
				break;
			case CLOUD:
				outMessage = "You are running in the cloud (CloudFoundry). No file system access available.";
				break;
			default: throw new IllegalStateException("Was expecting to resolve a home directory.");

		}

		if (SystemInformationUtils.existsConfigFile(apphome.getAppHomePath())) {
			springContextMode = SpringContextMode.ProductionContextConfiguration;
		} else {
			springContextMode = SpringContextMode.DemoContextConfiguration;
		}

		System.setProperty("ting-spring-profile", springContextMode.getCode());

		final StringBuilder bootMessage = new StringBuilder();

		bootMessage.append("\n");
		bootMessage.append(outMessage);
		bootMessage.append("\n");
		bootMessage.append("Using Spring Context: " + springContextMode);
		bootMessage.append("\n");
		bootMessage.append("Booting Ting...                          ").append("\n");
		bootMessage.append("-----------------------------------------------").append("\n");

		final String contextPathLabel = StringUtils.rightPad("Context Path", 40, '.');
		final String serverLabel = StringUtils.rightPad("Server", 40, '.');

		bootMessage.append(contextPathLabel + ": " + contextPath)     .append("\n");
		bootMessage.append(serverLabel      + ": " + server)          .append("\n");

		bootMessage.append(SystemInformationUtils.getAllSystemProperties());
		bootMessage.append("-----------------------------------------------").append("\n");

		LOGGER.info(bootMessage.toString());
	}

}
