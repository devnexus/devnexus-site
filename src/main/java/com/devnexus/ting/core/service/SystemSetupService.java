/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.devnexus.ting.core.service;

import java.io.InputStream;

import com.devnexus.ting.core.model.Backup;

/**
 * Special services allowing the administrator to populate the sytem with
 * demo data.
 *
 * @author Gunnar Hillert
 *
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

	void setupDatabase();
}
