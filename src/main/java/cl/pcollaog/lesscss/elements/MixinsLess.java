package cl.pcollaog.lesscss.elements;

import static org.apache.commons.lang.StringUtils.contains;
import static org.apache.commons.lang.StringUtils.replace;
import static org.apache.commons.lang.StringUtils.substringBetween;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
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
	protected String preProcess(String lessText) {
		Matcher matcher = CSS_BLOCK_PATTERN.matcher(lessText);

		Map<String, String> definitions = new LinkedHashMap<String, String>();

		while (matcher.find()) {
			String selector = matcher.group(1).trim();
			String definition = matcher.group(2);

			logger.debug("selector: [{}]", selector);
			logger.debug("definition: [{}]", definition);

			definition = replace(definition, "\n", " ");
			definition = substringBetween(definition, "{", "}").trim();

			definitions.put(selector, definition);

			lessText = StringUtils.remove(lessText, matcher.group(0));
		}

		getLessContext().setDefinitions(definitions);

		return super.preProcess(lessText);
	}

	@Override
	protected String processInternal(String lessText) {
		StringBuilder sbOut = new StringBuilder(lessText);

		for (String selector : getLessContext().getSelectors()) {
			String definition = getLessContext().getCssDefinition(selector);

			StringBuilder sbCssDef = new StringBuilder(selector);
			sbCssDef.append("{");

			logger.debug("selector [{}]", selector);
			logger.debug("Css definition [{}]", definition);

			String[] defs = definition.split(";");

			for (String def : defs) {
				if (contains(def.trim(), ":")) {
					sbCssDef.append(def.trim());
					sbCssDef.append(";");
				} else {

				}
			}
			sbCssDef.append("}");
			sbCssDef.append("\n");

			sbOut.append(sbCssDef);
		}

		return sbOut.toString();
	}
}
