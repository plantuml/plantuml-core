package net.sourceforge.plantuml.tim.iterator;

import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;

public abstract class AbstractCodeIterator implements CodeIterator {

	protected final CodeIterator source;

	public AbstractCodeIterator(CodeIterator source) {
		this.source = source;
	}

	public void next() throws EaterException, EaterExceptionLocated {
		source.next();
	}

	final public CodePosition getCodePosition() {
		return source.getCodePosition();
	}
	
	final public void jumpToCodePosition(CodePosition newPosition) throws EaterException {
		source.jumpToCodePosition(newPosition);
	}


}
