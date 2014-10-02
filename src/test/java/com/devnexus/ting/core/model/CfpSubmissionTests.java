package com.devnexus.ting.core.model;

import org.junit.Assert;
import org.junit.Test;

public class CfpSubmissionTests {

	@Test
	public void testGetSpeakersAsString() {
		
		final CfpSubmission cfpSubmission = new CfpSubmission();
		
		final CfpSubmissionSpeaker cfpSubmissionSpeaker1 = new CfpSubmissionSpeaker();
		cfpSubmissionSpeaker1.setFirstName("Tom");
		cfpSubmissionSpeaker1.setLastName("Jones");
		
		final CfpSubmissionSpeaker cfpSubmissionSpeaker2 = new CfpSubmissionSpeaker();
		cfpSubmissionSpeaker2.setFirstName("David");
		cfpSubmissionSpeaker2.setLastName("Letterman");
		
		final CfpSubmissionSpeaker cfpSubmissionSpeaker3 = new CfpSubmissionSpeaker();
		cfpSubmissionSpeaker3.setFirstName("Harald");
		cfpSubmissionSpeaker3.setLastName("Schmidt");
		
		cfpSubmission.getSpeakers().add(cfpSubmissionSpeaker1);
		cfpSubmission.getSpeakers().add(cfpSubmissionSpeaker2);
		cfpSubmission.getSpeakers().add(cfpSubmissionSpeaker3);
		
		Assert.assertEquals("Tom Jones, David Letterman & Harald Schmidt", cfpSubmission.getSpeakersAsString(false));
	}

	@Test
	public void testGetSpeakersAsStringOneSpeakerOnly() {
		
		final CfpSubmission cfpSubmission = new CfpSubmission();
		
		final CfpSubmissionSpeaker cfpSubmissionSpeaker1 = new CfpSubmissionSpeaker();
		cfpSubmissionSpeaker1.setFirstName("Tom");
		cfpSubmissionSpeaker1.setLastName("Jones");
		
		cfpSubmission.getSpeakers().add(cfpSubmissionSpeaker1);
		
		Assert.assertEquals("Tom Jones", cfpSubmission.getSpeakersAsString(false));
	}
	
	@Test
	public void testGetSpeakerFirstNamesAsString() {
		
		final CfpSubmission cfpSubmission = new CfpSubmission();
		
		final CfpSubmissionSpeaker cfpSubmissionSpeaker1 = new CfpSubmissionSpeaker();
		cfpSubmissionSpeaker1.setFirstName("Tom");
		cfpSubmissionSpeaker1.setLastName("Jones");
		
		final CfpSubmissionSpeaker cfpSubmissionSpeaker2 = new CfpSubmissionSpeaker();
		cfpSubmissionSpeaker2.setFirstName("David");
		cfpSubmissionSpeaker2.setLastName("Letterman");
		
		final CfpSubmissionSpeaker cfpSubmissionSpeaker3 = new CfpSubmissionSpeaker();
		cfpSubmissionSpeaker3.setFirstName("Harald");
		cfpSubmissionSpeaker3.setLastName("Schmidt");
		
		cfpSubmission.getSpeakers().add(cfpSubmissionSpeaker1);
		cfpSubmission.getSpeakers().add(cfpSubmissionSpeaker2);
		cfpSubmission.getSpeakers().add(cfpSubmissionSpeaker3);
		
		Assert.assertEquals("Tom, David & Harald", cfpSubmission.getSpeakersAsString(true));
	}

	@Test
	public void testGetSpeakerFirstNamesAsStringOneSpeakerOnly() {
		
		final CfpSubmission cfpSubmission = new CfpSubmission();
		
		final CfpSubmissionSpeaker cfpSubmissionSpeaker1 = new CfpSubmissionSpeaker();
		cfpSubmissionSpeaker1.setFirstName("Tom");
		cfpSubmissionSpeaker1.setLastName("Jones");
		
		cfpSubmission.getSpeakers().add(cfpSubmissionSpeaker1);
		
		Assert.assertEquals("Tom", cfpSubmission.getSpeakersAsString(true));
	}
	
}
