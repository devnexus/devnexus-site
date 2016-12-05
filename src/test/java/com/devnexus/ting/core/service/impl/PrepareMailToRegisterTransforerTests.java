/*
 * Copyright 2016 the original author or authors.
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.devnexus.ting.DevNexusApplication;
import com.devnexus.ting.common.IntegrationTestApplicationContextInitializer;
import com.devnexus.ting.common.SpringProfile;
import com.devnexus.ting.common.SystemInformationUtils;
import com.devnexus.ting.core.service.integration.PrepareMailToRegisterTransformer;
import com.devnexus.ting.model.Event;
import com.devnexus.ting.model.RegistrationDetails;
import com.devnexus.ting.model.RegistrationDetails.PaymentState;
import com.devnexus.ting.model.TicketOrderDetail;

/**
 * Testing the rendering of Email templates.
 *
 * @author Gunnar Hillert
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={DevNexusApplication.class})
@ContextConfiguration(initializers=IntegrationTestApplicationContextInitializer.class)
@WebAppConfiguration
@ActiveProfiles({SpringProfile.MAIL_ENABLED})
//@Import(MailNotificationConfig.class)
//@Ignore
public class PrepareMailToRegisterTransforerTests {

	@Autowired
	private PrepareMailToRegisterTransformer prepareMailToRegister;

	@Test
	public void transformText() throws InterruptedException {
		RegistrationDetails registrationDetails = getRegistrationDetails();
		String template = SystemInformationUtils.getRegisterTextEmailTemplate();
		String renderedTemplate = prepareMailToRegister.applyMustacheTemplate(registrationDetails, template);
		System.out.println(renderedTemplate);
	}

	@Test
	public void transformHtml() throws InterruptedException {
		RegistrationDetails registrationDetails = getRegistrationDetails();
		String template = SystemInformationUtils.getRegisterHtmlEmailTemplate();
		String renderedTemplate = prepareMailToRegister.applyMustacheTemplate(registrationDetails, template);
		System.out.println(renderedTemplate);
	}

	private RegistrationDetails getRegistrationDetails() {

		final RegistrationDetails registrationDetails = new RegistrationDetails();
		registrationDetails.setContactEmailAddress("cartman@ajug.org");
		registrationDetails.setContactName("Cartman");
		registrationDetails.setContactPhoneNumber("555-555-5555");
		registrationDetails.setEvent(new Event(123L));
		registrationDetails.setFinalCost(BigDecimal.TEN);
		registrationDetails.setInvoice("Invoice");

		List<TicketOrderDetail> ticketOrderDetails = new ArrayList<>();

		TicketOrderDetail ticketOrderDetail = new TicketOrderDetail();
		ticketOrderDetail.setCity("Kailua-Kona");
		ticketOrderDetail.setCompany("Pivotal");
		ticketOrderDetail.setCountry("USA");
		ticketOrderDetail.setCouponCode("NONE");
		ticketOrderDetail.setEmailAddress("kenny@ajug.org");
		ticketOrderDetail.setFirstName("Stan");
		ticketOrderDetail.setJobTitle("Hero");
		ticketOrderDetail.setLastName("Butters");
		ticketOrderDetail.setRegistration(registrationDetails);
		ticketOrderDetail.setState("HI");
		ticketOrderDetail.settShirtSize("M");

		ticketOrderDetails.add(ticketOrderDetail);

		registrationDetails.setOrderDetails(ticketOrderDetails);
		registrationDetails.setPaymentState(PaymentState.PAID);

		return registrationDetails;
	}

}
