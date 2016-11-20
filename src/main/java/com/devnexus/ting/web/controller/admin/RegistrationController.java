/*
 * Copyright 2015 the original author or authors.
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
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.supercsv.io.ICsvBeanWriter;

import com.devnexus.ting.config.support.CsvExportSettings;
import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.model.Dashboard;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.EventSignup;
import com.devnexus.ting.model.RegistrationDetails;
import com.devnexus.ting.model.TicketGroup;
import com.devnexus.ting.model.TicketOrderDetail;
import com.devnexus.ting.repository.EventSignupRepository;
import com.devnexus.ting.web.form.RegisterForm;
import com.devnexus.ting.web.form.RegistrationSearchForm;
import com.devnexus.ting.web.form.SignupRegisterView;
import com.devnexus.ting.web.form.UploadGroupRegistrationForm;
import com.devnexus.ting.web.view.BulkRegistrationFormView;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * Used to manage registration forms and validation/combination rules.
 *
 * @author Summers Pittman
 *
 */
@Controller("adminRegistrationController")
public class RegistrationController {

    @Inject
    private EventSignupRepository eventSignupRepository;

    @Inject
    private CsvExportSettings csvSettings;

    @Inject
    private BusinessService businessService;

    @RequestMapping(value = "/s/admin/{eventKey}/registration", method = RequestMethod.GET)
    public String loadRegistration(ModelMap model, HttpServletRequest request,
            @PathVariable(value = "eventKey") String eventKey) {

        EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);

        model.addAttribute("event", signUp.getEvent());
        model.addAttribute("eventSignup", signUp);

        return "/admin/manage-registration";
    }

    @RequestMapping(value = "/s/admin/{eventKey}/registration/ticketGroup", method = RequestMethod.GET)
    public String showAddTicketGroupForm(ModelMap model, HttpServletRequest request,
            @PathVariable(value = "eventKey") String eventKey) {

        EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);

        model.addAttribute("event", signUp.getEvent());
        model.addAttribute("eventSignUp", signUp);
        model.addAttribute("ticketGroup", new TicketGroup());

        return "/admin/add-ticketgroup";
    }

    @RequestMapping(value = "/s/admin/{eventKey}/reporting", method = RequestMethod.GET)
    public void createReport(@PathVariable(value = "eventKey") String eventKey, HttpServletResponse response)
            throws IOException {

        Event event = businessService.getEventByEventKey(eventKey);

        String csvFileName = eventKey + "registrations.csv";

        response.setContentType("text/csv");

        // creates mock data
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);

        List<RegistrationDetails> registrations = businessService.findRegistrationsForEvent(event);

        try ( // uses the Super CSV API to generate CSV data from the model data
                ICsvBeanWriter csvWriter = new TicketCsvWriter(response.getWriter(),
                        new SimpleDateFormat(csvSettings.getDateFormat()))) {
            String[] header = {"First Name", "Last Name", "Email Address", "City", "State", "County", "Job Title",
                "Company", "T Shirt Size", "Vegetarian Meal", "Allow Sponsor To Contact", "Purchase Date",
                "Ticket Type", "Payment state"};

            csvWriter.writeHeader(header);

            for (RegistrationDetails registration : registrations) {
                for (TicketOrderDetail detail : registration.getOrderDetails()) {
                    csvWriter.write(detail, header);
                }
            }
        }

    }

    @RequestMapping(value = "/s/admin/{eventKey}/dashboard", method = RequestMethod.GET)
    public String showDashboard(ModelMap model, HttpServletRequest request,
            @PathVariable(value = "eventKey") String eventKey) {

        EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);

        Dashboard dashboard = businessService.generateDashBoardForSignUp(signUp);

        model.addAttribute("event", signUp.getEvent());
        model.addAttribute("dashboard", dashboard);

        return "/admin/dashboard";
    }

    @RequestMapping(value = "/s/admin/{eventKey}/uploadRegistration", method = RequestMethod.GET)
    public String uploadRegistration(ModelMap model, HttpServletRequest request,
            @PathVariable(value = "eventKey") String eventKey) {

        EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);

        model.addAttribute("event", signUp.getEvent());
        model.addAttribute("registerForm", new UploadGroupRegistrationForm());

        return "/admin/upload-registration";
    }

    @RequestMapping(value = "/s/admin/{eventKey}/uploadRegistration", method = RequestMethod.POST)
    public ModelAndView uploadGroupRegistration(ModelAndView model, HttpServletRequest request,
            @PathVariable(value = "eventKey") String eventKey, @Valid @ModelAttribute("registerForm") UploadGroupRegistrationForm uploadForm,
            BindingResult bindingResult) throws FileNotFoundException, IOException, InvalidFormatException {

        EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);
        model.getModelMap().addAttribute("event", signUp.getEvent());
        
        if (bindingResult.hasErrors()) {

            
            model.getModelMap().addAttribute("registerForm", uploadForm);
            model.setViewName("/admin/upload-registration");
        } else {

            boolean hasErrors = false;

            try {

                Workbook wb = WorkbookFactory.create(uploadForm.getRegistrationFile().getInputStream());
                Sheet sheet = wb.getSheetAt(0);
                Cell contactNameCell = sheet.getRow(0).getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Cell contactEmailCell = sheet.getRow(1).getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Cell contactPhoneCell = sheet.getRow(2).getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Cell registrationReferenceCell = sheet.getRow(3).getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                if (contactNameCell.getStringCellValue().isEmpty()) {
                    hasErrors = true;
                    bindingResult.addError(new ObjectError("registrationFile", "Contact Name Empty"));
                }

                if (contactEmailCell.getStringCellValue().isEmpty()) {
                    hasErrors = true;
                    bindingResult.addError(new ObjectError("registrationFile", "Contact Email Empty"));
                }

                if (contactPhoneCell.getStringCellValue().isEmpty()) {
                    hasErrors = true;
                    bindingResult.addError(new ObjectError("registrationFile", "Contact Phone Empty"));
                }

                if (registrationReferenceCell.getStringCellValue().isEmpty()) {
                    hasErrors = true;
                    bindingResult.addError(new ObjectError("registrationFile", "Registration Reference Empty"));
                }

                if (!hasErrors) {
                    RegistrationDetails details = new RegistrationDetails();
                    details.setContactEmailAddress(contactEmailCell.getStringCellValue());
                    details.setContactName(contactNameCell.getStringCellValue());
                    details.setContactPhoneNumber(contactPhoneCell.getStringCellValue());
                    details.setRegistrationFormKey(registrationReferenceCell.getStringCellValue());
                    details.setEvent(signUp.getEvent());
                    details.setFinalCost(BigDecimal.ZERO);
                    details.setInvoice("Invoiced");
                    details.setPaymentState(RegistrationDetails.PaymentState.PAID);
                    int attendeeRowIndex = 7;

                    Row attendeeRow = sheet.getRow(attendeeRowIndex);
                    while (attendeeRow != null) {
                        attendeeRow = sheet.getRow(attendeeRowIndex);
                        if (attendeeRow != null) {
                            Cell firstName = attendeeRow.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                            Cell lastName = attendeeRow.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                            Cell emailAddress = attendeeRow.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                            Cell city = attendeeRow.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                            Cell state = attendeeRow.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                            Cell country = attendeeRow.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                            Cell ticketType = attendeeRow.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                            Cell company = attendeeRow.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                            Cell jobTitle = attendeeRow.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                            Cell tshirtSize = attendeeRow.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                            Cell vegetarian = attendeeRow.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                            Cell sponsorMessages = attendeeRow.getCell(11, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                            if (firstName.getStringCellValue().isEmpty()) {
                                break;
                            }

                            if (lastName.getStringCellValue().isEmpty()) {
                                bindingResult.addError(new ObjectError("registrationFile", " row " + (attendeeRowIndex + 1) + " missing information : lastName"));
                                hasErrors = true;
                                break;
                            }
                            if (emailAddress.getStringCellValue().isEmpty()) {
                                bindingResult.addError(new ObjectError("registrationFile", " row " + (attendeeRowIndex + 1) + " missing information : emailAddress"));
                                hasErrors = true;
                                break;
                            }
                            if (city.getStringCellValue().isEmpty()) {
                                bindingResult.addError(new ObjectError("registrationFile", " row " + (attendeeRowIndex + 1) + " missing information : city"));
                                hasErrors = true;
                                break;
                            }
                            if (state.getStringCellValue().isEmpty()) {
                                bindingResult.addError(new ObjectError("registrationFile", " row " + (attendeeRowIndex + 1) + " missing information : state "));
                                hasErrors = true;
                                break;
                            }
                            if (country.getStringCellValue().isEmpty()) {
                                bindingResult.addError(new ObjectError("registrationFile", " row " + (attendeeRowIndex + 1) + " missing information : country"));
                                hasErrors = true;
                                break;
                            }
                            if (company.getStringCellValue().isEmpty()) {
                                bindingResult.addError(new ObjectError("registrationFile", " row " + (attendeeRowIndex + 1) + " missing information : company"));
                                hasErrors = true;
                                break;
                            }
                            if (jobTitle.getStringCellValue().isEmpty()) {
                                bindingResult.addError(new ObjectError("registrationFile", " row " + (attendeeRowIndex + 1) + " missing information : jobTitle"));
                                hasErrors = true;
                                break;
                            }

                            if (ticketType.getStringCellValue().isEmpty()) {
                                bindingResult.addError(new ObjectError("registrationFile", " row " + (attendeeRowIndex + 1) + " missing information: ticket type"));
                                hasErrors = true;
                                break;
                            }

                            if (tshirtSize.getStringCellValue().isEmpty()) {
                                bindingResult.addError(new ObjectError("registrationFile", " row " + (attendeeRowIndex + 1) + " missing information: t shirt "));
                                hasErrors = true;
                                break;
                            }
                            if (vegetarian.getStringCellValue().isEmpty() || !(vegetarian.getStringCellValue().equalsIgnoreCase("no") || vegetarian.getStringCellValue().equalsIgnoreCase("yes"))) {
                                bindingResult.addError(new ObjectError("registrationFile", " row " + (attendeeRowIndex + 1) + " missing information. Vegetarian option should be yes or no "));
                                hasErrors = true;
                                break;
                            }
                            if (sponsorMessages.getStringCellValue().isEmpty() || !(sponsorMessages.getStringCellValue().equalsIgnoreCase("no") || sponsorMessages.getStringCellValue().equalsIgnoreCase("yes"))) {
                                bindingResult.addError(new ObjectError("registrationFile", " row " + (attendeeRowIndex + 1) + " missing information. Sponsor message should be yes or no "));
                                hasErrors = true;
                                break;
                            }

                            TicketOrderDetail detail = new TicketOrderDetail();

                            detail.setCity(city.getStringCellValue());
                            detail.setCompany(company.getStringCellValue());
                            detail.setCouponCode("");
                            detail.setCountry(country.getStringCellValue());
                            detail.setEmailAddress(emailAddress.getStringCellValue());
                            detail.setFirstName(firstName.getStringCellValue());
                            detail.setJobTitle(jobTitle.getStringCellValue());
                            detail.setLastName(lastName.getStringCellValue());
                            detail.setSponsorMayContact(sponsorMessages.getStringCellValue().equalsIgnoreCase("no") ? "false" : "true");
                            detail.setState(state.getStringCellValue());
                            detail.setTicketGroup(Long.parseLong(ticketType.getStringCellValue().split("-\\|-")[1].trim()));
                            
                            detail.setLabel(businessService.getTicketGroup(detail.getTicketGroup()).getLabel());
                            
                            detail.settShirtSize(tshirtSize.getStringCellValue());
                            detail.setVegetarian(vegetarian.getStringCellValue().equalsIgnoreCase("no") ? "false" : "true");
                            detail.setRegistration(details);
                            details.getOrderDetails().add(detail);

                            attendeeRowIndex++;

                        }
                    }

                    if (uploadForm.getOverrideRegistration()) {
                        try {
                            RegistrationDetails tempRegistration = businessService.getRegistrationForm(registrationReferenceCell.getStringCellValue());
                            tempRegistration.getOrderDetails().forEach((oldDetail) -> {oldDetail.setRegistration(null);});
                            tempRegistration.getOrderDetails().clear();
                            tempRegistration.getOrderDetails().addAll(details.getOrderDetails());
                            
                            tempRegistration.getOrderDetails().forEach((detail) -> {
                                detail.setRegistration(tempRegistration);
                            });
                            details = tempRegistration;
                            businessService.updateRegistration(details);
                        } catch (EmptyResultDataAccessException ignore) {
                            businessService.updateRegistration(details);
                        }
                    } else {
                        try {
                            RegistrationDetails tempRegistration = businessService.getRegistrationForm(registrationReferenceCell.getStringCellValue());
                            hasErrors = true;
                            bindingResult.addError(new ObjectError("registrationFile", "Registration with this key exists, please check \"Replace Registrations\"."));
                        } catch (EmptyResultDataAccessException ignore) {
                            businessService.updateRegistration(details);
                        }
                    }

                }

            } catch (Exception ex) {
                hasErrors = true;
                bindingResult.addError(new ObjectError("registrationFile", ex.getMessage()));
            }
            if (hasErrors) {
                model.setViewName("/admin/upload-registration");
            } else {
                model.setViewName("/admin/index");
            }
        }

        return model;
    }

    @RequestMapping(value = "/s/admin/{eventKey}/groupRegistration", method = RequestMethod.GET)
    public String groupRegistration(ModelMap model, HttpServletRequest request,
            @PathVariable(value = "eventKey") String eventKey) {

        EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);

        model.addAttribute("event", signUp.getEvent());
        model.addAttribute("registerForm", new RegisterForm());

        return "/admin/group-registration";
    }

    @RequestMapping(value = "/s/admin/{eventKey}/groupRegistration", method = RequestMethod.POST)
    public ModelAndView downloadGroupRegistration(ModelAndView model, HttpServletRequest request,
            @PathVariable(value = "eventKey") String eventKey, @Valid RegisterForm form, BindingResult result) throws FileNotFoundException, IOException, InvalidFormatException {

        EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);

        model.getModelMap().addAttribute("event", signUp.getEvent());
        model.getModelMap().addAttribute("registerForm", form);

        if (!result.hasErrors()) {
            Workbook wb = WorkbookFactory.create(getClass().getResourceAsStream("/forms/registration_form.xlsx"));
            Sheet sheet = wb.getSheetAt(0);
            XSSFDataValidationHelper validationHelper = new XSSFDataValidationHelper((XSSFSheet) sheet);
            Row contactNameRow = sheet.getRow(0);
            Row contactEmailRow = sheet.getRow(1);
            Row contactPhoneRow = sheet.getRow(2);
            Row registrationReferenceRow = sheet.getRow(3);

            String[] ticketTypes = signUp.getGroups().stream()
                    .filter((TicketGroup group) -> {
                        Calendar today = Calendar.getInstance();
                        return today.after(toCal(group.getOpenDate())) && today.before(toCal(group.getCloseDate()));
                    })
                    .map((TicketGroup group) -> {
                        return String.format("%s -|-%d", group.getLabel(), group.getId());
                    })
                    .toArray(String[]::new);

            contactNameRow.createCell(1).setCellValue(form.getContactName());
            contactEmailRow.createCell(1).setCellValue(form.getContactEmailAddress());
            contactPhoneRow.createCell(1).setCellValue(form.getContactPhoneNumber());
            registrationReferenceRow.createCell(1).setCellValue(UUID.randomUUID().toString());

            CellRangeAddressList ticketCellAddress = new CellRangeAddressList(7, 100, 6, 7);
            DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(ticketTypes);
            DataValidation dataValidation = validationHelper.createValidation(constraint, ticketCellAddress);
            dataValidation.setSuppressDropDownArrow(true);
            sheet.addValidationData(dataValidation);

            model.setView(new BulkRegistrationFormView(wb));
        } else {
            model.setViewName("/admin/group-registration");
        }
        return model;
    }

    @RequestMapping(value = "/s/admin/{eventKey}/registration/ticketGroup/{groupId}", method = RequestMethod.GET)
    public String prepareEditTicketGroupForm(ModelMap model, HttpServletRequest request,
            @PathVariable(value = "eventKey") String eventKey, @PathVariable(value = "groupId") String groupId) {

        EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);

        model.addAttribute("event", signUp.getEvent());
        model.addAttribute("eventSignUp", signUp);
        model.addAttribute("ticketGroup",
                signUp.getGroups().stream().reduce(new TicketGroup(), ticketGroupReducer(groupId)));

        return "/admin/add-ticketgroup";
    }

    @RequestMapping(value = "/s/admin/{eventKey}/registration/ticketGroup", method = RequestMethod.POST)
    public String addTicketGroup(@PathVariable(value = "eventKey") String eventKey, @Valid TicketGroup ticketGroupForm,
            BindingResult result, HttpServletRequest request) {

        EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);

        if (request.getParameter("cancel") != null) {
            return String.format("redirect:/s/admin/%s/registration/", eventKey);
        }

        if (result.hasErrors()) {
            return String.format("/admin/add-ticketgroup", eventKey);
        }

        signUp.getGroups().add(ticketGroupForm);
        ticketGroupForm.setEventSignup(signUp);
        ticketGroupForm.setEvent(signUp.getEvent());

        ticketGroupForm.getCouponCodes().forEach((code) -> {
            code.setTicketGroup(ticketGroupForm);
        });

        eventSignupRepository.saveAndFlush(signUp);

        return String.format("redirect:/s/admin/%s/registration/", eventKey);
    }

    @RequestMapping(value = "/s/admin/{eventKey}/editRegistration", method = RequestMethod.GET)
    public String editRegistrationsShowSearch(ModelMap model, HttpServletRequest request,
            @PathVariable(value = "eventKey") String eventKey) {

        EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);

        model.addAttribute("event", signUp.getEvent());
        model.addAttribute("eventSignup", signUp);
        model.addAttribute("registrationSearchForm", new RegistrationSearchForm());
        return "/admin/search-registrations";
    }

    @RequestMapping(value = "/s/admin/{eventKey}/editRegistration/{registrationId}", method = RequestMethod.GET)
    public String editRegistrations(ModelMap model, HttpServletRequest request,
            @PathVariable(value = "eventKey") String eventKey,
            @PathVariable(value = "registrationId") String registrationId) {

        EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);
        model.addAttribute("event", signUp.getEvent());

        RegistrationDetails registerForm = businessService.getRegistrationForm(registrationId);

        Event currentEvent = businessService.getCurrentEvent();
        EventSignup eventSignup = businessService.getEventSignup();

        model.addAttribute("signupRegisterView", new SignupRegisterView(eventSignup));
        model.addAttribute("registrationDetails", registerForm);
        model.addAttribute("paymentStates", RegistrationDetails.PaymentState.values());

        return "/admin/edit-registration";
    }

    @RequestMapping(value = "/s/admin/{eventKey}/editRegistration/{registrationId}/resendEmail", method = RequestMethod.GET)
    public String resendRegistrationsEmail(ModelMap model, HttpServletRequest request,
            @PathVariable(value = "eventKey") String eventKey,
            @PathVariable(value = "registrationId") String registrationId) {

        EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);
        model.addAttribute("event", signUp.getEvent());

        RegistrationDetails registerForm = businessService.getRegistrationForm(registrationId);

        EventSignup eventSignup = businessService.getEventSignup();

        model.addAttribute("signupRegisterView", new SignupRegisterView(eventSignup));
        model.addAttribute("registrationDetails", registerForm);
        model.addAttribute("paymentStates", RegistrationDetails.PaymentState.values());

        businessService.resendRegistrationEmail(registerForm);

        return "/admin/edit-registration";
    }

    @RequestMapping(value = "/s/admin/{eventKey}/editRegistration/{registrationId}", method = RequestMethod.POST)
    public String saveEditedRegistrations(ModelMap model, HttpServletRequest request,
            @PathVariable(value = "eventKey") String eventKey,
            @PathVariable(value = "registrationId") String registrationId, @Valid RegistrationDetails registerForm,
            BindingResult result) {

        EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);
        model.addAttribute("event", signUp.getEvent());

        RegistrationDetails originalForm = businessService.getRegistrationForm(registrationId);

        originalForm.setContactEmailAddress(registerForm.getContactEmailAddress());
        originalForm.setContactName(registerForm.getContactName());
        originalForm.setPaymentState(registerForm.getPaymentState());
        originalForm.setContactPhoneNumber(registerForm.getContactPhoneNumber());
        originalForm.getOrderDetails().removeAll(originalForm.getOrderDetails());
        originalForm.getOrderDetails().addAll(registerForm.getOrderDetails());

        originalForm.getOrderDetails().forEach(detail -> {
            detail.setRegistration(originalForm);
        });

        businessService.updateRegistration(originalForm);

        return "redirect:/s/admin";
    }

    @RequestMapping(value = "/s/admin/{eventKey}/editRegistration", method = RequestMethod.POST)
    public String editRegistrationsShowSearchResults(ModelMap model, HttpServletRequest request,
            @PathVariable(value = "eventKey") String eventKey, @Valid RegistrationSearchForm searchForm,
            BindingResult result) {

        EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);

        Set<RegistrationDetails> detailsResults = new HashSet<>();
        Set<TicketOrderDetail> orderDetailsResults = new HashSet<>();

        model.addAttribute("event", signUp.getEvent());
        model.addAttribute("eventSignup", signUp);
        model.addAttribute("registrationSearchForm", searchForm);

        List searchResults = businessService.findRegistrations(searchForm.getEmail(), searchForm.getName(), signUp);

        for (Object object : searchResults) {
            if (object instanceof RegistrationDetails) {
                detailsResults.add((RegistrationDetails) object);
            }

            if (object instanceof TicketOrderDetail) {
                orderDetailsResults.add((TicketOrderDetail) object);
            }
        }

        model.addAttribute("registrations", detailsResults);
        model.addAttribute("tickets", orderDetailsResults);

        return "/admin/search-registrations";
    }

    @RequestMapping(value = "/s/admin/{eventKey}/registration/ticketGroup/{groupId}", method = RequestMethod.POST)
    public String editTicketGroup(@PathVariable(value = "eventKey") String eventKey,
            @PathVariable(value = "groupId") String groupId, @Valid TicketGroup ticketGroupForm, BindingResult result,
            HttpServletRequest request) {

        EventSignup signUp = eventSignupRepository.getByEventKey(eventKey);

        if (request.getParameter("cancel") != null) {
            return String.format("redirect:/s/admin/%s/registration/", eventKey);
        }

        if (result.hasErrors()) {
            return String.format("/admin/add-ticketgroup", eventKey);
        }

        TicketGroup group = signUp.getGroups().stream().reduce(new TicketGroup(), ticketGroupReducer(groupId));
        group.setLabel(ticketGroupForm.getLabel());
        group.setCloseDate(ticketGroupForm.getCloseDate());
        group.setOpenDate(ticketGroupForm.getOpenDate());
        group.setCouponCode(ticketGroupForm.getCouponCodes());
        group.setDescription(ticketGroupForm.getDescription());
        group.setMinPurchase(ticketGroupForm.getMinPurchase());
        group.setPrice(ticketGroupForm.getPrice());
        group.setRegisterFormUrl(ticketGroupForm.getRegisterFormUrl());

        eventSignupRepository.saveAndFlush(signUp);

        return String.format("redirect:/s/admin/%s/registration/", eventKey);
    }

    private BinaryOperator<TicketGroup> ticketGroupReducer(String groupId) {
        return (left, right) -> {
            return Long.parseLong(groupId) == right.getId() ? right : left;
        };
    }

    private Calendar toCal(Date openDate) {
        Calendar toReturn = Calendar.getInstance();
        toReturn.setTime(openDate);
        return toReturn;
    }

}
