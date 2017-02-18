/*
 * Copyright 2015 the original author or authors.
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
package com.devnexus.ting.web.controller;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.pdfbox.multipdf.LayerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Gunnar Hillert
 */
public class PdfUtils {

	/**
	 *   Initialize Logging.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PdfUtils.class);

	final PDDocument doc;
	final PDFont baseFont;
	final PDFont headerFont;
	final PDFont subHeaderFont;
	final PDDocument devnexusLogo;

	PDPage currentPage = new PDPage();
	PDPageContentStream contents;

	final List<PDPage> pages = new ArrayList<>();
	final float margin;

	final float baseFontSize = 11;
	final float headerFontSize = 18;

	final float lineSpacing = 1.5f;
	float currentLeading;
	final float width;

	final float initialHeightCounter;
	float heightCounter;

	public PdfUtils(float margin, String title) throws IOException {
		this.margin = margin;
		doc = new PDDocument();
		baseFont   = PDType0Font.load(doc, PdfUtils.class.getResourceAsStream("/fonts/Arial.ttf"));
		headerFont = PDType1Font.HELVETICA_BOLD;
		subHeaderFont = PDType1Font.HELVETICA_BOLD;
		devnexusLogo = PDDocument.load(PdfUtils.class.getResourceAsStream("/fonts/devnexus-logo.pdf"));

		this.currentPage = new PDPage();
		this.pages.add(currentPage);
		this.doc.addPage(currentPage);

		final PDRectangle mediabox = currentPage.getMediaBox();
		this.width = mediabox.getWidth() - 2 * margin;

		float startX = mediabox.getLowerLeftX() + margin;
		float startY = mediabox.getUpperRightY() - margin;

		this.initialHeightCounter = startY;
		this.heightCounter = startY;

		LOGGER.info(String.format("Margin: %s, width: %s, startX: %s, "
				+ "startY: %s, heightCounter: %s, baseFontSize: %s, headerFontSize: %s",
				margin, width, startX, startY, heightCounter, baseFont, headerFont));

		contents = new PDPageContentStream(doc, currentPage);

		// Add Logo

		final LayerUtility layerUtility = new LayerUtility(doc);
		final PDFormXObject logo = layerUtility.importPageAsForm(devnexusLogo, 0);
		final AffineTransform affineTransform = AffineTransform.getTranslateInstance(100, startY - 50);
		affineTransform.scale(2d, 2d);
		layerUtility.appendFormAsLayer(currentPage, logo, affineTransform, "devnexus-logo");
		this.heightCounter -= 100;

		this.contents.beginText();

		this.contents.setFont(headerFont, headerFontSize);
		this.currentLeading = this.lineSpacing * baseFontSize;
		this.contents.setLeading(this.currentLeading);

		contents.newLineAtOffset(50, heightCounter);

		println(title);

		this.contents.setFont(baseFont, baseFontSize);
		this.currentLeading = this.lineSpacing * baseFontSize;
		this.contents.setLeading(this.currentLeading);

		println();

	}

	public void setHeightCounter(float heightCounter) {
		this.heightCounter = heightCounter;
	}

	public void println() throws IOException {
		println("");
	}

	float distanceFromZero = 0;

	public void print(float offset, String line) throws IOException {
		distanceFromZero = distanceFromZero + offset;
		contents.newLineAtOffset(offset, 0);
		print(line);
	}

	final Map<Float, String> rectangles = new HashMap<>();

	public void print(float offset, String line, String color ) throws IOException {
		createNewPageIfNecessary();
		LOGGER.info("Adding color '"+ color +"' for heightCounter: " + heightCounter + " " + line + "; Line Offset: " + distanceFromZero);
		rectangles.put(this.heightCounter, color);
		print(offset, line);
	}

	public void print(String line, boolean bold) throws IOException {
		if (bold) {
			this.contents.setFont(headerFont, baseFontSize);
		}

		print(line);

		if (bold) {
			this.contents.setFont(baseFont, baseFontSize);
		}
	}

	public void print(String line) throws IOException {
		createNewPageIfNecessary();

		final List<String> lines = getLines(line);

		if (lines.size() <= 1) {
			contents.showText(line);
		}
		else {
			contents.showText(lines.get(0) + " …");
		}
	}

	void createNewPageIfNecessary() throws IOException{
		if (heightCounter <=50) {
			LOGGER.info("Adding New Page.");

			this.heightCounter = this.initialHeightCounter;
			this.contents.endText();

			for (Entry<Float, String> rectangle : this.rectangles.entrySet()) {
				this.contents.setNonStrokingColor(Color.decode(rectangle.getValue()));
				this.contents.addRect(0, rectangle.getKey()-3, 35, 15);
				this.contents.fill();
				LOGGER.info("Reactangle: " + rectangle.getKey() + "|" +  rectangle.getValue());
			}

			this.rectangles.clear();

			this.contents.close();
			this.currentPage = new PDPage();
			this.pages.add(currentPage);
			this.doc.addPage(currentPage);
			contents = new PDPageContentStream(doc, this.currentPage);
			this.contents.beginText();
			this.contents.setFont(baseFont, baseFontSize);
			this.currentLeading = this.lineSpacing * baseFontSize;
			this.contents.setLeading(this.currentLeading);
			contents.newLineAtOffset(50, heightCounter);
			distanceFromZero = 0;

		}
	}

	public void println(String line) throws IOException {
		println(line, false);
	}

	public void println(String line, boolean bold) throws IOException {
		createNewPageIfNecessary();
		print(line, bold);
		LOGGER.info(heightCounter + " " + line + "; Line Offset: " + distanceFromZero);
		this.heightCounter -= this.currentLeading;

		contents.newLineAtOffset(-distanceFromZero, -this.currentLeading);
		this.distanceFromZero = 0;
	}

	boolean isLandscape(PDPage page) {
		int rotation = page.getRotation();
		final boolean isLandscape;
		if (rotation == 90 || rotation == 270) {
			isLandscape = true;
		} else if (rotation == 0 || rotation == 360 || rotation == 180) {
			isLandscape = false;
		} else {
			LOGGER.warn("Can only handle pages that are rotated in 90 degree steps. This page is rotated {} degrees. Will treat the page as in portrait format", rotation);
			isLandscape = false;
		}
		return isLandscape;
	}

	float getStringWidth(String text, PDFont font, int fontSize) throws IOException {
		return font.getStringWidth(text) * fontSize / 1000F;
	}

	public PDDocument getDoc() {
		return this.doc;
	}

	public void done(OutputStream os) throws IOException {
		this.contents.endText();

		for (Entry<Float, String> rectangle : this.rectangles.entrySet()) {
			this.contents.setNonStrokingColor(Color.decode(rectangle.getValue()));
			this.contents.addRect(0, rectangle.getKey()-3, 35, 15);

			this.contents.fill();
			LOGGER.info("Reactangle: " + rectangle.getKey() + "|" +  rectangle.getValue());
		}

		this.rectangles.clear();
		this.contents.close();

		// Add Page Numbers
		int pageIndex = 1;
		for (PDPage page : this.pages) {

			this.contents = new PDPageContentStream(doc, page, true, true);

			this.contents.beginText();
			this.contents.setFont(baseFont, baseFontSize);
			this.contents.setNonStrokingColor(Color.BLACK);
			this.currentLeading = this.lineSpacing * baseFontSize;
			this.contents.setLeading(this.currentLeading);

			contents.newLineAtOffset(500, 10);
			contents.showText(String.format("Page %s of %s", pageIndex, doc.getNumberOfPages()));

			this.contents.endText();
			pageIndex++;
			this.contents.close();

		}



		this.doc.save(os);
	}

	public float getHeightCounter() {
		return heightCounter;
	}

	public List<String> getLines(String text) throws IOException {

		final List<String> lines = new ArrayList<String>();
		int lastSpace = -1;
		while (text.length() > 0)
		{
			int spaceIndex = text.indexOf(' ', lastSpace + 1);
			if (spaceIndex < 0) {
				spaceIndex = text.length();
			}
			String subString = text.substring(0, spaceIndex);

			final float size;
			try {
				size = this.baseFontSize * this.baseFont.getStringWidth(subString) / 1000;
			}
			catch (IllegalArgumentException e) {
				throw new IllegalStateException("Faulty substring" + text, e);
			}

			if (size > (this.width + 50 - this.distanceFromZero))
			{
				if (lastSpace < 0) {
					lastSpace = spaceIndex;
				}
				subString = text.substring(0, lastSpace);
				lines.add(subString);
				text = text.substring(lastSpace).trim();

				lastSpace = -1;
			}
			else if (spaceIndex == text.length())
			{
				lines.add(text);
				text = "";
			}
			else
			{
				lastSpace = spaceIndex;
			}
		}

		return lines;
	}
}
