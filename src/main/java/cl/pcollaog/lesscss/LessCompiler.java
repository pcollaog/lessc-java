package cl.pcollaog.lesscss;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.pcollaog.lesscss.elements.AbstractElementLess;
import cl.pcollaog.lesscss.elements.MixinsLess;
import cl.pcollaog.lesscss.elements.VariableLess;

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
public class LessCompiler {

	private static Logger logger = LoggerFactory.getLogger(LessCompiler.class);

	private static final Pattern CSS_COMMENT_PATTERN = Pattern.compile(
			"(?s)/\\*.*?\\*/", Pattern.MULTILINE);

	private boolean _pretty = true;

	/**
	 * 
	 */
	public LessCompiler() {
	}

	/**
	 * @param lessText
	 */
	public String compile(final String lessText) {

		String result = CSS_COMMENT_PATTERN.matcher(lessText).replaceAll("")
				.trim();

		LessContext lessContext = new LessContext();

		AbstractElementLess less = new VariableLess(lessContext);
		result = less.process(result);

		less = new MixinsLess(lessContext);

		result = less.process(result);

		if (_pretty) {
			result = StringUtils.replace(result.trim(), ";", ";\n");
		}

		logger.debug("Result CssLess \n{}", result);

		return result;
	}

}
