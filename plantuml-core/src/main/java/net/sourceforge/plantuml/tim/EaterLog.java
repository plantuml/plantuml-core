// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.utils.Log;

public class EaterLog extends Eater {

	public EaterLog(StringLocated s) {
		super(s);
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException, EaterExceptionLocated {
		skipSpaces();
		checkAndEatChar("!log");
		skipSpaces();
		final String logData = context.applyFunctionsAndVariables(memory, getLineLocation(), this.eatAllToEnd());
		Log.error("[Log] " + logData);
	}

}
