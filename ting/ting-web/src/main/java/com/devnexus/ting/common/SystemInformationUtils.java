package com.devnexus.ting.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import com.devnexus.ting.common.AppHomeSource;
import com.devnexus.ting.common.SpringContextMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public final class SystemInformationUtils {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(SystemInformationUtils.class);

	public static int CONSOLE_SPACER_WIDTH = 40;
	public static String CONSOLE_SPACER_CHARACTER = ".";
	public static String TING_CONFIG_FILENAME = "ting.properties";

	public enum AppHomeDirectories {

		SPEAKER_IMAGES("/images"), PRESENTATION_SLIDES("/slides"), PRESENTATION_AUDIO(
				"/audio");

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

		if (StringUtils.isNotBlank(System
				.getProperty(Apphome.APP_HOME_DIRECTORY))) {

			apphome.setAppHomePath(System
					.getProperty(Apphome.APP_HOME_DIRECTORY));
			apphome.setAppHomeSource(AppHomeSource.SYSTEM_PROPERTY);

		} else if (StringUtils.isNotBlank(System
				.getenv(Apphome.APP_HOME_DIRECTORY))) {

			apphome.setAppHomePath(System
					.getProperty(Apphome.APP_HOME_DIRECTORY));
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

		final File configFile = new File(filePath + File.separator
				+ TING_CONFIG_FILENAME);

		return configFile.exists() && configFile.isFile();

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

	/**
	 *
	 * @return
	 */
	public static InputStream getSpeakerImage(String imageName) {

		final Apphome appHome = SystemInformationUtils
				.retrieveBasicSystemInformation();

		if (imageName == null) {
			return SystemInformationUtils.class
					.getResourceAsStream("/data/images/image_not_available.jpg");
		}

		final File image = new File(
				appHome.getAppHomePath()
						+ File.separator
						+ SystemInformationUtils.AppHomeDirectories.SPEAKER_IMAGES.folderName
						+ File.separator + File.separator + imageName);

		if (image.isFile() && image.exists()) {
			try {
				return new FileInputStream(image);
			} catch (FileNotFoundException e) {
				// I guess I should never get here

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
	public static void setSpeakerImage(String imageName, InputStream is) {

		final Apphome appHome = SystemInformationUtils
				.retrieveBasicSystemInformation();

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
				image.getParentFile().mkdir();
			}

			try {
				image.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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

		final File presentationFile = new File(
				appHome.getAppHomePath()
						+ File.separator
						+ "eventdata" + File.separator + eventKey
						+ File.separator
						+ SystemInformationUtils.AppHomeDirectories.PRESENTATION_SLIDES.folderName
						+ File.separator +  presentationLink);

		if (presentationFile.isFile() && presentationFile.exists()) {
			try {
				return new FileInputStream(presentationFile);
			} catch (FileNotFoundException e) {
               throw new IllegalStateException("Not a valid file.");
			}
		}

		throw new IllegalStateException("Not a valid file.");

	}
}
