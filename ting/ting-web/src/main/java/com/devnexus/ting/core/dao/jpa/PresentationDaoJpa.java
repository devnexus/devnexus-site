package com.devnexus.ting.core.dao.jpa;

import org.springframework.stereotype.Repository;

import com.devnexus.ting.core.dao.PresentationDao;
import com.devnexus.ting.core.model.Presentation;

@Repository("presentationDao")
public class PresentationDaoJpa extends GenericDaoJpa< Presentation, Long>
                           implements PresentationDao {

    /** Constructor. */
    public PresentationDaoJpa() {
        super(Presentation.class);
    }
	
}
