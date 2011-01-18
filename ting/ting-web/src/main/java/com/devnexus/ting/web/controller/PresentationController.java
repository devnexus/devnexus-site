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
import com.devnexus.ting.core.model.PresentationList;
import com.devnexus.ting.core.model.Speaker;
import com.devnexus.ting.core.service.BusinessService;

/**
 * Retrieves all jobs and returns an XML document. The structure conforms to the layout
 * defined by Indeed.com
 *
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
@Controller
public class PresentationController {

	@Autowired private BusinessService businessService;

    /** serialVersionUID. */
    private static final long serialVersionUID = -3422780336408883930L;

    private final static Logger LOGGER = LoggerFactory.getLogger(PresentationController.class);

    @RequestMapping("/{eventKey}/presentations")
    public String getPresentationsForEvent(@PathVariable("eventKey") String eventKey, ModelMap model) {
    	final Event event = businessService.getEventByEventKey(eventKey);
    	model.addAttribute("event", event);

    	final PresentationList presentationList = new PresentationList();
    	presentationList.setPresentations(businessService.getPresentationsForEvent(event.getId()));

    	model.addAttribute("presentationList", presentationList);
        return "presentations";
    }

    @RequestMapping(value="/presentations/{presentationId}/slides", method=RequestMethod.GET)
    public void getPresentationSlides(@PathVariable("presentationId") Long presentationId, HttpServletResponse response) {

    	final Presentation presentation = businessService.getPresentation(presentationId);

    	final InputStream presentationFile = SystemInformationUtils.getPresentation(presentation.getEvent().getEventKey(), presentation.getPresentationLink());

        try {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition",
                    "attachment; filename=\"" + presentation.getPresentationLink() + "\"");
			org.apache.commons.io.IOUtils.copy(presentationFile, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @RequestMapping("/presentations")
    public String getPresentationsForCurrentEvent(ModelMap model) {

    	final PresentationList presentationList = new PresentationList();
    	presentationList.setPresentations(businessService.getPresentationsForCurrentEvent());

    	model.addAttribute("presentationList", presentationList);
        return "presentations";
    }

}
