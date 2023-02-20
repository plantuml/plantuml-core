package net.sourceforge.plantuml.tim.iterator;

import java.util.List;

import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.text.TLineType;
import net.sourceforge.plantuml.tim.EaterElseIf;
import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;
import net.sourceforge.plantuml.tim.EaterIf;
import net.sourceforge.plantuml.tim.EaterIfdef;
import net.sourceforge.plantuml.tim.EaterIfndef;
import net.sourceforge.plantuml.tim.ExecutionContextIf;
import net.sourceforge.plantuml.tim.TContext;
import net.sourceforge.plantuml.tim.TMemory;

public class CodeIteratorIf extends AbstractCodeIterator {

	private final TContext context;
	private final TMemory memory;
	private final List<StringLocated> logs;

	public CodeIteratorIf(CodeIterator source, TContext context, TMemory memory, List<StringLocated> logs) {
		super(source);
		this.context = context;
		this.memory = memory;
		this.logs = logs;
	}

	public StringLocated peek() throws EaterException, EaterExceptionLocated {
		while (true) {
			final StringLocated result = source.peek();
			if (result == null) {
				return null;
			}
			if (result.getType() == TLineType.IF) {
				logs.add(result);
				executeIf(context, memory, result.getTrimmed());
				next();
				continue;
			} else if (result.getType() == TLineType.IFDEF) {
				logs.add(result);
				executeIfdef(context, memory, result.getTrimmed());
				next();
				continue;
			} else if (result.getType() == TLineType.IFNDEF) {
				logs.add(result);
				executeIfndef(context, memory, result.getTrimmed());
				next();
				continue;
			} else if (result.getType() == TLineType.ELSE) {
				logs.add(result);
				executeElse(context, memory, result.getTrimmed());
				next();
				continue;
			} else if (result.getType() == TLineType.ELSEIF) {
				logs.add(result);
				executeElseIf(context, memory, result.getTrimmed());
				next();
				continue;
			} else if (result.getType() == TLineType.ENDIF) {
				logs.add(result);
				executeEndif(context, memory, result.getTrimmed());
				next();
				continue;
			} else if (memory.peekIf() != null && (memory.areAllIfOk(context, memory) == false)) {
				logs.add(result);
				next();
				continue;
			}

			return result;
		}
	}

	private void executeIf(TContext context, TMemory memory, StringLocated s)
			throws EaterException, EaterExceptionLocated {
		final EaterIf condition = new EaterIf(s);
		condition.analyze(context, memory);
		final boolean isTrue = condition.isTrue();
		memory.addIf(ExecutionContextIf.fromValue(isTrue));
	}

	private void executeElseIf(TContext context, TMemory memory, StringLocated s)
			throws EaterException, EaterExceptionLocated {
		final ExecutionContextIf poll = (ExecutionContextIf) memory.peekIf();
		if (poll == null) {
			throw EaterException.located("No if related to this else");
		}

		poll.enteringElseIf();
		if (poll.hasBeenBurn() == false) {
			final EaterElseIf condition = new EaterElseIf(s);
			condition.analyze(context, memory);
			final boolean isTrue = condition.isTrue();
			if (isTrue) {
				poll.nowInSomeElseIf();
			}
		}
	}

	private void executeIfdef(TContext context, TMemory memory, StringLocated s) throws EaterException {
		final EaterIfdef condition = new EaterIfdef(s);
		condition.analyze(context, memory);
		final boolean isTrue = condition.isTrue(context, memory);
		memory.addIf(ExecutionContextIf.fromValue(isTrue));
	}

	private void executeIfndef(TContext context, TMemory memory, StringLocated s) throws EaterException {
		final EaterIfndef condition = new EaterIfndef(s);
		condition.analyze(context, memory);
		final boolean isTrue = condition.isTrue(context, memory);
		memory.addIf(ExecutionContextIf.fromValue(isTrue));
	}

	private void executeElse(TContext context, TMemory memory, StringLocated s) throws EaterException {
		final ExecutionContextIf poll = (ExecutionContextIf) memory.peekIf();
		if (poll == null) {
			throw EaterException.located("No if related to this else");
		}
		poll.nowInElse();
	}

	private void executeEndif(TContext context, TMemory memory, StringLocated s) throws EaterException {
		final ExecutionContextIf poll = (ExecutionContextIf) memory.pollIf();
		if (poll == null) {
			throw EaterException.located("No if related to this endif");
		}
	}

}
