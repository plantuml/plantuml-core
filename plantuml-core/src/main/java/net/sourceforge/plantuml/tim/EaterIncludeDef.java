package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.text.StringLocated;

public class EaterIncludeDef extends Eater {

	private String location;

	public EaterIncludeDef(StringLocated s) {
		super(s);
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException, EaterExceptionLocated {
		skipSpaces();
		checkAndEatChar("!includedef");
		skipSpaces();
		this.location = context.applyFunctionsAndVariables(memory, getLineLocation(), this.eatAllToEnd());

	}

	public final String getLocation() {
		return location;
	}

}
