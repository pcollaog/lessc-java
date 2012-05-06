package cl.pcollaog.lesscss.elements;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.pcollaog.lesscss.LessContext;

/**
 * <p>
 * </p>
 * <p>
 * $Id$
 * </p>
 * 
 * @author pcollaog
 * @version $Revision$
 */
public class MixinsLess extends AbstractElementLess {

	private static final Logger logger = LoggerFactory
			.getLogger(MixinsLess.class);

	private static final Pattern CSS_BLOCK_PATTERN = Pattern.compile(
			"([^\\}\\{]+)(\\{[^\\}]+\\})", Pattern.MULTILINE);

	/**
	 * @param lessContext
	 */
	public MixinsLess(LessContext lessContext) {
		super(lessContext);
	}

	@Override
	protected String processInternal(String lessText) {

		// content = content.replaceAll("(?s)/\\*.*?\\*/", ""); //This is to
		// remove the comments from the CSS

		Matcher matcher = CSS_BLOCK_PATTERN.matcher(lessText);

		Map<String, String> definitions = new LinkedHashMap<String, String>();

		while (matcher.find()) {
			String selector = matcher.group(1);
			String definition = matcher.group(2);

			logger.debug("selector: [{}]", selector);
			logger.debug("definition: [{}]", definition);

			definitions.put(selector, definition);
		}

		getLessContext().setDefinitions(definitions);

		return null;
	}
}
