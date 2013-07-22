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
package com.devnexus.ting.web.contextlistener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.devnexus.ting.common.Apphome;
import com.devnexus.ting.common.SpringContextMode;
import com.devnexus.ting.common.SystemInformationUtils;

/**
 * @author Gunnar Hillert
 *
 */
public class StartupContextListener implements ServletContextListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(StartupContextListener.class);

	/**
	 * Default constructor.
	 */
	public StartupContextListener() {
		super();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(final ServletContextEvent servletContextEvent) {

	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(final ServletContextEvent servletContextEvent) {
		final ServletContext servletContext = servletContextEvent.getServletContext();

		final String         contextPath      = servletContext.getContextPath();
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

		if (System.getProperty("ehcache.disk.store.dir") == null) {
			System.setProperty("ehcache.disk.store.dir", System.getProperty(Apphome.APP_HOME_DIRECTORY) + File.separator + "ehcache");
		}

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
