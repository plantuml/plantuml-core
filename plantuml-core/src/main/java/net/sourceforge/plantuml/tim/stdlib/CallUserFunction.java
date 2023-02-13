package net.sourceforge.plantuml.tim.stdlib;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;
import net.sourceforge.plantuml.tim.TContext;
import net.sourceforge.plantuml.tim.TFunction;
import net.sourceforge.plantuml.tim.TFunctionSignature;
import net.sourceforge.plantuml.tim.TMemory;
import net.sourceforge.plantuml.tim.expression.TValue;
import net.sourceforge.plantuml.utils.LineLocation;

public class CallUserFunction extends SimpleReturnFunction {

	public TFunctionSignature getSignature() {
		return new TFunctionSignature("%call_user_func", 1);
	}

	public boolean canCover(int nbArg, Set<String> namedArgument) {
		return nbArg > 0;
	}

	public TValue executeReturnFunction(TContext context, TMemory memory, LineLocation location, List<TValue> values,
			Map<String, TValue> named) throws EaterException, EaterExceptionLocated {
		final String fname = values.get(0).toString();
		final List<TValue> args = values.subList(1, values.size());
		final TFunctionSignature signature = new TFunctionSignature(fname, args.size());
		final TFunction func = context.getFunctionSmart(signature);
		if (func == null) {
			throw EaterException.unlocated("Cannot find void function " + fname);
		}
		return func.executeReturnFunction(context, memory, location, args, named);
	}

}
