package cl.pcollaog.lesscss;

import java.util.HashMap;
import java.util.Map;

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

	private Map<String, String> _lessVariables = new HashMap<String, String>();

	/**
	 * @param name
	 * @param value
	 */
	public void addVariable(String name, String value) {
		_lessVariables.put(name, value);
	}

}
