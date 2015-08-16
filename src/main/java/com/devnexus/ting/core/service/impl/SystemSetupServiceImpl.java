/*
 * Copyright 2002-2015 the original author or authors.
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devnexus.ting.common.CalendarUtils;
import com.devnexus.ting.common.SpringContextMode;
import com.devnexus.ting.core.dao.SystemDao;
import com.devnexus.ting.core.service.SystemSetupService;
import com.devnexus.ting.core.service.UserService;
import com.devnexus.ting.core.service.exception.DuplicateUserException;
import com.devnexus.ting.model.AuthorityType;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.PresentationTag;
import com.devnexus.ting.model.Room;
import com.devnexus.ting.model.ScheduleItem;
import com.devnexus.ting.model.ScheduleItemType;
import com.devnexus.ting.model.SchemaMigration;
import com.devnexus.ting.model.Track;
import com.devnexus.ting.model.User;
import com.devnexus.ting.model.UserAuthority;
import com.devnexus.ting.repository.EventRepository;
import com.devnexus.ting.repository.PresentationTagRepository;
import com.devnexus.ting.repository.RoomRepository;
import com.devnexus.ting.repository.ScheduleItemRepository;
import com.devnexus.ting.repository.SchemaMigrationRepository;
import com.devnexus.ting.repository.TrackRepository;
import com.devnexus.ting.repository.UserAuthorityRepository;

/**
 * @author Gunnar Hillert
 * @since 1.0
 */
@Service("systemSetupService")
public class SystemSetupServiceImpl implements SystemSetupService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SystemSetupServiceImpl.class);

	@Autowired
	private SchemaMigrationRepository schemaMigrationRepository;

	@Autowired
	private SystemDao systemDao;

	@Autowired
	private UserAuthorityRepository userAuthorityDao;

	@Autowired
	private Environment environment;

	@Autowired
	private RoomRepository roomDao;

	@Autowired
	private EventRepository eventDao;

	@Autowired
	private PresentationTagRepository presentationTagDao;

	@Autowired
	private TrackRepository trackDao;

	@Autowired
	private UserService userService;

	@Autowired
	private ScheduleItemRepository scheduleItemDao;

	@Value("${spring.datasource.driverClassName}")
	private String jdbcDriverClassName;

	@Value("${spring.datasource.url}")
	private String jdbcDatabaseUrl;

	@Override
	public void restore(final InputStream inputStream) {

//		final Backup backup = backupDao.convertToBackupData(inputStream);
//
//		this.restore(backup);

	}

	/** {@inheritDoc} */
	private void loadAndRestoreSeedData() {

		User user = new User();
                user.setId(1024l);
		user.setPassword("devnexus");
		user.setUsername("admin");
		user.setFirstName("admin");
		user.setLastName("admin");
		user.setEmail("notused@something.com");

		final User persistedUser;

		try {
			persistedUser = userService.addUser(user);
		} catch (DuplicateUserException e) {
			throw new IllegalStateException(e);
		}

		userAuthorityDao.save(new UserAuthority(persistedUser, AuthorityType.ADMIN));

	}

	private void createDatabase() {
		LOGGER.warn("Create Database with Settings jdbcDriverClassName: '{}'; jdbcDatabaseUrl: '{}'", jdbcDriverClassName, jdbcDatabaseUrl);
		systemDao.createDatabase(false, null);
	}

	@Override
	public boolean isDatabaseSetup() {
		try {
			final List<SchemaMigration> migrations = schemaMigrationRepository.findAll();

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
	@Transactional
	public void setupDatabase() {

			LOGGER.info("Ting Database is not setup, yet. Initializing DB...");

			this.createDatabase();

			LOGGER.info("Ting Database is not setup, yet. Populating Seed Data...");

			loadAndRestoreSeedData();

			if (environment.acceptsProfiles(SpringContextMode.DemoContextConfiguration.getCode())) {
				setupDemoData();
			}
	}

	@Transactional
	@Override
	public void setupDemoData() {
		LOGGER.info("Ting Database is not setup, yet. Populating Demo Data...");

		final int currentYear = new GregorianCalendar().get(Calendar.YEAR);

		Event devnexusEvent= new Event(1388L, "devnexus" + currentYear, "DevNexus " + currentYear, true);

		eventDao.replicate(devnexusEvent);

		final Room room102     = new Room(8L, "Room 102", 144, 12, devnexusEvent, "#e77ea5");
		final Room ballroomA   = new Room(1L, "Ballroom A", 111, 10, devnexusEvent, "#8ec741");
		final Room ballroomB   = new Room(7L, "Ballroom B", 111, 20, devnexusEvent, "#faa21b");
		final Room ballroomCDF = new Room(3L, "Ballroom CDF", 800, 30, devnexusEvent, "#2a2d7c");
		final Room ballroomE   = new Room(2L, "Ballroom E", 133, 40, devnexusEvent, "#5b903f");
		final Room room103     = new Room(4L, "Room 103", 100, 60, devnexusEvent, "#6ec7e8");
		final Room room104     = new Room(5L, "Room 104", 100, 70, devnexusEvent, "#a6c4e7");
		final Room room105     = new Room(6L, "Room 105", 80, 80, devnexusEvent, "#015351");

		final Room atrium     = new Room(10L, "Exhibit Area", 800, 100, devnexusEvent, "#ed1e24");
		final Room hallA      = new Room(9L, "Hall A", 800, 90, devnexusEvent, "#edce1e");

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

		PresentationTag tag1 = new PresentationTag(1L, "Big Data");
		PresentationTag tag2 = new PresentationTag(2L, "HTML5");
		PresentationTag tag3 = new PresentationTag(3L, "PaaS");
		PresentationTag tag4 = new PresentationTag(4L, "CSS");

		presentationTagDao.replicate(tag1);
		presentationTagDao.replicate(tag2);
		presentationTagDao.replicate(tag3);
		presentationTagDao.replicate(tag4);

		Track track1 = new Track(1L, "Data", 3, devnexusEvent);
		Track track2 = new Track(2L, "Web", 1, devnexusEvent);
		Track track3 = new Track(3L, "Agile", 2, devnexusEvent);

		trackDao.replicate(track1);
		trackDao.replicate(track2);
		trackDao.replicate(track3);

		setupDemoSchedule(currentYear);
	}

	private void setupDemoSchedule(int currentYear) {

		final Event event = eventDao.getByEventKey("devnexus" + currentYear);

		final Room room102     = roomDao.getOne(8L);
		final Room ballroomA   = roomDao.getOne(1L);
		final Room ballroomB   = roomDao.getOne(7L);
		final Room ballroomCDF = roomDao.getOne(3L);
		final Room ballroomE   = roomDao.getOne(2L);
		final Room room103     = roomDao.getOne(4L);
		final Room room104     = roomDao.getOne(5L);
		final Room room105     = roomDao.getOne(6L);

		final Room atrium     = roomDao.getOne(10L);
		final Room hallA      = roomDao.getOne(9L);

		ScheduleItem scheduleItem1 = new ScheduleItem();
		scheduleItem1.setScheduleItemType(ScheduleItemType.REGISTRATION);
		scheduleItem1.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 8, 0, 0).getTime());
		scheduleItem1.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 9, 0, 0).getTime());
		scheduleItem1.setCreatedDate(new Date());
		scheduleItem1.setRoom(atrium);
		scheduleItem1.setEvent(event);

		scheduleItemDao.save(scheduleItem1);

		ScheduleItem scheduleItem2 = new ScheduleItem();
		scheduleItem2.setScheduleItemType(ScheduleItemType.ADMINISTRATIVE);
		scheduleItem2.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 9,  0, 0).getTime());
		scheduleItem2.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 9, 15, 0).getTime());
		scheduleItem2.setCreatedDate(new Date());
		scheduleItem2.setTitle("Welcome");
		scheduleItem2.setRoom(ballroomCDF);
		scheduleItem2.setEvent(event);

		scheduleItemDao.save(scheduleItem2);

		ScheduleItem scheduleItem3 = new ScheduleItem();
		scheduleItem3.setScheduleItemType(ScheduleItemType.KEYNOTE);
		scheduleItem3.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18,  9, 15, 0).getTime());
		scheduleItem3.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 10, 15, 0).getTime());
		scheduleItem3.setCreatedDate(new Date());
		scheduleItem3.setTitle("TBD");
		scheduleItem3.setRoom(ballroomCDF);
		scheduleItem3.setEvent(event);

		scheduleItemDao.save(scheduleItem3);

		ScheduleItem scheduleItem4 = new ScheduleItem();
		scheduleItem4.setScheduleItemType(ScheduleItemType.BREAK);
		scheduleItem4.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 10, 15, 0).getTime());
		scheduleItem4.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 10, 30, 0).getTime());
		scheduleItem4.setCreatedDate(new Date());
		scheduleItem4.setTitle("Break");
		scheduleItem4.setRoom(atrium);
		scheduleItem4.setEvent(event);

		scheduleItemDao.save(scheduleItem4);

		//Sessions

		// Block 1

		ScheduleItem block1Session1 = new ScheduleItem();
		block1Session1.setScheduleItemType(ScheduleItemType.SESSION);
		block1Session1.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 10, 30, 0).getTime());
		block1Session1.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 11, 45, 0).getTime());
		block1Session1.setCreatedDate(new Date());
		block1Session1.setRoom(room102);
		block1Session1.setTitle("TBD");
		block1Session1.setEvent(event);

		ScheduleItem block1Session2 = new ScheduleItem();
		block1Session2.setScheduleItemType(ScheduleItemType.SESSION);
		block1Session2.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 10, 30, 0).getTime());
		block1Session2.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 11, 45, 0).getTime());
		block1Session2.setCreatedDate(new Date());
		block1Session2.setRoom(ballroomB);
		block1Session2.setTitle("TBD");
		block1Session2.setEvent(event);

		ScheduleItem block1Session3 = new ScheduleItem();
		block1Session3.setScheduleItemType(ScheduleItemType.SESSION);
		block1Session3.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 10, 30, 0).getTime());
		block1Session3.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 11, 45, 0).getTime());
		block1Session3.setCreatedDate(new Date());
		block1Session3.setRoom(ballroomCDF);
		block1Session3.setTitle("TBD");
		block1Session3.setEvent(event);

		ScheduleItem block1Session4 = new ScheduleItem();
		block1Session4.setScheduleItemType(ScheduleItemType.SESSION);
		block1Session4.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 10, 30, 0).getTime());
		block1Session4.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 11, 45, 0).getTime());
		block1Session4.setCreatedDate(new Date());
		block1Session4.setRoom(ballroomE);
		block1Session4.setTitle("TBD");
		block1Session4.setEvent(event);

		ScheduleItem block1Session5 = new ScheduleItem();
		block1Session5.setScheduleItemType(ScheduleItemType.SESSION);
		block1Session5.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 10, 30, 0).getTime());
		block1Session5.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 11, 45, 0).getTime());
		block1Session5.setCreatedDate(new Date());
		block1Session5.setRoom(room103);
		block1Session5.setTitle("TBD");
		block1Session5.setEvent(event);

		ScheduleItem block1Session6 = new ScheduleItem();
		block1Session6.setScheduleItemType(ScheduleItemType.SESSION);
		block1Session6.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 10, 30, 0).getTime());
		block1Session6.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 11, 45, 0).getTime());
		block1Session6.setCreatedDate(new Date());
		block1Session6.setRoom(room104);
		block1Session6.setTitle("TBD");
		block1Session6.setEvent(event);

		ScheduleItem block1Session7 = new ScheduleItem();
		block1Session7.setScheduleItemType(ScheduleItemType.SESSION);
		block1Session7.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 10, 30, 0).getTime());
		block1Session7.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 11, 45, 0).getTime());
		block1Session7.setCreatedDate(new Date());
		block1Session7.setRoom(room105);
		block1Session7.setTitle("TBD");
		block1Session7.setEvent(event);

		scheduleItemDao.save(block1Session1);
		scheduleItemDao.save(block1Session2);
		scheduleItemDao.save(block1Session3);
		scheduleItemDao.save(block1Session4);
		scheduleItemDao.save(block1Session5);
		scheduleItemDao.save(block1Session6);
		scheduleItemDao.save(block1Session7);

		ScheduleItem block1Lunch = new ScheduleItem();
		block1Lunch.setScheduleItemType(ScheduleItemType.BREAK);
		block1Lunch.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 11, 45, 0).getTime());
		block1Lunch.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 12, 30, 0).getTime());
		block1Lunch.setCreatedDate(new Date());
		block1Lunch.setTitle("Lunch");
		block1Lunch.setRoom(hallA);
		block1Lunch.setEvent(event);

		scheduleItemDao.save(block1Lunch);

		ScheduleItem block1Dessert = new ScheduleItem();
		block1Dessert.setScheduleItemType(ScheduleItemType.BREAK);
		block1Dessert.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 12, 30, 0).getTime());
		block1Dessert.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 13, 00, 0).getTime());
		block1Dessert.setCreatedDate(new Date());
		block1Dessert.setTitle("Dessert");
		block1Dessert.setRoom(atrium);
		block1Dessert.setEvent(event);

		scheduleItemDao.save(block1Dessert);

		// Block 2

		ScheduleItem block2Session1 = new ScheduleItem();
		block2Session1.setScheduleItemType(ScheduleItemType.SESSION);
		block2Session1.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 13, 00, 0).getTime());
		block2Session1.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 14, 15, 0).getTime());
		block2Session1.setCreatedDate(new Date());
		block2Session1.setRoom(room102);
		block2Session1.setTitle("TBD");
		block2Session1.setEvent(event);

		ScheduleItem block2Session2 = new ScheduleItem();
		block2Session2.setScheduleItemType(ScheduleItemType.SESSION);
		block2Session2.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 13, 00, 0).getTime());
		block2Session2.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 14, 15, 0).getTime());
		block2Session2.setCreatedDate(new Date());
		block2Session2.setRoom(ballroomB);
		block2Session2.setTitle("TBD");
		block2Session2.setEvent(event);

		ScheduleItem block2Session3 = new ScheduleItem();
		block2Session3.setScheduleItemType(ScheduleItemType.SESSION);
		block2Session3.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 13, 00, 0).getTime());
		block2Session3.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 14, 15, 0).getTime());
		block2Session3.setCreatedDate(new Date());
		block2Session3.setRoom(ballroomCDF);
		block2Session3.setTitle("TBD");
		block2Session3.setEvent(event);

		ScheduleItem block2Session4 = new ScheduleItem();
		block2Session4.setScheduleItemType(ScheduleItemType.SESSION);
		block2Session4.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 13, 00, 0).getTime());
		block2Session4.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 14, 15, 0).getTime());
		block2Session4.setCreatedDate(new Date());
		block2Session4.setRoom(ballroomE);
		block2Session4.setTitle("TBD");
		block2Session4.setEvent(event);

		ScheduleItem block2Session5 = new ScheduleItem();
		block2Session5.setScheduleItemType(ScheduleItemType.SESSION);
		block2Session5.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 13, 00, 0).getTime());
		block2Session5.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 14, 15, 0).getTime());
		block2Session5.setCreatedDate(new Date());
		block2Session5.setRoom(room103);
		block2Session5.setTitle("TBD");
		block2Session5.setEvent(event);

		ScheduleItem block2Session6 = new ScheduleItem();
		block2Session6.setScheduleItemType(ScheduleItemType.SESSION);
		block2Session6.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 13, 00, 0).getTime());
		block2Session6.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 14, 15, 0).getTime());
		block2Session6.setCreatedDate(new Date());
		block2Session6.setRoom(room104);
		block2Session6.setTitle("TBD");
		block2Session6.setEvent(event);

		ScheduleItem block2Session7 = new ScheduleItem();
		block2Session7.setScheduleItemType(ScheduleItemType.SESSION);
		block2Session7.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 13, 00, 0).getTime());
		block2Session7.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 14, 15, 0).getTime());
		block2Session7.setCreatedDate(new Date());
		block2Session7.setRoom(room105);
		block2Session7.setTitle("TBD");
		block2Session7.setEvent(event);

		scheduleItemDao.save(block2Session1);
		scheduleItemDao.save(block2Session2);
		scheduleItemDao.save(block2Session3);
		scheduleItemDao.save(block2Session4);
		scheduleItemDao.save(block2Session5);
		scheduleItemDao.save(block2Session6);
		scheduleItemDao.save(block2Session7);

		ScheduleItem block2Break = new ScheduleItem();
		block2Break.setScheduleItemType(ScheduleItemType.BREAK);
		block2Break.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 14, 15, 0).getTime());
		block2Break.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 14, 30, 0).getTime());
		block2Break.setCreatedDate(new Date());
		block2Break.setTitle("Break");
		block2Break.setRoom(atrium);
		block2Break.setEvent(event);

		scheduleItemDao.save(block2Break);

		// Block 3

		ScheduleItem block3Session1 = new ScheduleItem();
		block3Session1.setScheduleItemType(ScheduleItemType.SESSION);
		block3Session1.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 14, 30, 0).getTime());
		block3Session1.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 15, 45, 0).getTime());
		block3Session1.setCreatedDate(new Date());
		block3Session1.setRoom(ballroomA);
		block3Session1.setTitle("TBD");
		block3Session1.setEvent(event);

		ScheduleItem block3Session2 = new ScheduleItem();
		block3Session2.setScheduleItemType(ScheduleItemType.SESSION);
		block3Session2.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 14, 30, 0).getTime());
		block3Session2.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 15, 45, 0).getTime());
		block3Session2.setCreatedDate(new Date());
		block3Session2.setRoom(ballroomB);
		block3Session2.setTitle("TBD");
		block3Session2.setEvent(event);

		ScheduleItem block3Session3 = new ScheduleItem();
		block3Session3.setScheduleItemType(ScheduleItemType.SESSION);
		block3Session3.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 14, 30, 0).getTime());
		block3Session3.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 15, 45, 0).getTime());
		block3Session3.setCreatedDate(new Date());
		block3Session3.setRoom(ballroomCDF);
		block3Session3.setTitle("TBD");
		block3Session3.setEvent(event);

		ScheduleItem block3Session4 = new ScheduleItem();
		block3Session4.setScheduleItemType(ScheduleItemType.SESSION);
		block3Session4.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 14, 30, 0).getTime());
		block3Session4.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 15, 45, 0).getTime());
		block3Session4.setCreatedDate(new Date());
		block3Session4.setRoom(ballroomE);
		block3Session4.setTitle("TBD");
		block3Session4.setEvent(event);

		ScheduleItem block3Session5 = new ScheduleItem();
		block3Session5.setScheduleItemType(ScheduleItemType.SESSION);
		block3Session5.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 14, 30, 0).getTime());
		block3Session5.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 15, 45, 0).getTime());
		block3Session5.setCreatedDate(new Date());
		block3Session5.setRoom(room103);
		block3Session5.setTitle("TBD");
		block3Session5.setEvent(event);

		ScheduleItem block3Session6 = new ScheduleItem();
		block3Session6.setScheduleItemType(ScheduleItemType.SESSION);
		block3Session6.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 14, 30, 0).getTime());
		block3Session6.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 15, 45, 0).getTime());
		block3Session6.setCreatedDate(new Date());
		block3Session6.setRoom(room104);
		block3Session6.setTitle("TBD");
		block3Session6.setEvent(event);

		ScheduleItem block3Session7 = new ScheduleItem();
		block3Session7.setScheduleItemType(ScheduleItemType.SESSION);
		block3Session7.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 14, 30, 0).getTime());
		block3Session7.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 15, 45, 0).getTime());
		block3Session7.setCreatedDate(new Date());
		block3Session7.setRoom(room105);
		block3Session7.setTitle("TBD");
		block3Session7.setEvent(event);

		scheduleItemDao.save(block3Session1);
		scheduleItemDao.save(block3Session2);
		scheduleItemDao.save(block3Session3);
		scheduleItemDao.save(block3Session4);
		scheduleItemDao.save(block3Session5);
		scheduleItemDao.save(block3Session6);
		scheduleItemDao.save(block3Session7);

		final ScheduleItem block3Break = new ScheduleItem();
		block3Break.setScheduleItemType(ScheduleItemType.BREAK);
		block3Break.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 15, 45, 0).getTime());
		block3Break.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 16, 00, 0).getTime());
		block3Break.setCreatedDate(new Date());
		block3Break.setTitle("Break");
		block3Break.setRoom(atrium);
		block3Break.setEvent(event);

		scheduleItemDao.save(block3Break);

		// Block 4

		ScheduleItem block4Session1 = new ScheduleItem();
		block4Session1.setScheduleItemType(ScheduleItemType.SESSION);
		block4Session1.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 16, 00, 0).getTime());
		block4Session1.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 17, 15, 0).getTime());
		block4Session1.setCreatedDate(new Date());
		block4Session1.setRoom(ballroomA);
		block4Session1.setTitle("TBD");
		block4Session1.setEvent(event);

		ScheduleItem block4Session2 = new ScheduleItem();
		block4Session2.setScheduleItemType(ScheduleItemType.SESSION);
		block4Session2.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 16, 00, 0).getTime());
		block4Session2.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 17, 15, 0).getTime());
		block4Session2.setCreatedDate(new Date());
		block4Session2.setRoom(ballroomB);
		block4Session2.setTitle("TBD");
		block4Session2.setEvent(event);

		ScheduleItem block4Session3 = new ScheduleItem();
		block4Session3.setScheduleItemType(ScheduleItemType.SESSION);
		block4Session3.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 16, 00, 0).getTime());
		block4Session3.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 17, 15, 0).getTime());
		block4Session3.setCreatedDate(new Date());
		block4Session3.setRoom(ballroomCDF);
		block4Session3.setTitle("TBD");
		block4Session3.setEvent(event);

		ScheduleItem block4Session4 = new ScheduleItem();
		block4Session4.setScheduleItemType(ScheduleItemType.SESSION);
		block4Session4.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 16, 00, 0).getTime());
		block4Session4.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 17, 15, 0).getTime());
		block4Session4.setCreatedDate(new Date());
		block4Session4.setRoom(ballroomE);
		block4Session4.setTitle("TBD");
		block4Session4.setEvent(event);

		ScheduleItem block4Session5 = new ScheduleItem();
		block4Session5.setScheduleItemType(ScheduleItemType.SESSION);
		block4Session5.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 16, 00, 0).getTime());
		block4Session5.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 17, 15, 0).getTime());
		block4Session5.setCreatedDate(new Date());
		block4Session5.setRoom(room103);
		block4Session5.setTitle("TBD");
		block4Session5.setEvent(event);

		ScheduleItem block4Session6 = new ScheduleItem();
		block4Session6.setScheduleItemType(ScheduleItemType.SESSION);
		block4Session6.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 16, 00, 0).getTime());
		block4Session6.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 17, 15, 0).getTime());
		block4Session6.setCreatedDate(new Date());
		block4Session6.setRoom(room104);
		block4Session6.setTitle("TBD");
		block4Session6.setEvent(event);

		ScheduleItem block4Session7 = new ScheduleItem();
		block4Session7.setScheduleItemType(ScheduleItemType.SESSION);
		block4Session7.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 16, 00, 0).getTime());
		block4Session7.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 17, 15, 0).getTime());
		block4Session7.setCreatedDate(new Date());
		block4Session7.setRoom(room105);
		block4Session7.setTitle("TBD");
		block4Session7.setEvent(event);

		scheduleItemDao.save(block4Session1);
		scheduleItemDao.save(block4Session2);
		scheduleItemDao.save(block4Session3);
		scheduleItemDao.save(block4Session4);
		scheduleItemDao.save(block4Session5);
		scheduleItemDao.save(block4Session6);
		scheduleItemDao.save(block4Session7);

		final ScheduleItem block4Break = new ScheduleItem();
		block4Break.setScheduleItemType(ScheduleItemType.BREAK);
		block4Break.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 17, 15, 0).getTime());
		block4Break.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 17, 30, 0).getTime());
		block4Break.setCreatedDate(new Date());
		block4Break.setTitle("Break");
		block4Break.setRoom(atrium);
		block4Break.setEvent(event);

		scheduleItemDao.save(block4Break);

		ScheduleItem keynote2 = new ScheduleItem();
		keynote2.setScheduleItemType(ScheduleItemType.KEYNOTE);
		keynote2.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 17, 30, 0).getTime());
		keynote2.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 18, 45, 0).getTime());
		keynote2.setCreatedDate(new Date());
		keynote2.setTitle("TBD");
		keynote2.setRoom(ballroomCDF);
		keynote2.setEvent(event);

		scheduleItemDao.save(keynote2);

		ScheduleItem reception = new ScheduleItem();
		reception.setScheduleItemType(ScheduleItemType.ADMINISTRATIVE);
		reception.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 18, 18, 45, 0).getTime());
		reception.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 18, 20, 00, 0).getTime());
		reception.setCreatedDate(new Date());
		reception.setTitle("Cocktail Reception [Jocks and Jills Sports Bar]");
		//reception.setRoom(ballroomCDF);
		reception.setEvent(event);

		scheduleItemDao.save(reception);

		// Day 2

		ScheduleItem day1ScheduleItem3 = new ScheduleItem();
		day1ScheduleItem3.setScheduleItemType(ScheduleItemType.KEYNOTE);
		day1ScheduleItem3.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19,  9, 15, 0).getTime());
		day1ScheduleItem3.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 10, 15, 0).getTime());
		day1ScheduleItem3.setCreatedDate(new Date());
		day1ScheduleItem3.setTitle("TBD");
		day1ScheduleItem3.setRoom(ballroomCDF);
		day1ScheduleItem3.setEvent(event);

		scheduleItemDao.save(day1ScheduleItem3);

		ScheduleItem day1ScheduleItem4 = new ScheduleItem();
		day1ScheduleItem4.setScheduleItemType(ScheduleItemType.BREAK);
		day1ScheduleItem4.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 10, 15, 0).getTime());
		day1ScheduleItem4.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 10, 30, 0).getTime());
		day1ScheduleItem4.setCreatedDate(new Date());
		day1ScheduleItem4.setTitle("Break");
		day1ScheduleItem4.setRoom(atrium);
		day1ScheduleItem4.setEvent(event);

		scheduleItemDao.save(day1ScheduleItem4);

		//Sessions

		// Block 1

		ScheduleItem day1Block1Session1 = new ScheduleItem();
		day1Block1Session1.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block1Session1.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 10, 30, 0).getTime());
		day1Block1Session1.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 11, 45, 0).getTime());
		day1Block1Session1.setCreatedDate(new Date());
		day1Block1Session1.setRoom(ballroomA);
		day1Block1Session1.setTitle("TBD");
		day1Block1Session1.setEvent(event);

		ScheduleItem day1Block1Session2 = new ScheduleItem();
		day1Block1Session2.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block1Session2.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 10, 30, 0).getTime());
		day1Block1Session2.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 11, 45, 0).getTime());
		day1Block1Session2.setCreatedDate(new Date());
		day1Block1Session2.setRoom(ballroomB);
		day1Block1Session2.setTitle("TBD");
		day1Block1Session2.setEvent(event);

		ScheduleItem day1Block1Session3 = new ScheduleItem();
		day1Block1Session3.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block1Session3.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 10, 30, 0).getTime());
		day1Block1Session3.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 11, 45, 0).getTime());
		day1Block1Session3.setCreatedDate(new Date());
		day1Block1Session3.setRoom(ballroomCDF);
		day1Block1Session3.setTitle("TBD");
		day1Block1Session3.setEvent(event);

		ScheduleItem day1Block1Session4 = new ScheduleItem();
		day1Block1Session4.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block1Session4.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 10, 30, 0).getTime());
		day1Block1Session4.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 11, 45, 0).getTime());
		day1Block1Session4.setCreatedDate(new Date());
		day1Block1Session4.setRoom(ballroomE);
		day1Block1Session4.setTitle("TBD");
		day1Block1Session4.setEvent(event);

		ScheduleItem day1Block1Session5 = new ScheduleItem();
		day1Block1Session5.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block1Session5.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 10, 30, 0).getTime());
		day1Block1Session5.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 189, 11, 45, 0).getTime());
		day1Block1Session5.setCreatedDate(new Date());
		day1Block1Session5.setRoom(room103);
		day1Block1Session5.setTitle("TBD");
		day1Block1Session5.setEvent(event);

		ScheduleItem day1Block1Session6 = new ScheduleItem();
		day1Block1Session6.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block1Session6.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 10, 30, 0).getTime());
		day1Block1Session6.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 11, 45, 0).getTime());
		day1Block1Session6.setCreatedDate(new Date());
		day1Block1Session6.setRoom(room104);
		day1Block1Session6.setTitle("TBD");
		day1Block1Session6.setEvent(event);

		ScheduleItem day1Block1Session7 = new ScheduleItem();
		day1Block1Session7.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block1Session7.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 10, 30, 0).getTime());
		day1Block1Session7.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 11, 45, 0).getTime());
		day1Block1Session7.setCreatedDate(new Date());
		day1Block1Session7.setRoom(room105);
		day1Block1Session7.setTitle("TBD");
		day1Block1Session7.setEvent(event);

		scheduleItemDao.save(day1Block1Session1);
		scheduleItemDao.save(day1Block1Session2);
		scheduleItemDao.save(day1Block1Session3);
		scheduleItemDao.save(day1Block1Session4);
		scheduleItemDao.save(day1Block1Session5);
		scheduleItemDao.save(day1Block1Session6);
		scheduleItemDao.save(day1Block1Session7);

		ScheduleItem day1Block1Lunch = new ScheduleItem();
		day1Block1Lunch.setScheduleItemType(ScheduleItemType.BREAK);
		day1Block1Lunch.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 11, 45, 0).getTime());
		day1Block1Lunch.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 12, 30, 0).getTime());
		day1Block1Lunch.setCreatedDate(new Date());
		day1Block1Lunch.setTitle("Lunch");
		day1Block1Lunch.setRoom(hallA);
		day1Block1Lunch.setEvent(event);

		scheduleItemDao.save(day1Block1Lunch);

		ScheduleItem day1Block1Dessert = new ScheduleItem();
		day1Block1Dessert.setScheduleItemType(ScheduleItemType.BREAK);
		day1Block1Dessert.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 12, 30, 0).getTime());
		day1Block1Dessert.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 13, 00, 0).getTime());
		day1Block1Dessert.setCreatedDate(new Date());
		day1Block1Dessert.setTitle("Dessert");
		day1Block1Dessert.setRoom(atrium);
		day1Block1Dessert.setEvent(event);

		scheduleItemDao.save(day1Block1Dessert);

		// Block 2

		ScheduleItem day1Block2Session1 = new ScheduleItem();
		day1Block2Session1.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block2Session1.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 13, 00, 0).getTime());
		day1Block2Session1.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 14, 15, 0).getTime());
		day1Block2Session1.setCreatedDate(new Date());
		day1Block2Session1.setRoom(ballroomA);
		day1Block2Session1.setTitle("TBD");
		day1Block2Session1.setEvent(event);

		ScheduleItem day1Block2Session2 = new ScheduleItem();
		day1Block2Session2.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block2Session2.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 13, 00, 0).getTime());
		day1Block2Session2.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 14, 15, 0).getTime());
		day1Block2Session2.setCreatedDate(new Date());
		day1Block2Session2.setRoom(ballroomB);
		day1Block2Session2.setTitle("TBD");
		day1Block2Session2.setEvent(event);

		ScheduleItem day1Block2Session3 = new ScheduleItem();
		day1Block2Session3.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block2Session3.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 13, 00, 0).getTime());
		day1Block2Session3.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 14, 15, 0).getTime());
		day1Block2Session3.setCreatedDate(new Date());
		day1Block2Session3.setRoom(ballroomCDF);
		day1Block2Session3.setTitle("TBD");
		day1Block2Session3.setEvent(event);

		ScheduleItem day1Block2Session4 = new ScheduleItem();
		day1Block2Session4.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block2Session4.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 13, 00, 0).getTime());
		day1Block2Session4.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 14, 15, 0).getTime());
		day1Block2Session4.setCreatedDate(new Date());
		day1Block2Session4.setRoom(ballroomE);
		day1Block2Session4.setTitle("TBD");
		day1Block2Session4.setEvent(event);

		ScheduleItem day1Block2Session5 = new ScheduleItem();
		day1Block2Session5.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block2Session5.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 13, 00, 0).getTime());
		day1Block2Session5.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 14, 15, 0).getTime());
		day1Block2Session5.setCreatedDate(new Date());
		day1Block2Session5.setRoom(room103);
		day1Block2Session5.setTitle("TBD");
		day1Block2Session5.setEvent(event);

		ScheduleItem day1Block2Session6 = new ScheduleItem();
		day1Block2Session6.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block2Session6.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 13, 00, 0).getTime());
		day1Block2Session6.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 14, 15, 0).getTime());
		day1Block2Session6.setCreatedDate(new Date());
		day1Block2Session6.setRoom(room104);
		day1Block2Session6.setTitle("TBD");
		day1Block2Session6.setEvent(event);

		ScheduleItem day1Block2Session7 = new ScheduleItem();
		day1Block2Session7.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block2Session7.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 13, 00, 0).getTime());
		day1Block2Session7.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 14, 15, 0).getTime());
		day1Block2Session7.setCreatedDate(new Date());
		day1Block2Session7.setRoom(room105);
		day1Block2Session7.setTitle("TBD");
		day1Block2Session7.setEvent(event);

		scheduleItemDao.save(day1Block2Session1);
		scheduleItemDao.save(day1Block2Session2);
		scheduleItemDao.save(day1Block2Session3);
		scheduleItemDao.save(day1Block2Session4);
		scheduleItemDao.save(day1Block2Session5);
		scheduleItemDao.save(day1Block2Session6);
		scheduleItemDao.save(day1Block2Session7);

		ScheduleItem day1Block2Break = new ScheduleItem();
		day1Block2Break.setScheduleItemType(ScheduleItemType.BREAK);
		day1Block2Break.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 14, 15, 0).getTime());
		day1Block2Break.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 14, 30, 0).getTime());
		day1Block2Break.setCreatedDate(new Date());
		day1Block2Break.setTitle("Break");
		day1Block2Break.setRoom(atrium);
		day1Block2Break.setEvent(event);

		scheduleItemDao.save(day1Block2Break);

		// Block 3

		ScheduleItem day1Block3Session1 = new ScheduleItem();
		day1Block3Session1.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block3Session1.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 14, 30, 0).getTime());
		day1Block3Session1.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 15, 45, 0).getTime());
		day1Block3Session1.setCreatedDate(new Date());
		day1Block3Session1.setRoom(ballroomA);
		day1Block3Session1.setTitle("TBD");
		day1Block3Session1.setEvent(event);

		ScheduleItem day1Block3Session2 = new ScheduleItem();
		day1Block3Session2.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block3Session2.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 14, 30, 0).getTime());
		day1Block3Session2.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 15, 45, 0).getTime());
		day1Block3Session2.setCreatedDate(new Date());
		day1Block3Session2.setRoom(ballroomB);
		day1Block3Session2.setTitle("TBD");
		day1Block3Session2.setEvent(event);

		ScheduleItem day1Block3Session3 = new ScheduleItem();
		day1Block3Session3.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block3Session3.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 14, 30, 0).getTime());
		day1Block3Session3.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 15, 45, 0).getTime());
		day1Block3Session3.setCreatedDate(new Date());
		day1Block3Session3.setRoom(ballroomCDF);
		day1Block3Session3.setTitle("TBD");
		day1Block3Session3.setEvent(event);

		ScheduleItem day1Block3Session4 = new ScheduleItem();
		day1Block3Session4.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block3Session4.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 14, 30, 0).getTime());
		day1Block3Session4.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 15, 45, 0).getTime());
		day1Block3Session4.setCreatedDate(new Date());
		day1Block3Session4.setRoom(ballroomE);
		day1Block3Session4.setTitle("TBD");
		day1Block3Session4.setEvent(event);

		ScheduleItem day1Block3Session5 = new ScheduleItem();
		day1Block3Session5.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block3Session5.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 14, 30, 0).getTime());
		day1Block3Session5.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 15, 45, 0).getTime());
		day1Block3Session5.setCreatedDate(new Date());
		day1Block3Session5.setRoom(room103);
		day1Block3Session5.setTitle("TBD");
		day1Block3Session5.setEvent(event);

		ScheduleItem day1Block3Session6 = new ScheduleItem();
		day1Block3Session6.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block3Session6.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 14, 30, 0).getTime());
		day1Block3Session6.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 15, 45, 0).getTime());
		day1Block3Session6.setCreatedDate(new Date());
		day1Block3Session6.setRoom(room104);
		day1Block3Session6.setTitle("TBD");
		day1Block3Session6.setEvent(event);

		ScheduleItem day1Block3Session7 = new ScheduleItem();
		day1Block3Session7.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block3Session7.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 14, 30, 0).getTime());
		day1Block3Session7.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 15, 45, 0).getTime());
		day1Block3Session7.setCreatedDate(new Date());
		day1Block3Session7.setRoom(room105);
		day1Block3Session7.setTitle("TBD");
		day1Block3Session7.setEvent(event);

		scheduleItemDao.save(day1Block3Session1);
		scheduleItemDao.save(day1Block3Session2);
		scheduleItemDao.save(day1Block3Session3);
		scheduleItemDao.save(day1Block3Session4);
		scheduleItemDao.save(day1Block3Session5);
		scheduleItemDao.save(day1Block3Session6);
		scheduleItemDao.save(day1Block3Session7);

		final ScheduleItem day1Block3Break = new ScheduleItem();
		day1Block3Break.setScheduleItemType(ScheduleItemType.BREAK);
		day1Block3Break.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 15, 45, 0).getTime());
		day1Block3Break.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 16, 00, 0).getTime());
		day1Block3Break.setCreatedDate(new Date());
		day1Block3Break.setTitle("Break");
		day1Block3Break.setRoom(atrium);
		day1Block3Break.setEvent(event);

		scheduleItemDao.save(day1Block3Break);

		// Block 4

		ScheduleItem day1Block4Session1 = new ScheduleItem();
		day1Block4Session1.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block4Session1.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 16, 00, 0).getTime());
		day1Block4Session1.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 17, 15, 0).getTime());
		day1Block4Session1.setCreatedDate(new Date());
		day1Block4Session1.setRoom(ballroomA);
		day1Block4Session1.setTitle("TBD");
		day1Block4Session1.setEvent(event);

		ScheduleItem day1Block4Session2 = new ScheduleItem();
		day1Block4Session2.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block4Session2.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 16, 00, 0).getTime());
		day1Block4Session2.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 17, 15, 0).getTime());
		day1Block4Session2.setCreatedDate(new Date());
		day1Block4Session2.setRoom(ballroomB);
		day1Block4Session2.setTitle("TBD");
		day1Block4Session2.setEvent(event);

		ScheduleItem day1Bblock4Session3 = new ScheduleItem();
		day1Bblock4Session3.setScheduleItemType(ScheduleItemType.SESSION);
		day1Bblock4Session3.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 16, 00, 0).getTime());
		day1Bblock4Session3.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 17, 15, 0).getTime());
		day1Bblock4Session3.setCreatedDate(new Date());
		day1Bblock4Session3.setRoom(ballroomCDF);
		day1Bblock4Session3.setTitle("TBD");
		day1Bblock4Session3.setEvent(event);

		ScheduleItem day1Block4Session4 = new ScheduleItem();
		day1Block4Session4.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block4Session4.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 16, 00, 0).getTime());
		day1Block4Session4.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 17, 15, 0).getTime());
		day1Block4Session4.setCreatedDate(new Date());
		day1Block4Session4.setRoom(ballroomE);
		day1Block4Session4.setTitle("TBD");
		day1Block4Session4.setEvent(event);

		ScheduleItem day1Block4Session5 = new ScheduleItem();
		day1Block4Session5.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block4Session5.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 16, 00, 0).getTime());
		day1Block4Session5.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 17, 15, 0).getTime());
		day1Block4Session5.setCreatedDate(new Date());
		day1Block4Session5.setRoom(room103);
		day1Block4Session5.setTitle("TBD");
		day1Block4Session5.setEvent(event);

		ScheduleItem day1Block4Session6 = new ScheduleItem();
		day1Block4Session6.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block4Session6.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 16, 00, 0).getTime());
		day1Block4Session6.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 17, 15, 0).getTime());
		day1Block4Session6.setCreatedDate(new Date());
		day1Block4Session6.setRoom(room104);
		day1Block4Session6.setTitle("TBD");
		day1Block4Session6.setEvent(event);

		ScheduleItem day1Block4Session7 = new ScheduleItem();
		day1Block4Session7.setScheduleItemType(ScheduleItemType.SESSION);
		day1Block4Session7.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 16, 00, 0).getTime());
		day1Block4Session7.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 17, 15, 0).getTime());
		day1Block4Session7.setCreatedDate(new Date());
		day1Block4Session7.setRoom(room105);
		day1Block4Session7.setTitle("TBD");
		day1Block4Session7.setEvent(event);

		scheduleItemDao.save(day1Block4Session1);
		scheduleItemDao.save(day1Block4Session2);
		scheduleItemDao.save(day1Bblock4Session3);
		scheduleItemDao.save(day1Block4Session4);
		scheduleItemDao.save(day1Block4Session5);
		scheduleItemDao.save(day1Block4Session6);
		scheduleItemDao.save(day1Block4Session7);

		ScheduleItem scheduleItemClosing = new ScheduleItem();
		scheduleItemClosing.setScheduleItemType(ScheduleItemType.ADMINISTRATIVE);
		scheduleItemClosing.setFromTime(CalendarUtils.getCalendar(currentYear, 1, 19, 17, 15, 0).getTime());
		scheduleItemClosing.setToTime(CalendarUtils.getCalendar(  currentYear, 1, 19, 17, 45, 0).getTime());
		scheduleItemClosing.setCreatedDate(new Date());
		scheduleItemClosing.setTitle("Closing Ceremonies");
		scheduleItemClosing.setRoom(ballroomCDF);
		scheduleItemClosing.setEvent(event);

		scheduleItemDao.save(scheduleItemClosing);
	}

}
