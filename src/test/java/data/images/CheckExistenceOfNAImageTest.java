package data.images;

import junit.framework.Assert;

import org.junit.Test;

import com.devnexus.ting.common.SystemInformationUtils;

public class CheckExistenceOfNAImageTest {

	@Test
	public void testExistenceOfNAImage() throws Exception {

		byte[] picture = SystemInformationUtils.getSpeakerImage(null);

		Assert.assertNotNull(picture);

	}

}
