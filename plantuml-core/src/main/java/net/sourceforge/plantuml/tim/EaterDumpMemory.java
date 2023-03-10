// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.text.StringLocated;

public class EaterDumpMemory extends Eater {

	public EaterDumpMemory(StringLocated s) {
		super(s);
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException {
		skipSpaces();
		checkAndEatChar("!dump_memory");
		skipSpaces();
		final String remain = this.eatAllToEnd();
		memory.dumpDebug(remain);
		// final String logData = context.applyFunctionsAndVariables(memory, remain);
		// Log.error(logData);
	}

}
