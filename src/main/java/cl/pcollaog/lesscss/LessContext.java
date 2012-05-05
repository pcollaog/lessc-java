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
	 * @param variables
	 *            the variables to set
	 */
	public final void setVariables(Map<String, String> variables) {
		_variables = variables;
	}

	/**
	 * 
	 * @param variableName
	 * @return
	 */
	public String getVariable(String variableName) {
		return _variables.get(variableName);
	}

	/**
	 * @param variableName
	 * @return
	 */
	public boolean containsVariable(String variableName) {
		return _variables.containsKey(variableName);
	}

}
