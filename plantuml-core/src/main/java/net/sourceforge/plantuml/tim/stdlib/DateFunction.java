package net.sourceforge.plantuml.tim.stdlib;

import java.text.SimpleDateFormat;
import java.util.Date;
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

public class DateFunction extends SimpleReturnFunction {

	public TFunctionSignature getSignature() {
		return new TFunctionSignature("%date", 2);
	}

	public boolean canCover(int nbArg, Set<String> namedArgument) {
		return nbArg == 0 || nbArg == 1 || nbArg == 2;
	}

	public TValue executeReturnFunction(TContext context, TMemory memory, LineLocation location, List<TValue> values,
			Map<String, TValue> named) throws EaterException, EaterExceptionLocated {
		if (values.size() == 0)
			return TValue.fromString(new Date().toString());

		final String format = values.get(0).toString();
		final long now;
		if (values.size() == 2)
			now = 1000L * values.get(1).toInt();
		else
			now = System.currentTimeMillis();
		try {
			return TValue.fromString(new SimpleDateFormat(format).format(now));
		} catch (Exception e) {
			throw EaterException.unlocated("Bad date pattern");
		}
	}
}
