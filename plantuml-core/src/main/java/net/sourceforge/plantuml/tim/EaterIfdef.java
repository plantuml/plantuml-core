package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.preproc.EvalBoolean;
import net.sourceforge.plantuml.preproc.Truth;
import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.tim.expression.TValue;

public class EaterIfdef extends Eater {

	private String expression;

	public EaterIfdef(StringLocated s) {
		super(s);
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException {
		skipSpaces();
		checkAndEatChar("!ifdef");
		skipSpaces();
		expression = eatAllToEnd();
	}

	public boolean isTrue(final TContext context, final TMemory memory) {
		final EvalBoolean eval = new EvalBoolean(expression, new Truth() {

			public boolean isTrue(String varname) {
				final TValue currentValue = memory.getVariable(varname);
				return currentValue != null || context.doesFunctionExist(varname);
			}
		});

		return eval.eval();
	}

}
