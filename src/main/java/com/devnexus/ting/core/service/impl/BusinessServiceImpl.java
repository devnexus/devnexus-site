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

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.devnexus.ting.common.CalendarUtils;
import com.devnexus.ting.common.SystemInformationUtils;
import com.devnexus.ting.config.support.MailSettings;
import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.model.ApplicationCache;
import com.devnexus.ting.model.CfpSubmission;
import com.devnexus.ting.model.Dashboard;
import com.devnexus.ting.model.Evaluation;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.EventSignup;
import com.devnexus.ting.model.FileData;
import com.devnexus.ting.model.Organizer;
import com.devnexus.ting.model.PayPalPayment;
import com.devnexus.ting.model.Presentation;
import com.devnexus.ting.model.PresentationTag;
import com.devnexus.ting.model.RegistrationDetails;
import com.devnexus.ting.model.Room;
import com.devnexus.ting.model.ScheduleItem;
import com.devnexus.ting.model.ScheduleItemList;
import com.devnexus.ting.model.ScheduleItemType;
import com.devnexus.ting.model.Speaker;
import com.devnexus.ting.model.Sponsor;
import com.devnexus.ting.model.SponsorLevel;
import com.devnexus.ting.model.SponsorList;
import com.devnexus.ting.model.TicketAddOn;
import com.devnexus.ting.model.TicketGroup;
import com.devnexus.ting.model.TicketOrderDetail;
import com.devnexus.ting.model.Track;
import com.devnexus.ting.model.support.PresentationSearchQuery;
import com.devnexus.ting.repository.ApplicationCacheRepository;
import com.devnexus.ting.repository.CfpSubmissionRepository;
import com.devnexus.ting.repository.EvaluationRepository;
import com.devnexus.ting.repository.EventRepository;
import com.devnexus.ting.repository.EventSignupRepository;
import com.devnexus.ting.repository.OrganizerRepository;
import com.devnexus.ting.repository.PayPalRepository;
import com.devnexus.ting.repository.PresentationRepository;
import com.devnexus.ting.repository.PresentationTagRepository;
import com.devnexus.ting.repository.RegistrationRepository;
import com.devnexus.ting.repository.RoomRepository;
import com.devnexus.ting.repository.ScheduleItemRepository;
import com.devnexus.ting.repository.SpeakerRepository;
import com.devnexus.ting.repository.SponsorRepository;
import com.devnexus.ting.repository.TicketAddonRepository;
import com.devnexus.ting.repository.TicketGroupRepository;
import com.devnexus.ting.repository.TrackRepository;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Gunnar Hillert
 * @since 1.0
 */
@Service("businessService")
public class BusinessServiceImpl implements BusinessService {

	/**
	 *   Initialize Logging.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessServiceImpl.class);

	@Autowired private CfpSubmissionRepository cfpSubmissionRepository;
	@Autowired private EvaluationRepository   evaluationDao;
	@Autowired private EventRepository        eventDao;
	@Autowired private RegistrationRepository registrationDao;
        @Autowired private PayPalRepository       payPalDao;
	@Autowired private EventSignupRepository  eventSignupDao;
	@Autowired private TicketGroupRepository  ticketGroupDao;
	@Autowired private OrganizerRepository    organizerDao;
	@Autowired private PresentationRepository presentationDao;
	@Autowired private PresentationTagRepository presentationTagDao;
	@Autowired private RoomRepository         roomDao;
	@Autowired private ScheduleItemRepository scheduleItemDao;
	@Autowired private SpeakerRepository      speakerDao;
	@Autowired private SponsorRepository      sponsorDao;
	@Autowired private TrackRepository        trackDao;
        @Autowired private TicketAddonRepository      ticketAddOnDao;
	@Autowired private ApplicationCacheRepository applicationCacheDao;
	@Autowired private Environment environment;

	@Autowired private MessageChannel mailChannel;
	@Autowired private MessageChannel registrationMailChannel;

	private final TransactionTemplate transactionTemplate;

	@Autowired
	private MailSettings mailSettings;

	@Autowired
	public BusinessServiceImpl(PlatformTransactionManager transactionManager) {
		super();
		Assert.notNull(transactionManager, "The 'transactionManager' argument must not be null.");
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	/** {@inheritDoc} */
	@Override
	@Transactional
	@CacheEvict(value={"getCurrentEvent", "getAllNonCurrentEvents"}, allEntries=true)
	public void deleteEvent(Event event) {
		Assert.notNull(event, "The provided event must not be null.");
		Assert.notNull(event.getId(), "Id must not be Null for event " + event);

		LOGGER.debug("Deleting Event {}", event);
		eventDao.delete(event);
	}

	/** {@inheritDoc} */
	@Override
	@Transactional
	public void deleteOrganizer(Organizer organizerFromDb) {

		Assert.notNull(organizerFromDb,         "The provided organizer must not be null.");
		Assert.notNull(organizerFromDb.getId(), "Id must not be Null for organizer " + organizerFromDb);

		LOGGER.debug("Deleting Organizer {}", organizerFromDb);
		organizerDao.delete(organizerFromDb);
	}

	@CacheEvict(value="sponsors", allEntries=true)
	@Override
	@Transactional
	public void deleteSponsor(Sponsor sponsorFromDb) {
		Assert.notNull(sponsorFromDb,         "The provided sponsor must not be null.");
		Assert.notNull(sponsorFromDb.getId(), "Id must not be Null for sponsor " + sponsorFromDb);

		LOGGER.debug("Deleting Sponsor {}", sponsorFromDb);
		sponsorDao.delete(sponsorFromDb);
	}

	/** {@inheritDoc} */
	@Override
	@Transactional
	public void deletePresentation(Presentation presentation) {

		Assert.notNull(presentation,         "The provided presentation must not be null.");
		Assert.notNull(presentation.getId(), "Id must not be Null for presentation " + presentation);

		LOGGER.debug("Deleting Presentation {}", presentation);

		presentationDao.delete(presentation);

	}

	/** {@inheritDoc} */
	@Override
	@Transactional
	public void deleteSpeaker(Speaker speaker) {

		Assert.notNull(speaker,         "The provided speaker must not be null.");
		Assert.notNull(speaker.getId(), "Id must not be Null for speaker " + speaker);

		LOGGER.debug("Deleting Speaker {}", speaker);

		speakerDao.delete(speaker);
	}

	/** {@inheritDoc} */
	@Override
	public List<Event> getAllEventsOrderedByName() {
		return eventDao.getAllEventsOrderedByName();
	}

	/** {@inheritDoc} */
	@Override
	@Cacheable("getAllNonCurrentEvents")
	public List<Event> getAllNonCurrentEvents() {
		return eventDao.getAllNonCurrentEvents();
	}

	/** {@inheritDoc} */
	@Override
	public List<Organizer> getAllOrganizers() {
		return organizerDao.getAllOrganizers();
	}

	/** {@inheritDoc} */
	@Override
	public List<Presentation> getAllPresentations() {
		return presentationDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<Speaker> getAllSpeakersOrderedByName() {
		return speakerDao.getAllSpeakersOrderedByName();
	}

	/** {@inheritDoc} */
	@Override
	public Event getEvent(Long id) {
		return eventDao.findOne(id);
	}

	/** {@inheritDoc} */
	@Override
	public Event getEventByEventKey(String eventKey) {
		return eventDao.getByEventKey(eventKey);
	}

	/** {@inheritDoc} */
	@Override
	public Organizer getOrganizer(final Long organizerId) {
		return organizerDao.getOne(organizerId);
	}

	@Override
	public Sponsor getSponsor(Long sponsorId) {
		return sponsorDao.getOne(sponsorId);
	}

	/** {@inheritDoc} */
	@Override
	@Transactional
	public Organizer getOrganizerWithPicture(Long organizerId) {
		return organizerDao.getOrganizerWithPicture(organizerId);
	}

	@Override
	public Sponsor getSponsorWithPicture(final Long sponsorId) {

		final Sponsor sponsor = transactionTemplate.execute(new TransactionCallback<Sponsor>() {
			public Sponsor doInTransaction(TransactionStatus status) {
				return sponsorDao.getSponsorWithPicture(sponsorId);
			}
		});

		return sponsor;
	}

	@Override
	@Transactional
	public List<Organizer> getAllOrganizersWithPicture() {
		return organizerDao.getOrganizersWithPicture();
	}

	/** {@inheritDoc} */
	@Override
	@Transactional(readOnly=false)
	public Presentation getPresentation(Long id) {
		return presentationDao.getOne(id);
	}

	/** {@inheritDoc} */
	@Override
	public List<Presentation> getPresentationsForCurrentEvent() {
		List<Presentation> list = presentationDao.getPresentationsForCurrentEvent();
		Collections.sort(list);
		return list;
	}

	/** {@inheritDoc} */
	@Override
	public List<Presentation> getPresentationsForEventOrderedByName(Long eventId) {
		List<Presentation> list = presentationDao.getPresentationsForEventOrderedByName(eventId);
		return list;
	}

	@Override
	public List<Presentation> getPresentationsForEventOrderedByTrack(Long eventId) {
		List<Presentation> list = presentationDao.getPresentationsForEventOrderedByTrack(eventId);
		return list;
	}

	@Override
	public List<Presentation> getPresentationsForEventOrderedByRoom(Long eventId) {
		List<Presentation> list = presentationDao.getPresentationsForEventOrderedByRoom(eventId);
		return list;
	}

	/** {@inheritDoc} */
	@Override
	public Speaker getSpeaker(Long speakerId) {
		return speakerDao.getOne(speakerId);
	}

	/** {@inheritDoc} */
	@Override
	@Transactional(readOnly=false)
	public byte[] getSpeakerImage(Long speakerId) {

		Assert.notNull(speakerId, "SpeakerId must not be null.");

		final Speaker speaker = getSpeaker(speakerId);

		final byte[] speakerPicture;

		if (speaker==null || speaker.getPicture() == null) {
			speakerPicture = SystemInformationUtils.getSpeakerImage(null);
		} else {
			speakerPicture = speaker.getPicture().getFileData();
		}

		return speakerPicture;

	}

	/** {@inheritDoc} */
	@Override
	public List<Speaker> getSpeakersForCurrentEvent() {
		return speakerDao.getSpeakersForCurrentEvent();
	}

	/** {@inheritDoc} */
	@Override
	public List<Speaker> getSpeakersForEvent(Long eventId) {
		return speakerDao.getSpeakersForEvent(eventId);
	}

	@Override
	public List<Room> getRoomsForEvent(Long eventId) {
		return roomDao.getRoomsForEvent(eventId);
	}

	@Override
	public List<Track> getTracksForEvent(Long eventId) {
		return trackDao.getTracksForEvent(eventId);
	}

	/** {@inheritDoc} */
	@Override
	@Transactional
	@CacheEvict(value={"getCurrentEvent", "getAllNonCurrentEvents"}, allEntries=true)
	public void saveEvent(Event event) {
		eventDao.save(event);
	}

	/** {@inheritDoc} */
	@Override
	@Transactional
	public Organizer saveOrganizer(Organizer organizer) {
		return organizerDao.save(organizer);
	}

	@Override
	@Transactional
	@CacheEvict(value="sponsors", allEntries=true)
	public Sponsor saveSponsor(Sponsor sponsor) {
		return sponsorDao.save(sponsor);
	}

	/** {@inheritDoc} */
	@Override
	@Transactional
	public Presentation savePresentation(Presentation presentation) {
		return presentationDao.save(presentation);
	}

	/** {@inheritDoc} */
	@Override
	@Transactional
	public Speaker saveSpeaker(Speaker speaker) {
		return speakerDao.save(speaker);
	}

	/** {@inheritDoc} */
	@Override
	@Transactional
	public ApplicationCache updateApplicationCacheManifest() {

		final List<ApplicationCache> applicationCacheList = applicationCacheDao.findAll();

		if (applicationCacheList.isEmpty()) {
			ApplicationCache applicationCache = new ApplicationCache();
			applicationCache.setUpdatedDate(new Date());
			applicationCache.setUuid(UUID.randomUUID().toString());
			ApplicationCache savedApplicationCache = applicationCacheDao.save(applicationCache);
			return savedApplicationCache;
		} else if (applicationCacheList.size() >1) {
			throw new IllegalStateException("ApplicationCacheList should only contain 1 elements but found " + applicationCacheList.size());
		} else {
			ApplicationCache applicationCache = applicationCacheList.iterator().next();
			applicationCache.setUpdatedDate(new Date());
			applicationCache.setUuid(UUID.randomUUID().toString());
			return applicationCacheDao.save(applicationCache);
		}

	}

	@Override
	@Transactional
	public ApplicationCache getApplicationCacheManifest() {
		final List<ApplicationCache> applicationCacheList = applicationCacheDao.findAll();

		if (applicationCacheList.isEmpty()) {
			ApplicationCache applicationCache = new ApplicationCache();
			applicationCache.setUpdatedDate(new Date());
			applicationCache.setUuid(UUID.randomUUID().toString());
			ApplicationCache savedApplicationCache = applicationCacheDao.save(applicationCache);
			return savedApplicationCache;
		} else if (applicationCacheList.size() >1) {
			throw new IllegalStateException("ApplicationCacheList should only contain 1 elements but found " + applicationCacheList.size());
		} else {
			return applicationCacheList.iterator().next();
		}
	}

	@Override
	public FileData getPresentationFileData(final Long presentationId) {

		final Presentation presentation = transactionTemplate.execute(new TransactionCallback<Presentation>() {
			public Presentation doInTransaction(TransactionStatus status) {
				return presentationDao.getOneWithSlide(presentationId);
			}
		});

		if (presentation == null) {
			return null;
		}

		FileData fileData = presentation.getPresentationFile();
		return fileData;
	}

	@Override
	@Cacheable("getCurrentEvent")
	public Event getCurrentEvent() {
		return eventDao.getCurrentEvent();
	}

	@Override
	public Room getRoom(Long id) {
		return roomDao.getOne(id);
	}

	@Override
	public ScheduleItemList getScheduleForEvent(Long eventId) {

		final List<ScheduleItem> scheduleItems = scheduleItemDao.getScheduleForEvent(eventId);

		final ScheduleItemList scheduleItemList = new ScheduleItemList();
		scheduleItemList.setScheduleItems(scheduleItems);

		ScheduleItem currentScheduleItem = null;

		String hourOfDay = null;

		final SortedSet<Date> days = new TreeSet<Date>();

		int numberOfSessions = 0;
		int numberOfKeynoteSessions = 0;
		int numberOfBreakoutSessions = 0;
		int numberOfUnassignedSessions = 0;

		int numberOfBreaks = 0;

		Set<Long> speakerIds = new HashSet<Long>();
		Set<Long> roomIds = new HashSet<Long>();

		for (ScheduleItem scheduleItem : scheduleItems) {

			roomIds.add(scheduleItem.getRoom().getId());

			final Date fromTime = scheduleItem.getFromTime();
			final Date dayOfConference = CalendarUtils.getCalendarWithoutTime(fromTime).getTime();
			days.add(dayOfConference);

			if (ScheduleItemType.KEYNOTE.equals(scheduleItem.getScheduleItemType())
					|| ScheduleItemType.SESSION.equals(scheduleItem.getScheduleItemType())) {

				numberOfSessions++;

				if (scheduleItem.getPresentation() != null) {
					for (Speaker speaker : scheduleItem.getPresentation().getSpeakers()) {
						speakerIds.add(speaker.getId());
					}
				} else {
					numberOfUnassignedSessions++;
				}

				if (ScheduleItemType.KEYNOTE.equals(scheduleItem.getScheduleItemType())) {
					numberOfKeynoteSessions++;

				}

				if (ScheduleItemType.SESSION.equals(scheduleItem.getScheduleItemType())) {
					numberOfBreakoutSessions++;
				}

			}

			if (ScheduleItemType.BREAK.equals(scheduleItem.getScheduleItemType())) {
				numberOfBreaks++;
			}

			Calendar cal = Calendar.getInstance();
			cal.setTime(fromTime);

			String loopHour = cal.get(Calendar.HOUR_OF_DAY) + "_" + cal.get(Calendar.MINUTE);

			if (hourOfDay == null || !hourOfDay.equals(loopHour)) {
				currentScheduleItem = scheduleItem;
				hourOfDay = loopHour;
			} else {
				currentScheduleItem.setRowspan(currentScheduleItem.getRowspan() + 1);
			}

		}

		scheduleItemList.setDays(days);
		scheduleItemList.setNumberOfBreakoutSessions(numberOfBreakoutSessions);
		scheduleItemList.setNumberOfBreaks(numberOfBreaks);
		scheduleItemList.setNumberOfSessions(numberOfSessions);
		scheduleItemList.setNumberOfKeynoteSessions(numberOfKeynoteSessions);
		scheduleItemList.setNumberOfUnassignedSessions(numberOfUnassignedSessions);
		scheduleItemList.setNumberOfSpeakersAssigned(speakerIds.size());
		scheduleItemList.setNumberOfRooms(roomIds.size());
		return scheduleItemList;
	}

	@Override
	@Transactional
	public Evaluation saveEvaluation(Evaluation evaluation) {
		return evaluationDao.save(evaluation);
	}

	@Override
	public List<Evaluation> getEvaluationsForCurrentEvent() {
		return evaluationDao.getEvaluationsForCurrentEvent();
	}

	@Override
	public List<Evaluation> getEvaluationsForEvent(Long eventId) {
		return evaluationDao.getEvaluationsForEvent(eventId);
	}

	@Override
	public void removeEvaluation(Long evaluationId) {
		evaluationDao.delete(evaluationId);
	}

	@Override
	public List<CfpSubmission> getCfpSubmissions(Long eventId) {
		return cfpSubmissionRepository.getCfpSubmissions(eventId);
	}

	@Override
	@Transactional
	public CfpSubmission saveCfpSubmission(CfpSubmission cfpSubmission) {
		return cfpSubmissionRepository.save(cfpSubmission);
	}

	@Override
	public CfpSubmission saveAndNotifyCfpSubmission(final CfpSubmission cfpSubmission) {
		final BusinessService businessService = this;
		final CfpSubmission savedCfpSubmission = transactionTemplate.execute(new TransactionCallback<CfpSubmission>() {
			public CfpSubmission doInTransaction(TransactionStatus status) {
				return businessService.saveCfpSubmission(cfpSubmission);
			}
		});

		if (mailSettings.isEnabled()) {
			mailChannel.send(MessageBuilder.withPayload(cfpSubmission).build());
		}

		return savedCfpSubmission;
	}

	@Override
	public CfpSubmission getCfpSubmission(Long cfpId) {
		return this.cfpSubmissionRepository.getOne(cfpId);
	}

	@Override
	public Track getTrack(Long id) {
		return trackDao.getOne(id);
	}

	@Override
	public PresentationTag getPresentationTag(String tagName) {
		return presentationTagDao.getPresentationTag(tagName);
	}

	@Override
	public PresentationTag savePresentationTag(PresentationTag presentationTag) {
		return presentationTagDao.save(presentationTag);
	}

	@Override
	public Map<PresentationTag, Long> getTagCloud(Long eventId) {
		return presentationTagDao.getPresentationTagCountForEvent(eventId);
	}

	@Override
	public List<Presentation> findPresentations(
			PresentationSearchQuery presentationSearchQuery) {
		return presentationDao.findPresentations(presentationSearchQuery);
	}

	@Transactional
	@Override
	public void deleteCfpSubmission(Long id) {
		cfpSubmissionRepository.delete(id);
	}

	@Transactional
	@Override
	public Set<PresentationTag> processPresentationTags(String tagsAsText) {

		final Set<PresentationTag> presentationTagsToSave = new HashSet<>();

		if (!tagsAsText.trim().isEmpty()) {
			Set<String> tags = StringUtils.commaDelimitedListToSet(tagsAsText);

			for (String tag : tags) {
				if (tag != null) {

					final String massagedTagName = tag.trim().toLowerCase(Locale.ENGLISH);
					PresentationTag tagFromDb = this.getPresentationTag(massagedTagName);

					if (tagFromDb == null) {
						PresentationTag presentationTag = new PresentationTag();
						presentationTag.setName(massagedTagName);
						tagFromDb = this.savePresentationTag(presentationTag);
					}

					presentationTagsToSave.add(tagFromDb);
				}
			}
		}

		return presentationTagsToSave;
	}

	@Override
	public List<Sponsor> getSponsorsForEvent(Long id) {
		return sponsorDao.getSponsorsForEvent(id);
	}

	@Cacheable("sponsors")
	@Override
	public SponsorList getSponsorListForEvent(Long id, boolean large) {

		final List<Sponsor> sponsors = this.getSponsorsForEvent(id);

		final SponsorList sponsorList = new SponsorList();

		for (Sponsor sponsor : sponsors) {

			FileData imageData = this.getSponsorWithPicture(sponsor.getId()).getLogo();

			final int size;

			if (SponsorLevel.PLATINUM.equals(sponsor.getSponsorLevel())) {
				size = large ? 360 : 180;
			}
			else if (SponsorLevel.GOLD.equals(sponsor.getSponsorLevel())) {
				size = large ? 360 : 140;
			}
			else if (SponsorLevel.SILVER.equals(sponsor.getSponsorLevel())) {
				size = large ? 360 : 110;
			}
			else if (SponsorLevel.COCKTAIL_HOUR.equals(sponsor.getSponsorLevel())) {
				size = large ? 360 : 180;
			}
			else if (SponsorLevel.MEDIA_PARTNER.equals(sponsor.getSponsorLevel())) {
				size = large ? 920 : 460;
			}
			else {
				throw new IllegalStateException("Unsupported SponsorLevel " + sponsor.getSponsorLevel());
			}

			if (imageData != null) {
				ByteArrayInputStream bais = new ByteArrayInputStream(imageData.getFileData());
				BufferedImage image;
				try {
					image = ImageIO.read(bais);
					final BufferedImage scaled = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, size);
					final ByteArrayOutputStream out = new ByteArrayOutputStream();
					ImageIO.write(scaled, "PNG", out);

					byte[] bytes = out.toByteArray();

					final String base64bytes = Base64.encodeBase64String(bytes);
					final String src = "data:image/png;base64," + base64bytes;
					sponsorList.addSponsor(sponsor, src);
				} catch (IOException e) {
					LOGGER.error("Error while processing logo for sponsor " + sponsor.getName(), e);
				}
			}
		}

		return sponsorList;
	}

	@Override
	public EventSignup getEventSignup() {
		return eventSignupDao.getByEventKey(eventDao.getCurrentEvent().getEventKey());
	}

	@Override
	public TicketGroup getTicketGroup(Long ticketGroup) {
		return ticketGroupDao.findOne(ticketGroup);
	}

	@Override
	public RegistrationDetails getRegistrationForm(String registrationKey) {
		return registrationDao.findByKey(registrationKey);
	}

	@Override
        @Transactional
	public RegistrationDetails createPendingRegistrationForm(RegistrationDetails registerForm) {
		return registrationDao.createRegistrationPendingPayment(registerForm);
	}

        @Override
        @Transactional
        public void saveAndEmailPaidRegistration(RegistrationDetails registerForm, PayPalPayment payment) {
                registrationDao.saveAndFlush(registerForm);
                payPalDao.saveAndFlush(payment);
                if (mailSettings.isEnabled()) {
			registrationMailChannel.send(MessageBuilder.withPayload(registerForm).build());
		}
        }

    @Override
    public Long getCountOfAddonsSold(Long addOn) {
        return registrationDao.countSalesOfAddons(addOn);
    }

    @Override
    public TicketAddOn findAddOn(Long ticketAddOn) {
        return ticketAddOnDao.findOne(ticketAddOn);
    }

    @Override
    public Dashboard generateDashBoardForSignUp(EventSignup signUp) {
        Dashboard dashboard = new Dashboard();
        
        List<RegistrationDetails> orders = registrationDao.findPurchasedForEvent(signUp.getEvent());
        orders.sort((order1, order2) -> {return order1.getCreatedDate().compareTo(order2.getCreatedDate());});
        
        orders.stream().forEach((order) -> {
            dashboard.addOrder(order);
        });
        
        orders = registrationDao.findIncompletePaypalOrdersForEvent(signUp.getEvent());
        orders.sort((order1, order2) -> {return order1.getCreatedDate().compareTo(order2.getCreatedDate());});
        
        orders.stream().forEach((order) -> {
            dashboard.addInCompletePaypalOrders(order);
        });
        
        orders = registrationDao.findOrdersRequestingInvoiceForEvent(signUp.getEvent());
        orders.sort((order1, order2) -> {return order1.getCreatedDate().compareTo(order2.getCreatedDate());});
        
        orders.stream().forEach((order) -> {
            dashboard.addOrdersRequestingInvoice(order);
        });
        
        Map<Long, TicketGroup> ticketIdToGroup = new HashMap<>();
        Map<TicketGroup, Integer> ticketGroupCount = new HashMap<>();
        
        for (RegistrationDetails order : orders) {
            Long ticketGroupId = order.getTicketGroup();
            ticketIdToGroup.computeIfAbsent(ticketGroupId, (id)->{return getTicketGroup(id);});
            TicketGroup group = ticketIdToGroup.get(ticketGroupId);
            int count = ticketGroupCount.getOrDefault(group, 0);
            ticketGroupCount.put(group, count + 1);
        }
        
        for (Map.Entry<TicketGroup, Integer> entry : ticketGroupCount.entrySet()) {
            dashboard.addSale(entry.getKey(), entry.getValue());
        }
        
        Map<Long, TicketAddOn> workshopIdToWorkshop = new HashMap<>();
        Map<TicketAddOn, Integer> workshopCount = new HashMap<>();
        
        
        for (RegistrationDetails order : orders) {
            for (TicketOrderDetail detail : order.getOrderDetails()) {
                Long workshopId = detail.getTicketAddOn();
                if (workshopId == null) {
                    continue;
                }
                workshopIdToWorkshop.computeIfAbsent(workshopId, (id)->{return ticketAddOnDao.findOne(id);});
                TicketAddOn workshop = workshopIdToWorkshop.get(workshopId);
                int count = workshopCount.getOrDefault(workshop, 0);
                workshopCount.put(workshop, count + 1);
            }
            
        }
        
        for (Map.Entry<TicketAddOn, Integer> entry : workshopCount.entrySet()) {
            dashboard.addWorkshopSale(entry.getKey(), entry.getValue());
        }
        
        
        return dashboard;
    }

    @Override
    public List findRegistrations(String email, String name, EventSignup signUp) {
        
        List<List> results = new ArrayList<>();
        
        if (name != null && !name.isEmpty()) {
            String[] names = name.split(" ");
            results.addAll(registrationDao.findOrdersWithContactName(names, signUp));
        }
        if (email != null &&!email.isEmpty() ) {
            results.addAll(registrationDao.findOrdersWithContactEmail(email, signUp));
        }
        
        return new ArrayList<>(results);
        
    }

    @Override
    @Transactional
    public void updateRegistration(RegistrationDetails originalForm) {
        registrationDao.saveAndFlush(originalForm);
    }
    
}
