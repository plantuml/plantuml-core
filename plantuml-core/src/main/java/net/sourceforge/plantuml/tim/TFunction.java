package net.sourceforge.plantuml.tim;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sourceforge.plantuml.tim.expression.TValue;
import net.sourceforge.plantuml.utils.LineLocation;

public interface TFunction {

	public TFunctionSignature getSignature();

	public boolean canCover(int nbArg, Set<String> namedArguments);

	public TFunctionType getFunctionType();

	public void executeProcedure(TContext context, TMemory memory, LineLocation location, String s)
			throws EaterException, EaterExceptionLocated;

	public TValue executeReturnFunction(TContext context, TMemory memory, LineLocation location, List<TValue> args,
			Map<String, TValue> named) throws EaterException, EaterExceptionLocated;

	public void executeProcedureInternal(TContext context, TMemory memory, List<TValue> args, Map<String, TValue> named)
			throws EaterException, EaterExceptionLocated;

	public boolean isUnquoted();

}
