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
package com.devnexus.ting.web.controller.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.Validator;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.model.FileData;
import com.devnexus.ting.core.model.Speaker;
import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.web.filter.FlashMap;

/**
 * Retrieves all jobs and returns an XML document. The structure conforms to the layout
 * defined by Indeed.com
 *
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
@Controller("adminSpeakerController")
public class SpeakerController {

    @Autowired private BusinessService businessService;

    @Autowired private Validator validator;

    /** serialVersionUID. */
    private static final long serialVersionUID = -3422780336408883930L;

    private final static Logger LOGGER = LoggerFactory.getLogger(SpeakerController.class);

    @RequestMapping(value="/admin/speakers", method=RequestMethod.GET)
    public String getSpeakers(ModelMap model, HttpServletRequest request) {

        final List<Speaker> speakers = businessService.getAllSpeakersOrderedByName();
        model.addAttribute("speakers", speakers);

        return "/admin/manage-speakers";
    }

    @RequestMapping(value="/admin/speaker", method=RequestMethod.GET)
    public String prepareAddSpeaker(ModelMap model) {

        final List<Event> events = businessService.getAllEventsOrderedByName();

        model.addAttribute("events", events);

        Speaker speakerForm = new Speaker();

        model.addAttribute("speaker", speakerForm);

        return "/admin/add-speaker";
    }

    @RequestMapping(value="/admin/speaker/{speakerId}", method=RequestMethod.GET)
    public String prepareEditSpeaker(@PathVariable("speakerId") Long speakerId, ModelMap model) {

        final List<Event> events = businessService.getAllEventsOrderedByName();

        model.addAttribute("events", events);

        Speaker speakerForm = businessService.getSpeaker(speakerId);

        model.addAttribute("speaker", speakerForm);

        return "/admin/add-speaker";
    }

    @RequestMapping(value="/admin/speaker/{speakerId}", method=RequestMethod.POST)
    public String editSpeaker(@PathVariable("speakerId") Long speakerId,
                              @RequestParam MultipartFile pictureFile,
                              @Valid Speaker speakerForm,
                              BindingResult result, HttpServletRequest request) {

        if (request.getParameter("cancel") != null) {
            return "redirect:/s/admin/index";
        }

        if (result.hasErrors()) {
            return "/admin/add-speaker";
        }

        final Speaker speakerFromDb = businessService.getSpeaker(speakerId);

        speakerFromDb.setBio(speakerForm.getBio());
        speakerFromDb.setTwitterId(speakerForm.getTwitterId());
        speakerFromDb.setFirstName(speakerForm.getFirstName());
        speakerFromDb.setLastName(speakerForm.getLastName());


        if (pictureFile != null && pictureFile.getSize() > 0) {

            final FileData pictureData;
            if (speakerFromDb.getPicture()==null) {
                pictureData = new FileData();
            } else {
                pictureData = speakerFromDb.getPicture();
            }

            try {

                pictureData.setFileData(IOUtils.toByteArray(pictureFile.getInputStream()));
                pictureData.setFileSize(pictureFile.getSize());
                pictureData.setFileModified(new Date());
                pictureData.setName(pictureFile.getOriginalFilename());
                pictureData.setType(pictureFile.getContentType());

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            speakerFromDb.setPicture(pictureData);

            String message = "File '" + pictureData.getName() + "' uploaded successfully";
            FlashMap.setSuccessMessage(message);
        }

        businessService.saveSpeaker(speakerFromDb);

        FlashMap.setSuccessMessage("The speaker was edited successfully.");
        return "redirect:/s/admin/speakers";
    }

    @RequestMapping(value="/admin/speaker", method=RequestMethod.POST)
    public String addSpeaker(@RequestParam MultipartFile pictureFile, @Valid Speaker speakerForm, BindingResult result, HttpServletRequest request) {

        if (request.getParameter("cancel") != null) {
            return "redirect:/s/admin/speakers";
        }

        if (result.hasErrors()) {
            return "/admin/add-speaker";
        }

        if (pictureFile != null && pictureFile.getSize() > 0) {

             final FileData pictureData = new FileData();

             try {

                 pictureData.setFileData(IOUtils.toByteArray(pictureFile.getInputStream()));
                 pictureData.setFileSize(pictureFile.getSize());
                 pictureData.setFileModified(new Date());
                 pictureData.setName(pictureFile.getOriginalFilename());
                 pictureData.setType(pictureFile.getContentType());

             } catch (IOException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }

             speakerForm.setPicture(pictureData);

            String message = "File '" + speakerForm.getPicture().getName() + "' uploaded successfully";
            FlashMap.setSuccessMessage(message);

        }

        Speaker savedSpeaker = businessService.saveSpeaker(speakerForm);

        FlashMap.setSuccessMessage("The speaker was added successfully.");
        return "redirect:/s/admin/speakers";
    }

}
