package com.devnexus.ting.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.ContentType;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devnexus.ting.common.SystemInformationUtils;
import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.model.Presentation;
import com.devnexus.ting.core.model.Speaker;
import com.devnexus.ting.core.model.SpeakerList;
import com.devnexus.ting.core.service.BusinessService;

/**
 * Retrieves all jobs and returns an XML document. The structure conforms to the layout
 * defined by Indeed.com
 *
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
@Controller
public class SpeakerController {

	@Autowired private BusinessService businessService;

    /** serialVersionUID. */
    private static final long serialVersionUID = -3422780336408883930L;

    private final static Logger LOGGER = LoggerFactory.getLogger(SpeakerController.class);


    @RequestMapping(value="/speakers", method = RequestMethod.GET)
    public String getSpeakersForCurrentEvent(ModelMap model) {
    	SpeakerList speakers = new SpeakerList();
    	speakers.setSpeakers(businessService.getSpeakersForCurrentEvent());
    	model.addAttribute("speakerList",speakers);
        return "speakers";
    }

    @RequestMapping("/{eventKey}/speakers")
    public String getSpeakersForEvent(@PathVariable("eventKey") String eventKey, ModelMap model) {
    	final Event event = businessService.getEventByEventKey(eventKey);
    	model.addAttribute("event", event);

    	SpeakerList speakers = new SpeakerList();
    	speakers.setSpeakers(businessService.getSpeakersForEvent(event.getId()));
    	model.addAttribute("speakerList",speakers);

        return "speakers";
    }

    @RequestMapping(value="/speakers/{speakerId}.jpg", method=RequestMethod.GET)
    public void getSpeakerPicture(@PathVariable("speakerId") Long speakerId, HttpServletResponse response) {

    	final Speaker speaker = businessService.getSpeaker(speakerId);

    	final InputStream speakerPicture;

    	if (speaker==null) {
    		speakerPicture = SystemInformationUtils.getSpeakerImage(null);
    	} else {
    		speakerPicture = SystemInformationUtils.getSpeakerImage(speaker.getPicture());
    	}

        try {
			org.apache.commons.io.IOUtils.copy(speakerPicture, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        response.setContentType("image/png");

    }

}
