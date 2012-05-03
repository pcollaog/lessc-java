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
public class LessCssTest {

	private static Logger logger = LoggerFactory.getLogger(LessCssTest.class);

	@Test
	public void testLessCss() throws IOException {
		String lessText = IOUtils.toString(getClass().getResourceAsStream(
				"/test-variables.less"));

		logger.info(lessText);

		LessCompiler lessCompiler = new LessCompiler();
		String out = lessCompiler.compile(lessText);

		String expected = "#header { color: #6c94be; }";

		int x = Integer.parseInt("5B83AD", 16);

		int y = Integer.parseInt("111111", 16);

		int o = x + y;

		String xx = Integer.toHexString(o);

		logger.info(xx);

		Assert.assertEquals(expected, out);
	}

}
