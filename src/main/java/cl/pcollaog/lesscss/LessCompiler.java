package cl.pcollaog.lesscss;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class LessCompiler {

	private static Logger logger = LoggerFactory.getLogger(LessCompiler.class);

	private static final Pattern VARIABLE_PATTERN = Pattern.compile(
			"^(@[\\d\\w-_]*)\\s*:(.*);$", Pattern.MULTILINE);

	private LessContext _lessContext;

	/**
	 * 
	 */
	public LessCompiler() {
		_lessContext = new LessContext();
	}

	/**
	 * @param lessText
	 */
	public String compile(String lessText) {
		processVariables(lessText);
		return lessText;
	}

	private void processVariables(String lessText) {
		Matcher matcher = VARIABLE_PATTERN.matcher(lessText);

		while (matcher.find()) {
			String raw = matcher.group(0);
			String name = matcher.group(1);
			String value = matcher.group(2);

			_lessContext.addVariable(name, value);

			if (logger.isDebugEnabled()) {
				logger.debug("Raw: [" + raw + "]");
				logger.debug("Variable Name: [" + name + "]");
				logger.debug("Variable Value: [" + value + "]");
			}
		}
	}

}
