package cl.pcollaog.lesscss.elements;

import static org.apache.commons.lang.StringUtils.remove;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.pcollaog.lesscss.LessContext;
import cl.pcollaog.lesscss.css.CssDefinition;

/**
 * <p>
 * </p>
 * 
 * @author pcollaog
 */
public class MixinsLess extends AbstractElementLess {

	private static final String COLON = ":";

	private static final String SEMICOLON = ";";

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

		Map<String, CssDefinition> definitions = new LinkedHashMap<String, CssDefinition>();

		while (matcher.find()) {
			String selector = matcher.group(1).trim();
			String definition = matcher.group(2).trim();

			CssDefinition cssDefinition = new CssDefinition(selector,
					definition);

			definitions.put(selector, cssDefinition);

			lessText = remove(lessText, matcher.group(0));
		}

		getLessContext().setDefinitions(definitions);

		return super.preProcess(lessText);
	}

	@Override
	protected String processInternal(String lessText) {
		StringBuilder sb = new StringBuilder("");
		for (String selector : getLessContext().getSelectors()) {
			CssDefinition cssDef = getLessContext().getCssDefinition(selector);

			if (!cssDef.getReferences().isEmpty()) {
				for (String ref : cssDef.getReferences()) {
					CssDefinition cssDefRef = getLessContext()
							.getCssDefinition(ref);
					cssDef.addCssDefinition(cssDefRef);

					logger.debug("CssParsed {}", cssDef.toString());
				}
			}

			sb.append(cssDef);
			sb.append("\n");
		}

		return sb.toString();
	}
}
