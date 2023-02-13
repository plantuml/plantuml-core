package net.sourceforge.plantuml.activitydiagram.command;

import net.sourceforge.plantuml.activitydiagram.ActivityDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandInnerConcurrent extends SingleLineCommand2<ActivityDiagram> {

	public CommandInnerConcurrent() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandInnerConcurrent.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("--"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("NAME", "(.*)"), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram diagram, LineLocation location, RegexResult arg) {
		if (diagram.getCurrentGroup().isRoot()) {
			return CommandExecutionResult.error("No inner activity");
		}
		diagram.concurrentActivity(arg.get("NAME", 0));

		return CommandExecutionResult.ok();
	}

}
