package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;

public class ComplementInColorsFromTo implements Something {

	public IRegex toRegex(String suffix) {
		return new RegexLeaf("COMPLEMENT" + suffix,
				"from[%s]+(#?\\w+)(?:/(#?\\w+))?[%s]+to[%s]+(#?\\w+)(?:/(#?\\w+))?");
	}

	public Failable<CenterBorderColor[]> getMe(GanttDiagram diagram, RegexResult arg, String suffix) {
		final String arg0 = arg.get("COMPLEMENT" + suffix, 0);
		final String arg1 = arg.get("COMPLEMENT" + suffix, 1);
		final String arg2 = arg.get("COMPLEMENT" + suffix, 2);
		final String arg3 = arg.get("COMPLEMENT" + suffix, 3);
		final HColor from0 = arg0 == null ? null : diagram.getIHtmlColorSet().getColorOrWhite(arg0);
		final HColor from1 = arg1 == null ? null : diagram.getIHtmlColorSet().getColorOrWhite(arg1);
		final HColor to0 = arg2 == null ? null : diagram.getIHtmlColorSet().getColorOrWhite(arg2);
		final HColor to1 = arg3 == null ? null : diagram.getIHtmlColorSet().getColorOrWhite(arg3);
		final CenterBorderColor result[] = new CenterBorderColor[] { new CenterBorderColor(from0, from1),
				new CenterBorderColor(to0, to1) };
		return Failable.ok(result);
	}
}
