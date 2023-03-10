// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.json.JsonArray;
import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.tim.expression.TValue;

public class EaterForeach extends Eater {

	private String varname;
	private JsonArray jsonArray;

	public EaterForeach(StringLocated s) {
		super(s);
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException, EaterExceptionLocated {
		skipSpaces();
		checkAndEatChar("!foreach");
		skipSpaces();
		this.varname = eatAndGetVarname();
		skipSpaces();
		checkAndEatChar("in");
		skipSpaces();
		final TValue value = eatExpression(context, memory);
		this.jsonArray = (JsonArray) value.toJson();
	}

	public boolean isSkip() {
		if (this.jsonArray == null) {
			return true;
		}
		return this.jsonArray.size() == 0;
	}

	public final String getVarname() {
		return varname;
	}

	public final JsonArray getJsonArray() {
		return jsonArray;
	}

}
