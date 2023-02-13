package net.sourceforge.plantuml.tim.stdlib;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sourceforge.plantuml.json.JsonObject;
import net.sourceforge.plantuml.json.JsonValue;
import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;
import net.sourceforge.plantuml.tim.TContext;
import net.sourceforge.plantuml.tim.TFunctionSignature;
import net.sourceforge.plantuml.tim.TMemory;
import net.sourceforge.plantuml.tim.expression.TValue;
import net.sourceforge.plantuml.utils.LineLocation;

public class JsonKeyExists extends SimpleReturnFunction {

	public TFunctionSignature getSignature() {
		return new TFunctionSignature("%json_key_exists", 1);
	}

	public boolean canCover(int nbArg, Set<String> namedArgument) {
		return nbArg == 2;
	}

	public TValue executeReturnFunction(TContext context, TMemory memory, LineLocation location, List<TValue> values,
			Map<String, TValue> named) throws EaterException, EaterExceptionLocated {
		final TValue arg0 = values.get(0);
		if (arg0.isJson() == false)
			return TValue.fromBoolean(false);

		final JsonValue json = arg0.toJson();
		if (json.isObject() == false)
			return TValue.fromBoolean(false);

		final TValue arg1 = values.get(1);
		if (arg1.isString() == false)
			return TValue.fromBoolean(false);

		final String keyname = arg1.toString();
		final JsonObject object = (JsonObject) json;
		if (object.contains(keyname))
			return TValue.fromBoolean(true);
		return TValue.fromBoolean(false);
	}

}
