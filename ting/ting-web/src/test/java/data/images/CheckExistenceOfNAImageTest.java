package data.images;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import junit.framework.Assert;

import org.junit.Test;

import com.devnexus.ting.common.SystemInformationUtils;

public class CheckExistenceOfNAImageTest {

	@Test
	public void testExistenceOfNAImage() throws Exception {

		InputStream io = SystemInformationUtils.getSpeakerImage(null);

		Assert.assertNotNull(io);

	}

}
