package cl.pcollaog.lesscss;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * </p>
 */
public class VariableTest extends BaseTest {

	private static final String FILENAME_TEST = "test-variables";

	private static Logger logger = LoggerFactory.getLogger(VariableTest.class);

	@Test
	public void testLessCss() throws IOException {
		String lessText = loadLessCssFile(FILENAME_TEST);

		LessCompiler lessCompiler = new LessCompiler();
		String out = lessCompiler.compile(lessText);

		String expected = loadResultCssFile(FILENAME_TEST);

		Assert.assertEquals(expected, out);
	}

}
