package net.sourceforge.plantuml.tim.expression;

import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;
import net.sourceforge.plantuml.tim.TFunction;
import net.sourceforge.plantuml.tim.TFunctionSignature;

public interface Knowledge {

	public TValue getVariable(String name) throws EaterException, EaterExceptionLocated;

	public TFunction getFunction(TFunctionSignature signature);

}
