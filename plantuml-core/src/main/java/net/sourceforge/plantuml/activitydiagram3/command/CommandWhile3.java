package net.sourceforge.plantuml.activitydiagram3.command;

import net.sourceforge.plantuml.activitydiagram3.ActivityDiagram3;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.klimt.color.ColorParser;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandWhile3 extends SingleLineCommand2<ActivityDiagram3> {

	public CommandWhile3() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandWhile3.class.getName(), RegexLeaf.start(), //
				ColorParser.exp4(), //
				new RegexLeaf("while"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("\\("), //
				new RegexLeaf("TEST", "(.*?)"), //
				new RegexLeaf("\\)"), //
				new RegexOptional(new RegexConcat(//
						RegexLeaf.spaceZeroOrMore(), //
						new RegexLeaf("(is|equals?)"), //
						RegexLeaf.spaceZeroOrMore(), //
						new RegexLeaf("YES", "\\((.+?)\\)"))), //
				new RegexLeaf(";?"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram3 diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {
		final String s = arg.get("COLOR", 0);
		final HColor color = s == null ? null : diagram.getSkinParam().getIHtmlColorSet().getColor(s);
		diagram.doWhile(Display.getWithNewlines(arg.get("TEST", 0)), Display.getWithNewlines(arg.get("YES", 0)), color);

		return CommandExecutionResult.ok();
	}

}
