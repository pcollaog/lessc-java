package cl.pcollaog.lesscss;

import java.util.LinkedHashMap;
import java.util.Map;

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
public class LessContext {

	private static Logger logger = LoggerFactory.getLogger(LessContext.class);

	private Map<String, String> _variables = new LinkedHashMap<String, String>();

	/**
	 * @return the variables
	 */
	public final Map<String, String> getVariables() {
		return _variables;
	}

	/**
	 * @param variables
	 *            the variables to set
	 */
	public final void setVariables(Map<String, String> variables) {
		_variables = variables;
	}

}
