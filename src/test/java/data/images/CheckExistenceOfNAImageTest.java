package data.images;

import org.junit.Assert;

import org.junit.Test;

import com.devnexus.ting.common.SystemInformationUtils;

public class CheckExistenceOfNAImageTest {

	@Test
	public void testExistenceOfNAImage() throws Exception {

		byte[] picture = SystemInformationUtils.getSpeakerImage(null);

		Assert.assertNotNull(picture);

	}

}
