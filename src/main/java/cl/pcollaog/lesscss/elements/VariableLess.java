package cl.pcollaog.lesscss.elements;

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
 * 
 * <pre>
 * $Id$
 * </pre>
 * 
 * @author pcollaog
 * @version $Revision$
 */
public class VariableLess extends AbstractElementLess {

	private static final Pattern VARIABLE_MATCHER = Pattern
			.compile(".*(@[\\d\\w-_]*).*");

	private static Logger logger = LoggerFactory.getLogger(VariableLess.class);

	private static final Pattern VARIABLE_NAME_VALUE_PATTERN = Pattern.compile(
			"^(@[\\d\\w-_]*)\\s*:(.*);$", Pattern.MULTILINE);

	private static final Pattern VARIABLE_REPLACE_PATTERN = Pattern
			.compile("@[\\d\\w-_]*");

	/**
	 * @param lessContext
	 */
	public VariableLess(LessContext lessContext) {
		super(lessContext);
	}

	@Override
	protected String preProcess(String lessText) {
		Matcher matcher = VARIABLE_NAME_VALUE_PATTERN.matcher(lessText);

		Map<String, String> lessVariables = new LinkedHashMap<String, String>();

		while (matcher.find()) {
			String raw = matcher.group(0);
			String name = matcher.group(1);
			String value = matcher.group(2);

			lessVariables.put(name, value);

			lessText = StringUtils.remove(lessText, raw);

			if (logger.isDebugEnabled()) {
				logger.debug("Raw: [" + raw + "]");
				logger.debug("Variable Name: [" + name + "]");
				logger.debug("Variable Value: [" + value + "]");
			}
		}
		getLessContext().setVariables(lessVariables);
		return super.preProcess(lessText);
	}

	@Override
	protected String processInternal(String lessText) {
		Matcher matcher = VARIABLE_REPLACE_PATTERN.matcher(lessText);

		while (matcher.find()) {
			String variable = matcher.group();
			String result = replaceVariable(variable);

			logger.debug("Variable to replace: [" + variable + "] value ["
					+ result + "]");
		}

		return lessText;
	}

	/**
	 * @param variable
	 */
	private String replaceVariable(String variable) {

		String value = null;
		if (getLessContext().getVariables().containsKey(variable)) {
			value = getLessContext().getVariables().get(variable);

			Matcher matcher = VARIABLE_MATCHER.matcher(value);

			if (matcher.matches()) {

				String toReplace = matcher.group(1);
				String valueToReplace = replaceVariable(toReplace);

				return StringUtils.replace(value, toReplace, valueToReplace);
			} else {
				return value;
			}
		}

		return value;
	}

	@Override
	protected String postProcess(String result) {
		return super.postProcess(result);
	}

}
