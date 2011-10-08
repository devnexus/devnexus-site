package com.devnexus.ting.core.dao.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.devnexus.ting.core.dao.OrganizerDao;
import com.devnexus.ting.core.model.Organizer;

@Repository("organizerDao")
public class OrganizerDaoJpa extends GenericDaoJpa< Organizer, Long>
                           implements OrganizerDao {

    /** Constructor. */
    private OrganizerDaoJpa() {
        super(Organizer.class);
    }

	@Override
	public List<Organizer> getAllOrganizers() {

		return super.entityManager
		.createQuery("select o from Organizer o "
				   + "order by o.sortOrder ASC", Organizer.class)
		.getResultList();
	}

}
