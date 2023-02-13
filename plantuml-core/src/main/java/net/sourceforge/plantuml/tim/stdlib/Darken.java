package net.sourceforge.plantuml.tim.stdlib;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;
import net.sourceforge.plantuml.tim.TContext;
import net.sourceforge.plantuml.tim.TFunctionSignature;
import net.sourceforge.plantuml.tim.TMemory;
import net.sourceforge.plantuml.tim.expression.TValue;
import net.sourceforge.plantuml.utils.LineLocation;

public class Darken extends SimpleReturnFunction {

	// Inspired from https://github.com/Qix-/color
	public TFunctionSignature getSignature() {
		return new TFunctionSignature("%darken", 2);
	}

	public boolean canCover(int nbArg, Set<String> namedArgument) {
		return nbArg == 2;
	}

	public TValue executeReturnFunction(TContext context, TMemory memory, LineLocation location, List<TValue> values,
			Map<String, TValue> named) throws EaterException, EaterExceptionLocated {
		final String colorString = values.get(0).toString();
		final int ratio = values.get(1).toInt();
		try {
			HColor color = HColorSet.instance().getColorLEGACY(colorString);
			color = color.darken(ratio);
			return TValue.fromString(color.asString());
		} catch (NoSuchColorException e) {
			throw EaterException.located("No such color");
		}
	}
}
