package com.devnexus.ting.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devnexus.ting.common.SystemInformationUtils;
import com.devnexus.ting.core.model.Organizer;
import com.devnexus.ting.core.model.OrganizerList;
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
public class SiteController {

	@Autowired private BusinessService businessService;

    /** serialVersionUID. */
    private static final long serialVersionUID = -3422780336408883930L;

    private final static Logger LOGGER = LoggerFactory.getLogger(SiteController.class);

    @RequestMapping("/index")
    public String execute(final Model model, final SitePreference sitePreference) {

        if (sitePreference.isMobile()) {
            return "index-mobile";
        }

        return "index";
    }

    @RequestMapping("/schedule")
    public String schedule(final Model model, final SitePreference sitePreference) {

        if (sitePreference.isMobile()) {
            return "schedule-mobile";
        }

        return "schedule";

    }

    @RequestMapping("/organizers")
    public String getOrganizers(final Model model, final SitePreference sitePreference) {

    	final List<Organizer>organizers = businessService.getAllOrganizers();

    	final OrganizerList organizerList = new OrganizerList(organizers);
    	model.addAttribute("organizerList", organizerList);


    	model.addAttribute("organizers", organizers);

        if (sitePreference.isMobile()) {
            return "organizers-mobile";
        }

        return "organizers";

    }

    @RequestMapping(value="/organizers/{organizerId}.jpg", method=RequestMethod.GET)
    public void getOrganizerPicture(@PathVariable("organizerId") Long organizerId, HttpServletResponse response) {

    	final Organizer organizer = businessService.getOrganizer(organizerId);

    	final InputStream organizerPicture;

    	if (organizer==null) {
    		organizerPicture = SystemInformationUtils.getOrganizerImage(null);
    	} else {
    		organizerPicture = SystemInformationUtils.getOrganizerImage(organizer.getPicture());
    	}

        try {
			org.apache.commons.io.IOUtils.copy(organizerPicture, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        response.setContentType("image/jpg");

    }

}
