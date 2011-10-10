/**
 *
 */
package com.devnexus.ting.core.dao.jaxb;

import java.io.InputStream;

import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Repository;

import com.devnexus.ting.core.dao.BackupDao;
import com.devnexus.ting.core.model.Backup;

/**
 * @author Gunnar Hillert
 * @since 2.0
 *
 */
@Repository("backupDao")
public class BackupDaoJaxb implements BackupDao {

	/** Logger declaration */
	private static final Logger LOGGER = LoggerFactory.getLogger(BackupDaoJaxb.class);

    private @Autowired Jaxb2Marshaller marshaller;

	/**
	 *
	 */
	public BackupDaoJaxb() {
	}

    @Override
    public Backup convertToBackupData(final InputStream inputStream) {

        final StreamSource source = new StreamSource(inputStream);
        final Backup backup = (Backup) marshaller.unmarshal(source);

        LOGGER.info("Restoring: " + backup.getUsers().size()         + " users, "
                                  + backup.getEvents().size()        + " events, "
                                  + backup.getOrganizers().size()    + " organizers, "
                                  + backup.getPresentations().size() + " presentations, and "
                                  + backup.getSpeakers().size()      + " speakers.");

        return backup;

    }

}
