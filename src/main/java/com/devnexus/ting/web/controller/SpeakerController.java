/*
 * Copyright 2002-2014 the original author or authors.
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
package com.devnexus.ting.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.model.CfpSubmissionSpeaker;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.Speaker;
import com.devnexus.ting.model.SpeakerList;
import java.util.HashMap;
import java.util.Map;

/**
 * Display speakers.
 *
 * @author Gunnar Hillert
 *
 */
@Controller
public class SpeakerController {

    @Autowired
    private BusinessService businessService;

    @RequestMapping(value = "/s/speakers", method = RequestMethod.GET)
    public String getSpeakersForCurrentEvent(Model model, @RequestParam(value = "image", defaultValue = "false") boolean image) {
        Event currentEvent = businessService.getCurrentEvent();
        prepareSpeakers(currentEvent, model);
        return "speakers";
    }

    @RequestMapping("/s/{eventKey}/speakers")
    public String getSpeakersForEvent(@PathVariable("eventKey") String eventKey, Model model) {
        final Event event = businessService.getEventByEventKey(eventKey);
        model.addAttribute("contextEvent", event);
        prepareSpeakers(event, model);
        return "speakers";
    }

    @RequestMapping("/s/speakers/{speakerId}")
    public String getSpeakerDetails(@PathVariable("speakerId") Long speakerId, Model model) {
        final Event event = businessService.getCurrentEvent();
        prepareSpeaker(event, speakerId, model);
        return "speaker-details";
    }

    @RequestMapping("/s/{eventKey}/speakers/{speakerId}")
    public String getSpeakerDetailsForEvent(@PathVariable("eventKey") String eventKey, @PathVariable("speakerId") Long speakerId, Model model) {
        final Event event = businessService.getEventByEventKey(eventKey);
        model.addAttribute("contextEvent", event);
        prepareSpeaker(event, speakerId, model);
        return "speaker-details";
    }

    private void prepareSpeaker(Event event, Long speakerId, Model model) {
        model.addAttribute("event", event);
        final Speaker speaker = businessService.getSpeakerFilteredForEvent(speakerId, event);
        model.addAttribute("speaker", speaker);
    }

    private void prepareSpeakers(Event event, Model model) {
        model.addAttribute("event", event);
        SpeakerList speakers = new SpeakerList();

        speakers.setSpeakers(businessService.getSpeakersForEvent(event.getId()));
        Map<Long, CfpSubmissionSpeaker> cfpSpeakers = new HashMap<>(speakers.getNumberOfSpeakers());

        speakers.getSpeakers().forEach((speaker) -> {
            if (speaker.getCfpSpeakerId() != null) {
                CfpSubmissionSpeaker cfpSpeaker = businessService.getCfpSubmissionSpeaker(speaker.getCfpSpeakerId());
                if (cfpSpeaker == null ) {
                    cfpSpeaker = new CfpSubmissionSpeaker();
                }
                cfpSpeakers.put(speaker.getId(), cfpSpeaker);
            } else {
                cfpSpeakers.put(speaker.getId(), new CfpSubmissionSpeaker());
            }
        });

        model.addAttribute("trackList", businessService.getTracksForEvent(event.getId()));
        model.addAttribute("speakerList", speakers);
        model.addAttribute("cfpSpeakersMap", cfpSpeakers);

    }

    @RequestMapping(value = "/s/speakers/{speakerId}.jpg", method = RequestMethod.GET)
    public void getSpeakerPicture(@PathVariable("speakerId") Long speakerId, HttpServletResponse response) {

        byte[] speakerImage = businessService.getSpeakerImage(speakerId);

        try {
            org.apache.commons.io.IOUtils.write(speakerImage, response.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        response.setContentType("image/jpg");

    }

}
