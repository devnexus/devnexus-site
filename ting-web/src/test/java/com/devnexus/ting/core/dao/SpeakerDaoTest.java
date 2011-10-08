/**
 *
 */
package com.devnexus.ting.core.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.devnexus.ting.core.model.Speaker;

/**
 * @author Gunnar Hillert
 * @version $Id: IndustryDaoTest.java 605 2010-08-31 05:31:30Z ghillert $
 */
public class SpeakerDaoTest extends BaseDaoIntegrationTest {

	@Autowired private SpeakerDao speakerDao;

    /**
     * Test to verify that the seed data is correctly populated. Typically there
     * should be 10 industries in the system:
     *
     */
    @Test
    public void testGenerateSchema() {
    	List<Speaker> speakers = speakerDao.getSpeakersForCurrentEvent();
    	System.out.println("Numbers speakers: " + speakers.size());

    	for (Speaker speaker : speakers) {
    		System.out.println(speaker.getLastName() + ";" + speaker.getPresentations().size());
    	}
    }

}
