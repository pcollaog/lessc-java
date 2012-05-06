package cl.pcollaog.lesscss;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * </p>
 */
public class VariableTest {

	private static Logger logger = LoggerFactory.getLogger(VariableTest.class);

	@Test
	public void testLessCss() throws IOException {
		String lessText = IOUtils.toString(getClass().getResourceAsStream(
				"/test-variables.less"));

		logger.info(lessText);

		LessCompiler lessCompiler = new LessCompiler();
		String out = lessCompiler.compile(lessText);

		String expected = "#header { color: #6c94be; }\n";

		Assert.assertEquals(expected, out);
	}

}
