package cl.pcollaog.lesscss;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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

	private Map<String, String> _definitions = new LinkedHashMap<String, String>();

	/**
	 * @param variables
	 *            the variables to set
	 */
	public final void setVariables(Map<String, String> variables) {
		_variables = variables;
		logger.debug("Adding Variables [{}]", variables);
	}

	/**
	 * @param definitions
	 *            the definitions to set
	 */
	public final void setDefinitions(Map<String, String> definitions) {
		_definitions = definitions;
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

	/**
	 * 
	 * @param selector
	 * @return
	 */
	public boolean containsCssDefinition(String selector) {
		return _definitions.containsKey(selector);
	}

	/**
	 * 
	 * @return
	 */
	public Set<String> getSelectors() {
		return _definitions.keySet();
	}

	/**
	 * @param selector
	 * @return
	 */
	public String getCssDefinition(String selector) {
		return _definitions.get(selector);
	}

}
