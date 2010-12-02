package com.devnexus.ting.core.dao;

import java.util.List;

import com.devnexus.ting.core.model.Presentation;

public interface PresentationDao  extends GenericDao < Presentation, Long > {

	List<Presentation> getPresentationsForCurrentEvent();

	List<Presentation> getPresentationsForEvent(Long eventId);

}
