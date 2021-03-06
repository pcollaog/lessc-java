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
 * <pre>
 * $Id$
 * </pre>
 * 
 * @author pcollaog
 * @version $Revision$
 */
public class EvaluateVariableTest extends BaseTest {

	private static Logger logger = LoggerFactory
			.getLogger(EvaluateVariableTest.class);

	@Test
	public void testEvaluateVariable() throws IOException {
		String lessText = loadLessCssFile("test-evaluate-variables");

		logger.info(lessText);

		LessCompiler lessCompiler = new LessCompiler();
		String out = lessCompiler.compile(lessText);

		String expected = "content: \"I am fnord.\";\n";

		Assert.assertEquals(expected, out);
	}
}
