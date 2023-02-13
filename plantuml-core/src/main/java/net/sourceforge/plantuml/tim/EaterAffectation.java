package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.tim.expression.TValue;

public class EaterAffectation extends Eater {

	public EaterAffectation(StringLocated sl) {
		super(sl.getTrimmed());
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException, EaterExceptionLocated {
		skipSpaces();
		checkAndEatChar("!");
		skipSpaces();
		String varname = eatAndGetVarname();
		TVariableScope scope = null;
		skipSpaces();
		boolean conditional = false;
		if (peekChar() == '?') {
			checkAndEatChar('?');
			conditional = true;
		}
		if (peekChar() != '=') {
			scope = TVariableScope.valueOf(varname.toUpperCase());
			varname = eatAndGetVarname();
			skipSpaces();
		}
		checkAndEatChar('=');
		if (conditional) {
			final TValue already = memory.getVariable(varname);
			if (already != null) {
				return;
			}
		}
		skipSpaces();
		final TValue value = eatExpression(context, memory);
		memory.putVariable(varname, value, scope);
	}

}
