package net.sourceforge.plantuml.tim.stdlib;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;
import net.sourceforge.plantuml.tim.TContext;
import net.sourceforge.plantuml.tim.TFunctionSignature;
import net.sourceforge.plantuml.tim.TMemory;
import net.sourceforge.plantuml.tim.expression.TValue;
import net.sourceforge.plantuml.utils.LineLocation;

public class Substr extends SimpleReturnFunction {

	public TFunctionSignature getSignature() {
		return new TFunctionSignature("%substr", 3);
	}

	public boolean canCover(int nbArg, Set<String> namedArgument) {
		return nbArg == 2 || nbArg == 3;
	}

	public TValue executeReturnFunction(TContext context, TMemory memory, LineLocation location, List<TValue> values,
			Map<String, TValue> named) throws EaterException, EaterExceptionLocated {
		final String full = values.get(0).toString();
		final int pos = values.get(1).toInt();
		if (pos >= full.length())
			return TValue.fromString("");

		String result = full.substring(pos);
		if (values.size() == 3) {
			final int len = values.get(2).toInt();
			if (len < result.length())
				result = result.substring(0, len);

		}
		return TValue.fromString(result);
	}
}
