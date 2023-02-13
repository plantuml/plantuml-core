package net.sourceforge.plantuml.project.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandGroupEnd extends SingleLineCommand2<GanttDiagram> {

	public CommandGroupEnd() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandGroupEnd.class.getName(), RegexLeaf.start(), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("end"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("group"), //
				RegexLeaf.spaceZeroOrMore(), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(GanttDiagram diagram, LineLocation location, RegexResult arg) {
		return diagram.endGroup();
	}

}
