package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexResult;

public interface Sentence {

	public IRegex toRegex();

	public CommandExecutionResult execute(GanttDiagram project, RegexResult arg);

}
