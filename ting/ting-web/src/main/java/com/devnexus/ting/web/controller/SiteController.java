package com.devnexus.ting.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
            LOGGER.info("Hello mobile user!");
            return "index-mobile";
        }

        return "index";
    }

    @RequestMapping("/schedule")
    public String schedule(final Model model) {
        return "schedule";
    }

    @RequestMapping("/organizers")
    public String getOrganizers(final Model model) {
        return "organizers";
    }

}
