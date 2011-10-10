package com.devnexus.ting.core.service.impl;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateJdbcException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devnexus.ting.core.dao.BackupDao;
import com.devnexus.ting.core.dao.SchemaMigrationDao;
import com.devnexus.ting.core.dao.SystemDao;
import com.devnexus.ting.core.model.Backup;
import com.devnexus.ting.core.model.SchemaMigration;
import com.devnexus.ting.core.service.SystemSetupService;

/**
 * @author Gunnar Hillert
 * @version $Id: SystemSetupServiceImpl.java 607 2010-09-08 13:20:36Z ghillert $
 */
@Service("systemSetupService")
@Transactional
public class SystemSetupServiceImpl implements SystemSetupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemSetupServiceImpl.class);

    private @Autowired SchemaMigrationDao   schemaMigrationDao;
    private @Autowired BackupDao            backupDao;
    private @Autowired SystemDao            systemDao;

    @Override
    public void restore(final InputStream inputStream) {

        final Backup backup = backupDao.convertToBackupData(inputStream);

        this.restore(backup);

    }

    /** {@inheritDoc} */
    @Override
    public void loadAndRestoreSeedData() {
        final InputStream is = SystemSetupServiceImpl.class.getResourceAsStream("/data/seeddata.xml");
        restore(is);
    }

    /** {@inheritDoc} */
    @Override
    public void restore(final Backup backup) {

       //TODO

    }

	@Override
	public void createDatabase() {
		systemDao.createDatabase(false, null);
	}

	@Override
	public void updateDatabase() {
		systemDao.updateDatabase();
	}

	@Override
	public boolean isDatabaseSetup() {

		//FIXME - Bad code...also need to check for versions etc.

		try {
			final List<SchemaMigration> migrations = schemaMigrationDao.getAll();

			if (migrations.isEmpty()) {
				return false;
			} else {
				return true;
			}
		} catch (HibernateJdbcException e) {
			return false;
		}

	}

	@Override
	public Backup convertToBackupData(InputStream inputStream) {
		return backupDao.convertToBackupData(inputStream);
	}

}
