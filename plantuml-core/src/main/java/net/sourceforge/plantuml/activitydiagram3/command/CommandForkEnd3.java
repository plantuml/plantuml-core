package net.sourceforge.plantuml.activitydiagram3.command;

import net.sourceforge.plantuml.activitydiagram3.ActivityDiagram3;
import net.sourceforge.plantuml.activitydiagram3.ForkStyle;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOr;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandForkEnd3 extends SingleLineCommand2<ActivityDiagram3> {

	public CommandForkEnd3() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandForkEnd3.class.getName(), //
				RegexLeaf.start(), //
				new RegexOr("STYLE", //
						new RegexConcat( //
								new RegexLeaf("end"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("fork") //
						), //
						new RegexConcat( //
								new RegexLeaf("fork"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("end") //
						), //
						new RegexConcat( //
								new RegexLeaf("end"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("merge") //
						) //
				), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("LABEL", "(\\{.+\\})?"), //
				new RegexLeaf(";?"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram3 diagram, LineLocation location, RegexResult arg) {
		final String style = arg.get("STYLE", 0);
		final ForkStyle forkStyle = style.contains("merge") ? ForkStyle.MERGE : ForkStyle.FORK;
		final String label = arg.get("LABEL", 0);
		return diagram.endFork(forkStyle, label);
	}

}
