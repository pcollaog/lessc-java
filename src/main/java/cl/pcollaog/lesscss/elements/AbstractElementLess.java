package cl.pcollaog.lesscss.elements;

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
public abstract class AbstractElementLess {

	private LessContext _lessContext;

	/**
	 * @param lessContext
	 */
	public AbstractElementLess(LessContext lessContext) {
		_lessContext = lessContext;
	}

	/**
	 * @param lessText
	 * @return
	 */
	public final String process(final String lessText) {
		String result = preProcess(lessText);
		result = processInternal(result);
		result = postProcess(result);
		return result.trim();
	}

	/**
	 * @param result
	 * @return
	 */
	protected String postProcess(String result) {
		return result;
	}

	/**
	 * @param lessText
	 * @return
	 */
	protected String preProcess(String lessText) {
		return lessText;
	}

	/**
	 * @return the lessContext
	 */
	protected final LessContext getLessContext() {
		return _lessContext;
	}

	protected abstract String processInternal(String lessText);

}
