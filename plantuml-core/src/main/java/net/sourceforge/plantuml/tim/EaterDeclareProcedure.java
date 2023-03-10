// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.utils.LineLocation;

public class EaterDeclareProcedure extends Eater {

	private TFunctionImpl function;
	private final LineLocation location;
	private boolean finalFlag;

	public EaterDeclareProcedure(StringLocated s) {
		super(s.getTrimmed());
		this.location = s.getLocation();
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException, EaterExceptionLocated {
		skipSpaces();
		checkAndEatChar("!");
		boolean unquoted = false;
		while (peekUnquoted() || peekFinal()) {
			if (peekUnquoted()) {
				checkAndEatChar("unquoted");
				skipSpaces();
				unquoted = true;
			} else if (peekFinal()) {
				checkAndEatChar("final");
				skipSpaces();
				finalFlag = true;
			}
		}
		checkAndEatChar("procedure");
		skipSpaces();
		function = eatDeclareProcedure(context, memory, unquoted, location);
	}

	private boolean peekUnquoted() {
		return peekChar() == 'u';
	}

	private boolean peekFinal() {
		return peekChar() == 'f' && peekCharN2() == 'i';
	}

	public TFunctionImpl getFunction() {
		return function;
	}

	public final boolean getFinalFlag() {
		return finalFlag;
	}

}
