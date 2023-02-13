package net.sourceforge.plantuml.tim.iterator;

import java.util.List;

import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.text.TLineType;
import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;

public class CodeIteratorLongComment extends AbstractCodeIterator {

	private final List<StringLocated> logs;

	public CodeIteratorLongComment(CodeIterator source, List<StringLocated> logs) {
		super(source);
		this.logs = logs;
	}

	public StringLocated peek() throws EaterException, EaterExceptionLocated {
		while (true) {
			if (source.peek() == null) {
				return null;
			}
			if (source.peek().getType() != TLineType.COMMENT_LONG_START) {
				return source.peek();
			}
			StringLocated s = null;
			while ((s = source.peek()) != null && s.getTrimmed().getString().endsWith("'/") == false) {
				logs.add(s);
				source.next();
			}
			assert source.peek() == null || s.getTrimmed().getString().endsWith("'/");
			if (source.peek() != null) {
				assert s.getTrimmed().getString().endsWith("'/");
				logs.add(source.peek());
				source.next();
			}
		}

	}
}
