package cl.pcollaog.lesscss;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * </p>
 * 
 * @author pcollaog
 */
public class MixinsLessTest extends BaseTest {

	private static Logger logger = LoggerFactory
			.getLogger(MixinsLessTest.class);

	@Test
	public void testEvaluateVariable() throws IOException {
		String lessText = loadLessCssFile("test-mixins.less");

		LessCompiler lessCompiler = new LessCompiler();
		String out = lessCompiler.compile(lessText);

		String expected = "content: \"I am fnord.\";\n";

		Assert.assertEquals(expected, out);
	}
}
