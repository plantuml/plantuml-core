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

public class LogicalNand extends SimpleReturnFunction {

	public TFunctionSignature getSignature() {
		return new TFunctionSignature("%nand", 2);
	}

	public boolean canCover(int nbArg, Set<String> namedArgument) {
		return nbArg >= 2;
	}

	public TValue executeReturnFunction(TContext context, TMemory memory, LineLocation location, List<TValue> values,
			Map<String, TValue> named) throws EaterException, EaterExceptionLocated {
		for (TValue v : values)
			if (v.toBoolean() == false)
				return TValue.fromBoolean(!false);

		return TValue.fromBoolean(!true);

	}
}
