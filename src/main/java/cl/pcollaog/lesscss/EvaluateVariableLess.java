package cl.pcollaog.lesscss;

import cl.pcollaog.lesscss.elements.AbstractElementLess;

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
public class EvaluateVariableLess extends AbstractElementLess {

	/**
	 * @param lessContext
	 */
	public EvaluateVariableLess(LessContext lessContext) {
		super(lessContext);
	}

	@Override
	protected String processInternal(String lessText) {
		return lessText;
	}

}
