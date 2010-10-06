package com.devnexus.ting.core.dao.jpa;

import org.springframework.stereotype.Repository;

import com.devnexus.ting.core.dao.SpeakerDao;
import com.devnexus.ting.core.model.Speaker;

@Repository("speakerDao")
public class SpeakerDaoJpa extends GenericDaoJpa< Speaker, Long>
                           implements SpeakerDao {

    /** Constructor. */
    private SpeakerDaoJpa() {
        super(Speaker.class);
    }
	
}
