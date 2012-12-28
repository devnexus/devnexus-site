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
package com.hillert.apptools.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;

import com.devnexus.ting.common.SpringContextMode;
import com.devnexus.ting.core.dao.EventDao;
import com.devnexus.ting.core.dao.RoomDao;
import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.model.Room;
import com.devnexus.ting.core.service.SystemSetupService;

/**
 *
 * @author Gunnar Hillert
 * @since 2.0
 *
 */
public class ContextRefreshedEventListener implements
		ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ContextRefreshedEventListener.class);

	@Autowired
	private SystemSetupService systemSetupService;

	@Autowired
	Environment environment;

	@Autowired
	private RoomDao roomDao;

	@Autowired
	private EventDao eventDao;

	/**
	*
	*
	*/
	@Transactional
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		// From: http://forum.springsource.org/showthread.php?t=84312&page=2

		if (event.getApplicationContext().getParent() == null) {

			LOGGER.info("Parent Spring Context started.");
			event.getApplicationContext().getEnvironment().getActiveProfiles();
			if (!systemSetupService.isDatabaseSetup()) {

				LOGGER.info("Ting Database is not setup, yet. Initializing DB...");

				systemSetupService.createDatabase();

				LOGGER.info("Ting Database is not setup, yet. Populating Seed Data...");

				// systemSetupService.loadAndRestoreSeedData();

				if (environment
						.acceptsProfiles(SpringContextMode.DemoContextConfiguration
								.getCode())) {
					LOGGER.info("Ting Database is not setup, yet. Populating Demo Data...");

					Event devnexus2013 = new Event(1388L, "devnexus2013", "DevNexus 2013", true);

					eventDao.replicate(devnexus2013);

					final Room room102     = new Room(8L, "Room 102", 144, 12, devnexus2013);
					final Room ballroomA   = new Room(1L, "Ballroom A", 111, 10, devnexus2013);
					final Room ballroomB   = new Room(7L, "Ballroom B", 111, 20, devnexus2013);
					final Room ballroomCDF = new Room(3L, "Ballroom CDF", 800, 30, devnexus2013);
					final Room ballroomE   = new Room(2L, "Ballroom E", 133, 40, devnexus2013);
					final Room room103     = new Room(4L, "Room 103", 100, 60, devnexus2013);
					final Room room104     = new Room(5L, "Room 104", 100, 70, devnexus2013);
					final Room room105     = new Room(6L, "Room 105", 80, 80, devnexus2013);

					final Room atrium     = new Room(10L, "Galleria Atrium", 800, 100, devnexus2013);
					final Room hallA      = new Room(9L, "Hall A", 800, 90, devnexus2013);

					roomDao.replicate(room102);
					roomDao.replicate(ballroomA);
					roomDao.replicate(ballroomB);
					roomDao.replicate(ballroomCDF);
					roomDao.replicate(ballroomE);
					roomDao.replicate(room103);
					roomDao.replicate(room104);
					roomDao.replicate(room105);
					roomDao.replicate(atrium);
					roomDao.replicate(hallA);

				}

			}

		}

	}

}
