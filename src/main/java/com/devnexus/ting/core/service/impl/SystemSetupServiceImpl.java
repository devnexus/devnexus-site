/*
 * Copyright 2002-2013 the original author or authors.
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
package com.devnexus.ting.core.service.impl;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devnexus.ting.common.SpringContextMode;
import com.devnexus.ting.core.dao.BackupDao;
import com.devnexus.ting.core.dao.EventDao;
import com.devnexus.ting.core.dao.RoomDao;
import com.devnexus.ting.core.dao.SchemaMigrationDao;
import com.devnexus.ting.core.dao.SystemDao;
import com.devnexus.ting.core.model.Backup;
import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.model.Room;
import com.devnexus.ting.core.model.SchemaMigration;
import com.devnexus.ting.core.service.SystemSetupService;

/**
 * @author Gunnar Hillert
 * @since 1.0
 */
@Service("systemSetupService")
@Transactional
public class SystemSetupServiceImpl implements SystemSetupService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SystemSetupServiceImpl.class);

	@Autowired
	private SchemaMigrationDao   schemaMigrationDao;

	@Autowired
	private BackupDao            backupDao;

	@Autowired
	private SystemDao            systemDao;

	@Autowired
	private Environment environment;

	@Autowired
	private RoomDao roomDao;

	@Autowired
	private EventDao eventDao;

	@Value("${database.jdbc.driverClassName}")
	private String jdbcDriverClassName;

	@Value("${database.jdbc.url}")
	private String jdbcDatabaseUrl;

	@Override
	public void restore(final InputStream inputStream) {

		final Backup backup = backupDao.convertToBackupData(inputStream);

		this.restore(backup);

	}

	/** {@inheritDoc} */
	@Override
	public void loadAndRestoreSeedData() {
		final InputStream is = SystemSetupServiceImpl.class.getResourceAsStream("/data/seeddata.xml");
		restore(is);
	}

	/** {@inheritDoc} */
	@Override
	public void restore(final Backup backup) {

	   //TODO

	}

	@Override
	public void createDatabase() {
		LOGGER.warn("Create Database with Settings jdbcDriverClassName: '{}'; jdbcDatabaseUrl: '{}'", jdbcDriverClassName, jdbcDatabaseUrl);
		systemDao.createDatabase(false, null);
	}

	@Override
	public void updateDatabase() {
		systemDao.updateDatabase();
	}

	@Override
	public boolean isDatabaseSetup() {
		try {
			final List<SchemaMigration> migrations = schemaMigrationDao.getAll();

			if (migrations.isEmpty()) {
				return false;
			} else {
				return true;
			}
		} catch (InvalidDataAccessResourceUsageException e) {
			LOGGER.warn("Looks like the database has not been set up, yet.", e.getMessage());
			return false;
		}
	}

	@Override
	public Backup convertToBackupData(InputStream inputStream) {
		return backupDao.convertToBackupData(inputStream);
	}

	@Override
	public void setupDatabase() {
		if (!this.isDatabaseSetup()) {

			LOGGER.info("Ting Database is not setup, yet. Initializing DB...");

			this.createDatabase();

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
