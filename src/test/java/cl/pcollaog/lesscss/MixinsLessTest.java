package cl.pcollaog.lesscss;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

/**
 * <p>
 * </p>
 * 
 * @author pcollaog
 */
public class MixinsLessTest extends BaseTest {

	private static final String FILENAME_TEST = "test-mixins";

	@Test
	public void testEvaluateVariable() throws IOException {
		String lessText = loadLessCssFile(FILENAME_TEST);

		LessCompiler lessCompiler = new LessCompiler();
		String out = lessCompiler.compile(lessText);

		String expected = loadResultCssFile(FILENAME_TEST);

		Assert.assertEquals(expected, out);
	}
}
