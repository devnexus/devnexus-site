/**
 *
 */
package com.devnexus.ting.core.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.devnexus.ting.core.model.FileData;

/**
 * @author Gunnar Hillert
 */
public class DocumentDaoTest extends BaseDaoIntegrationTest {

	@Autowired private DocumentDao documentDao;

	/**
	 * Test to verify that the seed data is correctly populated. Typically there
	 * should be 10 industries in the system:
	 *
	 */
	@Test
	public void testInsertDocument() throws IOException {

		FileData document = new FileData();
		document.setFileModified(new Date());
		document.setName("cartman.jpg");
		document.setType("image/jpeg");

		InputStream is = DocumentDaoTest.class.getResourceAsStream("/images/speakers/hans_dockter.jpg");

		byte[] imageData = IOUtils.toByteArray(is);
		document.setFileSize(Long.valueOf(imageData.length));
		document.setFileData(imageData);

		FileData savedDocument = documentDao.save(document);
		super.entityManager.flush();

		FileData docFromDb = documentDao.get(savedDocument.getId());

		Assert.assertEquals("cartman.jpg", docFromDb.getName());

	}

}
