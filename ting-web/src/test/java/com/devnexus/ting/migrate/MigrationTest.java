package com.devnexus.ting.migrate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.devnexus.ting.core.dao.DocumentDao;
import com.devnexus.ting.core.dao.SpeakerDao;
import com.devnexus.ting.core.service.BusinessService;

@ContextConfiguration(
		locations={ "classpath:spring/mainApplicationContext.xml"})
@ActiveProfiles("default")
public class MigrationTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private SpeakerDao speakerDao;
	@Autowired
	private DocumentDao documentDao;
	protected @PersistenceContext(unitName="base") EntityManager entityManager;


	@Autowired BusinessService businessService;

	@Test
	public void dummyTest() {
	}

//	//@Test
//	@Rollback(value=false)
//	public void migrateSpeakerPictures() throws IOException {
//
//		List<Speaker> speakers = speakerDao.getAll();
//
//		System.out.println(speakers.size());
//		Assert.assertNotNull(speakers);
//
//		final Apphome appHome = SystemInformationUtils
//		.retrieveBasicSystemInformation();
//
//		for (Speaker speaker : speakers) {
//			FileData document = new FileData();
//
//			final File image = new File(
//					appHome.getAppHomePath()
//							+ File.separator
//							+ SystemInformationUtils.AppHomeDirectories.SPEAKER_IMAGES.getFolderName()
//							+ File.separator + File.separator + speaker.getPicture());
//
//			if (image.isFile() && image.exists()) {
//
//				byte[] data = IOUtils.toByteArray(new FileInputStream(image));
//
//				document.setFileModified(new Date(image.lastModified()));
//				document.setName(speaker.getPicture());
//				document.setFileData(data);
//				document.setFileSize(image.length());
//				document.setType("image/jpg");
//				FileData saved = documentDao.save(document);
//				speaker.setPictureData(saved);
//
//				speakerDao.save(speaker);
//				entityManager.flush();
//
//			}
//
//		}
//
//	}


}
