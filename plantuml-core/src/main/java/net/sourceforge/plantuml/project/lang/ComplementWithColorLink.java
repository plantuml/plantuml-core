package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;

public class ComplementWithColorLink implements Something {

	public IRegex toRegex(String suffix) {
		final String optionalStyle = "(?:(dotted|bold|dashed)[%s]+)?";
		return new RegexLeaf("COMPLEMENT" + suffix,
				"with[%s]+" + optionalStyle + "(#?\\w+)[%s]+" + optionalStyle + "link");
	}

	public Failable<CenterBorderColor> getMe(GanttDiagram diagram, RegexResult arg, String suffix) {
		final String style0 = arg.get("COMPLEMENT" + suffix, 0);
		final String color1 = arg.get("COMPLEMENT" + suffix, 1);
		final String style2 = arg.get("COMPLEMENT" + suffix, 2);
		final HColor col1 = color1 == null ? null
				: diagram.getIHtmlColorSet().getColorOrWhite(color1);
		final String style = style0 == null ? style2 : style0;
		return Failable.ok(new CenterBorderColor(col1, col1, style));
	}
}
