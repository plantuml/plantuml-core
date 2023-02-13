package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexResult;

public interface Something {

	public IRegex toRegex(String suffix);

	public Failable<? extends Object> getMe(GanttDiagram project, RegexResult arg, String suffix);

}
