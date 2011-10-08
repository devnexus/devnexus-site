package com.devnexus.ting.web.flex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

import com.devnexus.ting.core.dao.PresentationDao;
import com.devnexus.ting.core.dao.SpeakerDao;
import com.devnexus.ting.core.model.Presentation;
import com.devnexus.ting.core.model.Speaker;


@Service @RemotingDestination
public class FlexService {

	@Autowired
	private SpeakerDao speakerDao;

	@Autowired
	private PresentationDao presentationDao;
	
	public FlexService() {
		super();
	}

	@RemotingInclude
	public String sayHello(String name) {
		return "howdy, " + name;
	}
	
	@RemotingInclude
	public List<Speaker> getAllSpeakers(String name) {
		return speakerDao.getAll();
	}
	
	@RemotingInclude
	public List<Presentation> getAllPresentations() {
		System.out.println("Returning presentations.");
		return presentationDao.getAll();
	}
	
}
