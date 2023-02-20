package net.sourceforge.plantuml.tim.iterator;

import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;

public interface CodeIterator {

	public StringLocated peek() throws EaterException, EaterExceptionLocated;

	public void next() throws EaterException, EaterExceptionLocated;

	public CodePosition getCodePosition();

	public void jumpToCodePosition(CodePosition newPosition) throws EaterException;

}
