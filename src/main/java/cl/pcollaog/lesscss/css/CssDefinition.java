package cl.pcollaog.lesscss.css;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * <p>
 * </p>
 * 
 * @author pcollaog
 */
public class CssDefinition {

	private String _selector;

	private Map<String, String> _definitions;

	private Set<String> _references;

	/**
	 * @param selector
	 */
	public CssDefinition(String selector, String rawDefinitions) {
		_selector = selector.trim();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(_selector);
		sb.append("{");
		for (Entry<String, String> entry : _definitions.entrySet()) {
			sb.append(entry.getKey());
			sb.append(":");
			sb.append(entry.getValue());
			sb.append(";");
		}
		sb.append("}");
		return sb.toString();
	}

}
