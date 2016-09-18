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
package com.devnexus.ting.web.controller.admin.support;

import java.util.Date;
import java.util.Locale;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

import com.devnexus.ting.model.ScheduleItemType;

/**
 *
 * @author Gunnar Hillert
 *
 */
public class CsvScheduleItemBean {

	public static CellProcessor[] getProcessors() {

		final CellProcessor[] processors = new CellProcessor[] {
				new Optional(new ParseLong()), // Id
				new NotNull(new ParseLong()),  // event_id
				new NotNull(new ParseDate("yyyy-MM-dd HH:mm", false, Locale.ENGLISH)),  // fromTime
				new NotNull(new ParseDate("yyyy-MM-dd HH:mm", false, Locale.ENGLISH)),  // toTime
				new NotNull(),  // type
				new Optional(), // title
				new Optional(new ParseLong()), // presentation_id
				new Optional(new ParseLong())  // room_id
		};

		return processors;
	}

	private Long id;
	private Long eventId;
	private Date fromTime;
	private Date toTime;
	private ScheduleItemType type;
	private String title;
	private Long  presentationId;
	private Long  roomId;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Date getFromTime() {
		return fromTime;
	}
	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}
	public Date getToTime() {
		return toTime;
	}
	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}
	public ScheduleItemType getType() {
		return type;
	}
	public void setType(ScheduleItemType type) {
		this.type = type;
	}
	public void setType(String type) {
		this.type = ScheduleItemType.valueOf(type);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getPresentationId() {
		return presentationId;
	}
	public void setPresentationId(Long presentationId) {
		this.presentationId = presentationId;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
}
