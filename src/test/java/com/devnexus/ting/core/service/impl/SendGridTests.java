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
package com.devnexus.ting.core.service.impl;

import org.junit.Ignore;
import org.junit.Test;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

/**
 *
 * @author Gunnar Hillert
 *
 */
@Ignore
public class SendGridTests {

	@Test
	public void sendEmailWithSendGrid() {
		SendGrid sendgrid = new SendGrid("your api key");

		SendGrid.Email email = new SendGrid.Email();
		email.addTo("gunnar@hillert.com");
		email.setFrom("gunnar@hillert.com");
		email.setSubject("Hello World");
		email.setText("My first email with SendGrid Java!");

		try {
			SendGrid.Response response = sendgrid.send(email);
			System.out.println(response.getMessage());
		}
		catch (SendGridException e) {
			System.err.println(e);
		}
	}
}
