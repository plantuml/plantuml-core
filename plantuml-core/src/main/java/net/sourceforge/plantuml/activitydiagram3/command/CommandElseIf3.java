package net.sourceforge.plantuml.activitydiagram3.command;

import net.sourceforge.plantuml.activitydiagram3.ActivityDiagram3;
import net.sourceforge.plantuml.activitydiagram3.LinkRendering;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.descdiagram.command.CommandLinkElement;
import net.sourceforge.plantuml.klimt.color.ColorParser;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexOr;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandElseIf3 extends SingleLineCommand2<ActivityDiagram3> {

	public CommandElseIf3() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandElseIf3.class.getName(), RegexLeaf.start(), //
				ColorParser.exp4(), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexOptional(new RegexConcat( //
						new RegexLeaf("\\("), //
						new RegexOptional(new RegexOr(//
								new RegexLeaf("->"), //
								new RegexLeaf("INCOMING_COLOR", CommandLinkElement.STYLE_COLORS_MULTIPLES))), //
						new RegexLeaf("INCOMING", "(.*?)"), //
						new RegexLeaf("\\)"))), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("else"), //
				RegexLeaf.spaceZeroOrMore(), //
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
				new RegexOptional( //
						new RegexConcat( //
								new RegexLeaf("then"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexOptional(new RegexConcat( //
										new RegexLeaf("\\("), //
										new RegexOptional(new RegexOr(//
												new RegexLeaf("->"), //
												new RegexLeaf("WHEN_COLOR",
														CommandLinkElement.STYLE_COLORS_MULTIPLES))), //
										new RegexLeaf("\\)"))) //
						)), //
				new RegexLeaf(";?"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram3 diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {
		final String s = arg.get("COLOR", 0);
		final HColor color = s == null ? null : diagram.getSkinParam().getIHtmlColorSet().getColor(s);

		String test = arg.get("TEST", 0);
		if (test.length() == 0) {
			test = null;
		}

		final LinkRendering incoming = CommandBackward3.getBackRendering(diagram, arg, "INCOMING");
		final LinkRendering when = CommandBackward3.getBackRendering(diagram, arg, "WHEN");

		return diagram.elseIf(incoming, Display.getWithNewlines(test), when, color);
	}

}
