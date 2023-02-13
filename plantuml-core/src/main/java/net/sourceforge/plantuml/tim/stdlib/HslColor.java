package net.sourceforge.plantuml.tim.stdlib;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.color.HSLColor;
import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;
import net.sourceforge.plantuml.tim.TContext;
import net.sourceforge.plantuml.tim.TFunctionSignature;
import net.sourceforge.plantuml.tim.TMemory;
import net.sourceforge.plantuml.tim.expression.TValue;
import net.sourceforge.plantuml.utils.LineLocation;

public class HslColor extends SimpleReturnFunction {

	public TFunctionSignature getSignature() {
		return new TFunctionSignature("%hsl_color", 3);
	}

	public boolean canCover(int nbArg, Set<String> namedArgument) {
		return nbArg == 3 || nbArg == 4;
	}

	public TValue executeReturnFunction(TContext context, TMemory memory, LineLocation location, List<TValue> values,
			Map<String, TValue> named) throws EaterException, EaterExceptionLocated {
		final int h = values.get(0).toInt();
		final int s = values.get(1).toInt();
		final int l = values.get(2).toInt();
		if (values.size() == 3) {
			final HSLColor color = new HSLColor(h, s, l);
			final Color rgb = color.getRGB();
			return TValue.fromString(HColors.simple(rgb).asString());
		}
		final int a = values.get(3).toInt();
		final HSLColor color = new HSLColor(h, s, l, (float) (a / 100.0));
		final Color rgb = color.getRGB();
		return TValue.fromString(HColors.simple(rgb).asString());

	}
}
