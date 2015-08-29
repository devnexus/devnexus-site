/*
 * Copyright 2002-2015 the original author or authors.
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
package com.devnexus.ting.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.cloudfoundry.runtime.env.CloudEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 *
 * @author Gunnar Hillert
 *
 */
public final class SystemInformationUtils {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SystemInformationUtils.class);

	public static int CONSOLE_SPACER_WIDTH = 40;
	public static String CONSOLE_SPACER_CHARACTER = ".";
	public static String TING_CONFIG_FILENAME = "devnexus.yml";

	public enum AppHomeDirectories {

		ORGANIZER_IMAGES("/images/organizers"),
		SPEAKER_IMAGES("/images"),
		PRESENTATION_SLIDES("slides"), PRESENTATION_AUDIO(
				"audio");

		private String folderName;

		AppHomeDirectories(String folderName) {
			this.folderName = folderName;
		}

		public String getFolderName() {
			return folderName;
		}

	}

	/**
	 *
	 */
	private SystemInformationUtils() {
		// private utility class constructor
	}

	/**
	 *
	 * @return
	 */
	public static Apphome retrieveBasicSystemInformation() {

		final Apphome apphome = new Apphome();

		CloudEnvironment env = new CloudEnvironment();
		if (env.getInstanceInfo() != null) {

			apphome.setAppHomePath(null);
			apphome.setAppHomeSource(AppHomeSource.CLOUD);

		} else  if (StringUtils.isNotBlank(System
				.getProperty(Apphome.APP_HOME_DIRECTORY))) {

			apphome.setAppHomePath(System
					.getProperty(Apphome.APP_HOME_DIRECTORY));
			apphome.setAppHomeSource(AppHomeSource.SYSTEM_PROPERTY);

		} else if (StringUtils.isNotBlank(System
				.getenv(Apphome.APP_HOME_DIRECTORY))) {

			apphome.setAppHomePath(System
					.getenv(Apphome.APP_HOME_DIRECTORY));
			apphome.setAppHomeSource(AppHomeSource.ENVIRONMENT_VARIABLE);

		} else {

			final String userHomeDirectiory = System.getProperty("user.home");

			apphome.setAppHomePath(userHomeDirectiory + File.separator
					+ ".ting");
			apphome.setAppHomeSource(AppHomeSource.USER_DIRECTORY);

		}

		return apphome;
	}

	/**
	 *
	 * @param filePath
	 * @return
	 */
	public static boolean existsConfigFile(final String filePath) {

		final File configFile = getConfigFile(filePath);

		return configFile.exists() && configFile.isFile();

	}

	private static File getConfigFile(final String filePath) {
		return new File(filePath + File.separator
				+ TING_CONFIG_FILENAME);
	}

	public static Properties getConfigProperties(final String filePath) {

		Resource resource = new FileSystemResource(getConfigFile(filePath));
		try {
			return PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			throw new IllegalStateException("Error loading properties.", e);
		}

	}

	public static SpringContextMode getSpringContextMode(final String filePath) {

		if (existsConfigFile(filePath)) {
			return SpringContextMode.ProductionContextConfiguration;
		} else {
			return SpringContextMode.DemoContextConfiguration;
		}

	}

	/**
	 *
	 * @return
	 */
	public static String getAllEnvironmentVariables() {

		final StringBuilder environmentVariables = new StringBuilder();

		final Map<String, String> environmentVariablesAsMap = System.getenv();

		for (final Entry<String, String> entry : environmentVariablesAsMap
				.entrySet()) {
			environmentVariables.append(entry.getKey() + ": "
					+ entry.getValue() + "\n");
		}

		LOGGER.info(environmentVariables.toString());

		return environmentVariables.toString();
	}

	/**
	 *
	 * @return
	 */
	public static String getAllSystemProperties() {

		final StringBuilder systemProperties = new StringBuilder();

		for (SystemPropertyInformation property : SystemPropertyInformation
				.getSystemPropertyValuesAsList()) {

			final String label = StringUtils.rightPad(
					property.getPropertyKey(), CONSOLE_SPACER_WIDTH,
					CONSOLE_SPACER_CHARACTER);

			systemProperties.append(label + ": " + property.getPropertyValue())
					.append("\n");
		}

		return systemProperties.toString();
	}

	public static InputStream getImage(AppHomeDirectories folder, String imageName) {
		final Apphome appHome = SystemInformationUtils.retrieveBasicSystemInformation();

		if (imageName == null) {
			return SystemInformationUtils.class
					.getResourceAsStream("/data/images/image_not_available.jpg");
		}

		final File image = new File(
				appHome.getAppHomePath()
						+ File.separator
						+ folder.folderName
						+ File.separator + File.separator + imageName);

		if (image.isFile() && image.exists()) {
			try {
				return new FileInputStream(image);
			} catch (FileNotFoundException e) {
				return SystemInformationUtils.class
						.getResourceAsStream("/data/images/image_not_available.jpg");
			}
		} else {
			return SystemInformationUtils.class
					.getResourceAsStream("/data/images/image_not_available.jpg");
		}

	}

	/**
	 *
	 * @return
	 */
	public static byte[] getOrganizerImage(String imageName) {

		final InputStream is = SystemInformationUtils.getImage(SystemInformationUtils.AppHomeDirectories.ORGANIZER_IMAGES, imageName);

		try {
			return IOUtils.toByteArray(is);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

	}

	/**
	 *
	 * @return
	 */
	public static byte[] getSpeakerImage(String imageName) {

		final InputStream is = SystemInformationUtils.getImage(SystemInformationUtils.AppHomeDirectories.SPEAKER_IMAGES, imageName);

		try {
			return IOUtils.toByteArray(is);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

        public static String getCfpTextEmailTemplate() {

		final InputStream is = SystemInformationUtils.class.getResourceAsStream("/templates/mail/cfp-email.txt");

		final String template;

		try {
			template = IOUtils.toString(is, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

		return template;
	}

        
	public static String getRegisterTextEmailTemplate() {

		final InputStream is = SystemInformationUtils.class.getResourceAsStream("/templates/mail/register-email.txt");

		final String template;

		try {
			template = IOUtils.toString(is, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

		return template;
	}
        
	public static String getRegisterHtmlEmailTemplate() {

		final InputStream is = SystemInformationUtils.class.getResourceAsStream("/templates/mail/register-email.html");

		final String template;

		try {
			template = IOUtils.toString(is, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

		return template;
	}

	public static String getCfpHtmlEmailTemplate() {

		final InputStream is = SystemInformationUtils.class.getResourceAsStream("/templates/mail/cfp-email.html");

		final String template;

		try {
			template = IOUtils.toString(is, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

		return template;
	}

	/**
	 *
	 * @return
	 */
	public static void setSpeakerImage(String imageName, InputStream is) {

		final Apphome appHome = SystemInformationUtils.retrieveBasicSystemInformation();

		if (imageName == null) {
			throw new IllegalStateException("Please provide an image name.");
		}

		if (is == null) {
			throw new IllegalStateException(
					"Please provide a valid input stream.");
		}

		final File image = new File(
				appHome.getAppHomePath()
						+ File.separator
						+ SystemInformationUtils.AppHomeDirectories.SPEAKER_IMAGES.folderName, imageName);


			if (!image.getParentFile().exists()) {
				boolean created = image.getParentFile().mkdirs();
				if (!created) {
					throw new IllegalStateException("Unable to create directory: " + image.getParentFile().getAbsolutePath());
				}
			}

			try {
				image.createNewFile();
			} catch (IOException e1) {
				throw new IllegalStateException("Unable to create File: " + image.getName());
			}
		FileOutputStream out;
		try {
			out = new FileOutputStream(image);
		} catch (FileNotFoundException e) {
			throw new IllegalStateException(
			"File not found.", e);
		}

		try {
			org.apache.commons.io.IOUtils.copy(is, out);
		} catch (IOException e) {
			throw new IllegalStateException(
					"Problem copying streams.", e);
		}
	}

	public static InputStream getPresentation(String eventKey,
			String presentationLink) {
		final Apphome appHome = SystemInformationUtils.retrieveBasicSystemInformation();

		final String filePath = appHome.getAppHomePath()
			+ File.separator
			+ "eventdata" + File.separator + eventKey
			+ File.separator
			+ SystemInformationUtils.AppHomeDirectories.PRESENTATION_SLIDES.folderName
			+ File.separator +  presentationLink;

		final File presentationFile = new File(filePath);

		if (presentationFile.isFile() && presentationFile.exists()) {
			try {
				return new FileInputStream(presentationFile);
			} catch (FileNotFoundException e) {
			   throw new IllegalStateException(String.format("Not a valid file at '%s'.", filePath));
			}
		}

		throw new IllegalStateException(String.format("Not a valid file at '%s'.", filePath));

	}
}
