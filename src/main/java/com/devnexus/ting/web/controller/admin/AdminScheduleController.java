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
package com.devnexus.ting.web.controller.admin;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.Validator;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.model.CfpSpeakerImage;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.Presentation;
import com.devnexus.ting.model.PresentationList;
import com.devnexus.ting.model.ScheduleItem;
import com.devnexus.ting.model.ScheduleItemList;
import com.devnexus.ting.web.controller.admin.support.CsvScheduleItemBean;
import com.devnexus.ting.web.controller.admin.support.CsvSpeakerBean;
import com.devnexus.ting.web.controller.cfp.CallForPapersController;
import com.devnexus.ting.web.form.UploadScheduleForm;

/**
 * @author Gunnar Hillert
 */
@Controller
@RequestMapping(value="/s/admin")
public class AdminScheduleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CallForPapersController.class);

	@Autowired private BusinessService businessService;
	@Autowired private Validator validator;

	private void prepareReferenceData(ModelMap model) {

//TODO
//		final Event currentEvent = businessService.getCurrentEvent();
//		model.addAttribute("currentEvent", currentEvent);
//
//		final Set<PresentationType> presentationTypes = EnumSet.allOf(PresentationType.class);
//		model.addAttribute("presentationTypes", presentationTypes);
//
//		final Set<SkillLevel> skillLevels = EnumSet.allOf(SkillLevel.class);
//		model.addAttribute("skillLevels", skillLevels);
//
//		final Set<CfpSubmissionStatusType> cfpSubmissionStatusTypes = EnumSet.allOf(CfpSubmissionStatusType.class);
//		model.addAttribute("cfpSubmissionStatusTypes", cfpSubmissionStatusTypes);

	}

	@RequestMapping(value="/{eventKey}/manage-schedule", method=RequestMethod.GET)
	public String prepareEditCfp(@PathVariable("eventKey") String eventKey, ModelMap model) {

		prepareReferenceData(model);
		final Event event = businessService.getEventByEventKey(eventKey);
		final ScheduleItemList scheduleItemList = businessService.getScheduleForEvent(event.getId());

		final List<Presentation> presentations = businessService.getPresentationsForEventOrderedByName(event.getId());
		final PresentationList presentationList = new PresentationList();
		presentationList.setPresentations(presentations);

		model.addAttribute("scheduleItemList", scheduleItemList);
		model.addAttribute("presentationList", presentationList);

		return "/admin/manage-schedule";
	}

	@RequestMapping("/{eventKey}/download-schedule")
	public void downloadScheduleForEvent(@PathVariable("eventKey") String eventKey, HttpServletResponse response)
		throws IOException {

		final Event event = businessService.getEventByEventKey(eventKey);

		final String csvFileName = event.getEventKey() + "-schedule.csv";

		response.setContentType("text/csv");

		final String headerKey = "Content-Disposition";
		final String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
		response.setHeader(headerKey, headerValue);

		final ScheduleItemList scheduleItems = businessService.getScheduleForEvent(event.getId());
		final List<CsvScheduleItemBean> csvData = new ArrayList<>();

		for (ScheduleItem scheduleItem : scheduleItems.getScheduleItems()) {
			final CsvScheduleItemBean csvScheduleItemBean = new CsvScheduleItemBean();

			csvScheduleItemBean.setEventId(scheduleItem.getEvent().getId());

			csvScheduleItemBean.setFromTime(scheduleItem.getFromTime());
			csvScheduleItemBean.setId(scheduleItem.getId());

			if (scheduleItem.getPresentation() != null) {
				csvScheduleItemBean.setPresentationId(scheduleItem.getPresentation().getId());
			}
			else {
				csvScheduleItemBean.setPresentationId(-1L);
			}

			if (scheduleItem.getRoom() != null) {
				csvScheduleItemBean.setRoomId(scheduleItem.getRoom().getId());
			}

			csvScheduleItemBean.setTitle(scheduleItem.getTitle());
			csvScheduleItemBean.setToTime(scheduleItem.getToTime());
			csvScheduleItemBean.setType(scheduleItem.getScheduleItemType());

			csvData.add(csvScheduleItemBean);
		}

		ICsvBeanWriter beanWriter = null;
		try {
			beanWriter = new CsvBeanWriter(response.getWriter(),
				CsvPreference.STANDARD_PREFERENCE);

			final String[] header = new String[] {
					"id",
					"eventId",
					"fromTime",
					"toTime",
					"type",
					"title",
					"presentationId",
					"roomId"
			};

			final CellProcessor[] processors = CsvSpeakerBean.getProcessors();

			beanWriter.writeHeader(header);

			for( final CsvScheduleItemBean scheduleItem : csvData ) {
					if (scheduleItem.getId().equals(Long.valueOf(5747))) {
						//System.out.println("qweqeqweqe");
					}
					beanWriter.write(scheduleItem, header, processors);
			}

		}
		finally {
				if( beanWriter != null ) {
						beanWriter.close();
				}
		}
	}

	/**
	 * Open the Add Speaker Page.
	 *
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/{eventKey}/upload-schedule", method=RequestMethod.GET)
	public String openUploadSchedule(ModelMap model, WebRequest request) {

		model.addAttribute("uploadScheduleForm", new UploadScheduleForm());

		return "admin/schedule/upload-schedule";

	}

	@RequestMapping(value="/{eventKey}/upload-schedule", method=RequestMethod.POST)
	public String uploadScheduleForEvent(@PathVariable("eventKey") String eventKey, HttpServletResponse response,
			@Valid @ModelAttribute("uploadScheduleForm") UploadScheduleForm uploadScheduleForm,
			BindingResult bindingResult,
			ModelMap model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) throws IOException {

		if (request.getParameter("cancel") != null) {
			return "redirect:/s/admin/index";
		}

		final Event event = businessService.getEventByEventKey(eventKey);

		final MultipartFile scheduleFile = uploadScheduleForm.getScheduleFile();
		processScheduleCsv(scheduleFile, bindingResult);
		return "redirect:/s/admin/index";

	}

	private List<ScheduleItem> processScheduleCsv(MultipartFile scheduleCsv, BindingResult bindingResult) {
		List<ScheduleItem> scheduleItems = new ArrayList<>();
		byte[] scheduleCsvData = null;

		try {
			scheduleCsvData = scheduleCsv.getBytes();
		} catch (IOException e) {
			LOGGER.error("Error processing Schedule CSV File.", e);
			bindingResult.addError(new FieldError("uploadScheduleForm", "scheduleFile", "Error processing Schedule CSV File."));
			return null;
		}

		if (scheduleCsvData != null && scheduleCsv.getSize() > 0) {

			final ByteArrayInputStream bais = new ByteArrayInputStream(scheduleCsvData);

			ICsvBeanReader beanReader = null;
			try {
				final Reader reader = new InputStreamReader(bais);
				beanReader = new CsvBeanReader(reader, CsvPreference.STANDARD_PREFERENCE);

				// the header elements are used to map the values to the bean (names must match)
				final String[] header = beanReader.getHeader(true);
				final CellProcessor[] processors = CsvScheduleItemBean.getProcessors();

				CsvScheduleItemBean scheduleItemBean;
				while( (scheduleItemBean = beanReader.read(CsvScheduleItemBean.class, header, processors)) != null ) {
					System.out.println(String.format("lineNo=%s, rowNo=%s, customer=%s", beanReader.getLineNumber(),
					beanReader.getRowNumber(), scheduleItemBean));
					final ScheduleItem scheduleItem = new ScheduleItem();
//					scheduleItem.setEvent(null);
//					scheduleItem.setId(id);
//					scheduleItem.setFromTime();
//					scheduleItem.setPresentation(presentation);
//					scheduleItem.setRoom(room);
//					scheduleItem.setScheduleItemType(scheduleItemType);
//					scheduleItem.setTitle(title);
//					scheduleItem.setToTime(toTime);
//					scheduleItem.set
				}
			}
			catch (IOException e) {
				LOGGER.error("Error processing CSV File.", e);
				bindingResult.addError(new FieldError("uploadScheduleForm", "scheduleFile", "Error processing Schedule CSV File."));
				return null;
			}
			finally {
				if( beanReader != null ) {
					try {
						beanReader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return null;
	}

}
