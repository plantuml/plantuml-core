package net.sourceforge.plantuml.activitydiagram3.command;

import net.sourceforge.plantuml.activitydiagram3.ActivityDiagram3;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.klimt.color.ColorParser;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandIf4 extends SingleLineCommand2<ActivityDiagram3> {

	public CommandIf4() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandIf4.class.getName(), RegexLeaf.start(), //
				ColorParser.exp4(), //
				new RegexLeaf("if"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("\\("), //
				new RegexLeaf("TEST", "(.*?)"), //
				new RegexLeaf("\\)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("(is|equals?)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("\\("), //
				new RegexLeaf("WHEN", "(.+?)"), //
				new RegexLeaf("\\)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("then"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf(";?"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram3 diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {
		final String s = arg.get("COLOR", 0);
		final HColor color = s == null ? null
				: diagram.getSkinParam().getIHtmlColorSet().getColor(s);

		String test = arg.get("TEST", 0);
		if (test.length() == 0) {
			test = null;
		}
		diagram.startIf(Display.getWithNewlines(test), Display.getWithNewlines(arg.get("WHEN", 0)), color, null);

		return CommandExecutionResult.ok();
	}
}
