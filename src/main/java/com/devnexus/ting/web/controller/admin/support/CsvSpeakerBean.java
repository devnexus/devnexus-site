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
package com.devnexus.ting.web.controller.admin.support;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

/**
 *
 * @author Gunnar Hillert
 *
 */
public class CsvSpeakerBean {

	public static CellProcessor[] getProcessors() {

		final CellProcessor[] processors = new CellProcessor[] {
				new NotNull(), // firstName
				new NotNull(), // lastName
				new Optional(), // email
				new Optional(), // twitterId
				new Optional(), // phone
				new Optional(), // tshirt
				new NotNull(), //travel
				new Optional() // location
		};

		return processors;
	}

	private String firstName;
	private String lastName;
	private String email;
	private String twitterId;
	private String phone;
	private String tshirtSize;
	private boolean reimburseTravel;
	private String location;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTwitterId() {
		return twitterId;
	}
	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTshirtSize() {
		return tshirtSize;
	}
	public void setTshirtSize(String tshirtSize) {
		this.tshirtSize = tshirtSize;
	}
	public boolean isReimburseTravel() {
		return reimburseTravel;
	}
	public void setReimburseTravel(boolean reimburseTravel) {
		this.reimburseTravel = reimburseTravel;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + (reimburseTravel ? 1231 : 1237);
		result = prime * result + ((tshirtSize == null) ? 0 : tshirtSize.hashCode());
		result = prime * result + ((twitterId == null) ? 0 : twitterId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CsvSpeakerBean other = (CsvSpeakerBean) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (reimburseTravel != other.reimburseTravel)
			return false;
		if (tshirtSize == null) {
			if (other.tshirtSize != null)
				return false;
		} else if (!tshirtSize.equals(other.tshirtSize))
			return false;
		if (twitterId == null) {
			if (other.twitterId != null)
				return false;
		} else if (!twitterId.equals(other.twitterId))
			return false;
		return true;
	}

}
