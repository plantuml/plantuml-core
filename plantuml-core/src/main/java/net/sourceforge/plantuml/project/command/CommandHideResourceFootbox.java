package net.sourceforge.plantuml.project.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandHideResourceFootbox extends SingleLineCommand2<GanttDiagram> {

	public CommandHideResourceFootbox() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandHideResourceFootbox.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("hide"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("ress?ources?"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("footbox"), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(GanttDiagram diagram, LineLocation location, RegexResult arg) {
		return diagram.hideResourceFootbox();
	}

}
