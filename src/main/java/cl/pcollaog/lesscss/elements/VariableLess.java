package cl.pcollaog.lesscss.elements;

import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.apache.commons.lang.StringUtils.remove;
import static org.apache.commons.lang.StringUtils.replace;
import static org.apache.commons.lang.StringUtils.substringAfter;

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

	private static Logger logger = LoggerFactory.getLogger(VariableLess.class);

	/**
	 * busca los candidatos a evaluar variables
	 * 
	 * <pre>
	 * content: @@variable;
	 * </pre>
	 */
	private static final Pattern EVALUATE_VARIABLE_PATTERN = Pattern
			.compile("@(@([\\d\\w-_]+))");

	/**
	 * Busca las variables
	 */
	private static final Pattern VARIABLE_MATCHER = Pattern
			.compile(".*(@[\\w-]*).*");

	private static final Pattern VARIABLE_NAME_VALUE_PATTERN = Pattern.compile(
			"^(@[\\w-]*)\\s*:\\s*(.*);$", Pattern.MULTILINE);

	private static final Pattern VARIABLE_REPLACE_PATTERN = Pattern
			.compile("@[\\w-]+");

	private static Pattern HEX_CSS_PATTER = Pattern
			.compile("(#[\\da-fA-F]{6}|#[\\da-fA-F]{3})\\s*((\\+|\\-)?\\s*(#[\\da-fA-F]{6}|#[\\da-fA-F]{3}))?");

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

			lessText = remove(lessText, raw).trim();

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

		Matcher evaluateVariableMatcher = EVALUATE_VARIABLE_PATTERN
				.matcher(lessText);

		while (evaluateVariableMatcher.find()) {
			String variable = evaluateVariableMatcher.group();
			String variableName = substringAfter(variable, "@");

			if (getLessContext().containsVariable(variableName)) {
				String value = getLessContext().getVariable(variableName);
				value = remove(value, "'").trim();
				String result = getLessContext().getVariable("@".concat(value));

				lessText = replace(lessText, variable, result);

				logger.debug("Variable to evaluate: [{}] value [{}]",
						variableName, result);
			}

		}

		Matcher matcher = VARIABLE_REPLACE_PATTERN.matcher(lessText);
		while (matcher.find()) {
			String variable = matcher.group();
			String result = replaceVariable(variable);

			lessText = replace(lessText, variable, result);

			logger.debug("Variable to replace: [{}] value [{}]", variable,
					result);
		}

		return lessText;
	}

	/**
	 * @param variable
	 */
	private String replaceVariable(String variable) {

		String value = null;
		if (getLessContext().containsVariable(variable)) {
			value = getLessContext().getVariable(variable);

			Matcher matcher = VARIABLE_MATCHER.matcher(value);

			if (matcher.matches()) {
				String toReplace = matcher.group(1);
				String valueToReplace = replaceVariable(toReplace);
				String replacedValue = replace(value, toReplace, valueToReplace);

				replacedValue = operationsProcess(replacedValue);

				return replacedValue;
			} else {
				return value;
			}
		}

		return value;
	}

	/**
	 * @param replacedValue
	 * @return
	 */
	private String operationsProcess(String replacedValue) {

		Matcher matcher = HEX_CSS_PATTER.matcher(replacedValue);

		while (matcher.find()) {

			String firstValue = matcher.group(1);
			String operator = matcher.group(3);
			String secondValue = matcher.group(4);

			if (isEmpty(operator) && isEmpty(secondValue)) {
				return firstValue;
			} else {
				// TODO: refatorize this
				int a = normalizeHexValue(firstValue);
				int b = normalizeHexValue(secondValue);

				int c = 0;
				if ("+".equals(operator)) {
					c = a + b;
				} else {
					c = a - b;
				}

				return integerToCssColor(c);
			}

		}

		return null;
	}

	/**
	 * 
	 * @param cssHexValue
	 * @return int value base hex
	 */
	private int normalizeHexValue(String cssHexValue) {
		String cleanCssHexValue = StringUtils.substringAfter(cssHexValue, "#");
		StringBuffer sb = new StringBuffer();

		if (3 == cleanCssHexValue.length()) {
			for (int i = 0; i < cleanCssHexValue.length(); i++) {
				sb.append(cleanCssHexValue.charAt(i));
				sb.append(cleanCssHexValue.charAt(i));
			}
		} else if (6 == cleanCssHexValue.length()) {
			sb.append(cleanCssHexValue);
		} else {
			// TODO:mejorar manejo de excepciones
			throw new IllegalArgumentException(
					"Wrong value for CSS or HEX color");
		}
		return Integer.parseInt(sb.toString(), 16);
	}

	private String integerToCssColor(int intValue) {
		return "#".concat(Integer.toHexString(intValue));
	}

}
