package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;

public class ComplementInColors2 implements Something {

	public IRegex toRegex(String suffix) {
		return new RegexLeaf("COMPLEMENT" + suffix, "colou?red[%s]+(?:in[%s]+)?(#?\\w+)(?:/(#?\\w+))?");
	}

	public Failable<CenterBorderColor> getMe(GanttDiagram diagram, RegexResult arg, String suffix) {
		final String color1 = arg.get("COMPLEMENT" + suffix, 0);
		final String color2 = arg.get("COMPLEMENT" + suffix, 1);
		final HColor col1 = color1 == null ? null
				: diagram.getIHtmlColorSet().getColorOrWhite(color1);
		final HColor col2 = color2 == null ? null
				: diagram.getIHtmlColorSet().getColorOrWhite(color2);
		return Failable.ok(new CenterBorderColor(col1, col2));
	}

}
