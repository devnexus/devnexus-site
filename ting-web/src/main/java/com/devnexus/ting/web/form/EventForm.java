package com.devnexus.ting.web.form;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.devnexus.ting.core.model.Event;

public class EventForm {

	@Valid @NotNull
	Event event;

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
