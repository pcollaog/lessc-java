package cl.pcollaog.lesscss;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pcollaog
 * 
 */
public abstract class BaseTest {

	private static Logger logger = LoggerFactory.getLogger(BaseTest.class);

	protected String loadLessCssFile(final String filename) throws IOException {
		String filePath = "/lesscss/".concat(filename).concat(".less");
		logger.debug("Reading file [{}]", filePath);
		return IOUtils.toString(getClass().getResourceAsStream(filePath));
	}

	protected String loadResultCssFile(final String filename)
			throws IOException {
		String filePath = "/lesscss/compiled/".concat(filename).concat(".css");
		logger.debug("Reading file [{}]", filePath);
		return IOUtils.toString(getClass().getResourceAsStream(filePath));
	}
}
