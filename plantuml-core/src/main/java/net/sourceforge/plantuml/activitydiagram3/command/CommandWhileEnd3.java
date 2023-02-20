package net.sourceforge.plantuml.activitydiagram3.command;

import net.sourceforge.plantuml.activitydiagram3.ActivityDiagram3;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexOr;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandWhileEnd3 extends SingleLineCommand2<ActivityDiagram3> {

	public CommandWhileEnd3() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandWhileEnd3.class.getName(), RegexLeaf.start(), //
				new RegexOr( //
						new RegexConcat( //
								new RegexLeaf("end"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("while") //
						), //
						new RegexConcat( //
								new RegexLeaf("while"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("end") //
						) //
				), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexOptional(new RegexLeaf("OUT", "\\((.+?)\\)")), //
				new RegexLeaf(";?"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram3 diagram, LineLocation location, RegexResult arg) {
		return diagram.endwhile(Display.getWithNewlines(arg.get("OUT", 0)));
	}

}
