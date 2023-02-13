package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.text.StringLocated;

public class EaterLegacyDefine extends Eater {

	private TFunctionImpl function;

	public EaterLegacyDefine(StringLocated s) {
		super(s.getTrimmed());
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException, EaterExceptionLocated {
		skipSpaces();
		checkAndEatChar("!define");
		skipSpaces();
		function = eatDeclareFunction(context, memory, true, getLineLocation(), false, TFunctionType.LEGACY_DEFINE);
		final String def = this.eatAllToEnd();
//		function.setFunctionType(TFunctionType.LEGACY_DEFINE);
		function.setLegacyDefinition(def);
	}

	public TFunction getFunction() {
		return function;
	}

}
