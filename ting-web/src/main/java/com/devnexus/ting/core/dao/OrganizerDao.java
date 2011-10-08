package com.devnexus.ting.core.dao;

import java.util.List;

import com.devnexus.ting.core.model.Organizer;

public interface OrganizerDao  extends GenericDao < Organizer, Long > {

	List<Organizer> getAllOrganizers();

}
