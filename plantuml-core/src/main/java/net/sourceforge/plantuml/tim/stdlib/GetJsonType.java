package net.sourceforge.plantuml.tim.stdlib;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sourceforge.plantuml.json.JsonValue;
import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;
import net.sourceforge.plantuml.tim.TContext;
import net.sourceforge.plantuml.tim.TFunctionSignature;
import net.sourceforge.plantuml.tim.TMemory;
import net.sourceforge.plantuml.tim.expression.TValue;
import net.sourceforge.plantuml.utils.LineLocation;

public class GetJsonType extends SimpleReturnFunction {

	public TFunctionSignature getSignature() {
		return new TFunctionSignature("%get_json_type", 1);
	}

	public boolean canCover(int nbArg, Set<String> namedArgument) {
		return nbArg == 1;
	}

	public TValue executeReturnFunction(TContext context, TMemory memory, LineLocation location, List<TValue> values,
			Map<String, TValue> named) throws EaterException, EaterExceptionLocated {
		final TValue data = values.get(0);
		if (data.isString())
			return TValue.fromString("string");
		if (data.isNumber())
			return TValue.fromString("number");
		if (data.isJson() == false)
			return TValue.fromString("not_json");
		final JsonValue json = data.toJson();
		if (json.isArray())
			return TValue.fromString("array");
		if (json.isObject())
			return TValue.fromString("object");
		if (json.isBoolean())
			return TValue.fromString("boolean");
		if (json.isNumber())
			return TValue.fromString("number");
		if (json.isString())
			return TValue.fromString("string");

		return TValue.fromString("json");
	}

}
