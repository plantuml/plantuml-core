package net.sourceforge.plantuml.project.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandHideResourceName extends SingleLineCommand2<GanttDiagram> {

	public CommandHideResourceName() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandHideResourceName.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("hide"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("ress?ources?"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("names?"), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(GanttDiagram diagram, LineLocation location, RegexResult arg) {
		return diagram.hideResourceName();
	}

}
