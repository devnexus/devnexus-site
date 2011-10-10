package com.devnexus.ting.core.dao;

import java.io.InputStream;

import com.devnexus.ting.core.model.Backup;

/**
 * @author Gunnar Hillert
 * @since 2.0
 */
public interface BackupDao {

	/**
     */
	Backup convertToBackupData(InputStream inputStream);
}
