package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.tim.expression.TValue;

public class EaterElseIf extends Eater {

	private boolean booleanValue;

	public EaterElseIf(StringLocated s) {
		super(s);
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException, EaterExceptionLocated {
		skipSpaces();
		checkAndEatChar("!elseif");
		skipSpaces();
		final TValue value = eatExpression(context, memory);
		this.booleanValue = value.toBoolean();
	}

	public boolean isTrue() {
		return this.booleanValue;
	}

}
