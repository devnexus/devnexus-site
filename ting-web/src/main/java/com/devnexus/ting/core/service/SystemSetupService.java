package com.devnexus.ting.core.service;

import java.io.InputStream;

import com.devnexus.ting.core.model.Backup;
import com.devnexus.ting.core.model.User;

/**
 * Special services allowing the administrator to populate the sytem with
 * demo data.
 *
 * @author Gunnar Hillert
 *
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
public interface SystemSetupService {

    /**
     * Restore a set of backed-up master data.
     */
    void restore(Backup backup);

    /**
     * Restore a set of backed-up master data.
     */
    void restore(InputStream inputStream);

    /** Create the database using Hibernate's SchemaExport functionality */
    void createDatabase();

    /** Update the database using Hibernate's SchemaUpdate functionality */
    void updateDatabase();

    /** */
    void loadAndRestoreSeedData();

    /** */
	boolean isDatabaseSetup();

	Backup convertToBackupData(InputStream inputStream);
}
