package net.sourceforge.plantuml.tim.iterator;

import java.util.Collections;
import java.util.Map;

import net.sourceforge.plantuml.preproc.Sub;
import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.text.TLineType;
import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;
import net.sourceforge.plantuml.tim.EaterStartsub;
import net.sourceforge.plantuml.tim.TContext;
import net.sourceforge.plantuml.tim.TMemory;

public class CodeIteratorSub extends AbstractCodeIterator {

	private final Map<String, Sub> subs;

	private CodeIterator readingInProgress;

	private final TMemory memory;
	private final TContext context;

	public CodeIteratorSub(CodeIterator source, Map<String, Sub> subs, TContext context, TMemory memory) {
		super(source);
		this.context = context;
		this.memory = memory;
		this.subs = subs;
	}

	public Map<String, Sub> getSubs() {
		return Collections.unmodifiableMap(subs);
	}

	public StringLocated peek() throws EaterException, EaterExceptionLocated {
		if (readingInProgress != null) {
			return readingInProgress.peek();
		}
		StringLocated result = source.peek();
		if (result == null) {
			return null;
		}
		if (result.getType() == TLineType.STARTSUB) {
			final EaterStartsub eater = new EaterStartsub(result.getTrimmed());
			eater.analyze(context, memory);
			final Sub created = new Sub(eater.getSubname());
			this.subs.put(eater.getSubname(), created);
			source.next();
			StringLocated s = null;
			while ((s = source.peek()) != null) {
				if (s.getType() == TLineType.STARTSUB) {
					throw EaterException.located("Cannot nest sub");
				} else if (s.getType() == TLineType.ENDSUB) {
					source.next();
					readingInProgress = new CodeIteratorImpl(created.lines());
					break;
				} else {
					created.add(s);
					source.next();
				}
			}
		}
		if (readingInProgress != null) {
			return readingInProgress.peek();
		}
		return result;
	}

	@Override
	public void next() throws EaterException, EaterExceptionLocated {
		if (readingInProgress == null) {
			source.next();
			return;
		}
		readingInProgress.next();
		if (readingInProgress.peek() == null) {
			readingInProgress = null;
		}
	}

}
