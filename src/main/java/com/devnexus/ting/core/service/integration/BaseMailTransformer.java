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
package com.devnexus.ting.core.service.integration;
import javax.mail.internet.MimeMessage;

import com.devnexus.ting.model.CfpSubmission;

/**
 * Converts a {@link CfpSubmission} and converts it to a {@link MimeMessage}.
 *
 * @author Gunnar Hillert
 *
 */
abstract class BaseMailTransformer {

	protected String fromUser;
	protected String ccUser;

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public void setCcUser(String ccUser) {
		this.ccUser = ccUser;
	}

}
