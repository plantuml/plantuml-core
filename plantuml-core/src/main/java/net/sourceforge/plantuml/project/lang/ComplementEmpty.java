package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;

public class ComplementEmpty implements Something {

	public IRegex toRegex(String suffix) {
		return new RegexLeaf("");
	}

	public Failable<Object> getMe(GanttDiagram system, RegexResult arg, String suffix) {
		return Failable.ok(new Object());
	}
}
