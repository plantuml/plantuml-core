package net.sourceforge.plantuml.tim.iterator;

import java.util.List;

import net.sourceforge.plantuml.json.ParseException;
import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.text.TLineType;
import net.sourceforge.plantuml.tim.EaterAffectation;
import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;
import net.sourceforge.plantuml.tim.TContext;
import net.sourceforge.plantuml.tim.TMemory;

public class CodeIteratorAffectation extends AbstractCodeIterator {

	private final TContext context;
	private final TMemory memory;
	private final List<StringLocated> logs;

	public CodeIteratorAffectation(CodeIterator source, TContext context, TMemory memory, List<StringLocated> log) {
		super(source);
		this.context = context;
		this.memory = memory;
		this.logs = log;
	}

	public StringLocated peek() throws EaterException, EaterExceptionLocated {
		while (true) {
			final StringLocated result = source.peek();
			if (result == null) {
				return null;
			}
			if (result.getType() == TLineType.AFFECTATION) {
				logs.add(result);
				doAffectation(result);
				next();
				continue;
			}
			return result;
		}
	}

	private void doAffectation(StringLocated result) throws EaterException, EaterExceptionLocated {
		int lastLocation = -1;
		for (int i = 0; i < 9999; i++)
			try {
				this.executeAffectation(context, memory, result);
				return;
			} catch (ParseException e) {
				if (e.getColumn() <= lastLocation) {
					throw EaterException.located("Error in JSON format");
				}
				lastLocation = e.getColumn();
				next();
				final StringLocated forward = source.peek();
				result = result.append(forward.getString());
			}
	}

	private void executeAffectation(TContext context, TMemory memory, StringLocated s) throws EaterException, EaterExceptionLocated {
		new EaterAffectation(s).analyze(context, memory);
	}

}
