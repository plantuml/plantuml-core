package net.sourceforge.plantuml.tim.iterator;

import java.util.List;

import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.text.TLineType;
import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;

public class CodeIteratorShortComment extends AbstractCodeIterator {

	private final List<StringLocated> logs;

	public CodeIteratorShortComment(CodeIterator source, List<StringLocated> logs) {
		super(source);
		this.logs = logs;
	}

	public StringLocated peek() throws EaterException, EaterExceptionLocated {
		while (true) {
			final StringLocated result = source.peek();
			if (result == null) {
				return null;
			}
			if (result.getType() == TLineType.COMMENT_SIMPLE) {
				logs.add(result);
				next();
				continue;
			}
			assert result != null && result.getType() != TLineType.COMMENT_SIMPLE;
			return result;
		}
	}
}
