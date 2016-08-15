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
package com.devnexus.ting.web.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.devnexus.ting.model.CfpSubmission;
import com.devnexus.ting.model.CfpSubmissionSpeaker;

/**
 *
 * @author Gunnar Hillert
 *
 */
public class CfpSubmissionSpeakerForm extends CfpSubmissionSpeaker {

	private static final long serialVersionUID = 1L;
	private MultipartFile pictureFile;
	private List<CfpAvailabilityForm> availabilityDays = new ArrayList<>();

	public void setPictureFile(MultipartFile file) {
		this.pictureFile = file;
	}

	public MultipartFile getPictureFile() {
		return pictureFile;
	}

	public List<CfpAvailabilityForm> getAvailabilityDays() {
		return availabilityDays;
	}

	public void setAvailabilityDays(List<CfpAvailabilityForm> availabilityDays) {
		this.availabilityDays = availabilityDays;
	}

}
