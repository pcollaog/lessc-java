package cl.pcollaog.lesscss.css;

import static org.apache.commons.lang.StringUtils.replace;
import static org.apache.commons.lang.StringUtils.substringBetween;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * </p>
 * 
 * @author pcollaog
 */
public class CssDefinition {

	private String _selector;

	private Map<String, String> _definitions = new LinkedHashMap<String, String>();

	private Set<String> _references = new LinkedHashSet<String>();

	/**
	 * @param selector
	 */
	public CssDefinition(final String selector, final String rawDefinitions) {
		_selector = selector.trim();
		String definition = replace(rawDefinitions, "\n", " ");
		definition = substringBetween(definition, "{", "}").trim();

		for (String def : definition.split(";")) {
			if (StringUtils.contains(def, ':')) {
				String property = StringUtils.substringBefore(def, ":").trim();
				String value = StringUtils.substringAfter(def, ":").trim();
				_definitions.put(property, value);
			} else {
				_references.add(def.trim());
			}
		}

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
