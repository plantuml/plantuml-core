package net.sourceforge.plantuml.activitydiagram3.command;

import net.sourceforge.plantuml.activitydiagram3.ActivityDiagram3;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandCase extends SingleLineCommand2<ActivityDiagram3> {

	public CommandCase() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandCase.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("case"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("\\("), //
				new RegexLeaf("TEST", "(.*?)"), //
				new RegexLeaf("\\)"), //
				RegexLeaf.spaceZeroOrMore(), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram3 diagram, LineLocation location, RegexResult arg) {
		String test = arg.get("TEST", 0);
		if (test.length() == 0) {
			test = null;
		}

		return diagram.switchCase(Display.getWithNewlines(test));

	}

}
