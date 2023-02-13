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

public class CommandSwimlane extends SingleLineCommand2<ActivityDiagram3> {

	public CommandSwimlane() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandSwimlane.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("\\|"), //
				ColorParser.exp6(), //
				new RegexLeaf("SWIMLANE", "([^|]+)"), //
				new RegexLeaf("\\|"), //
				new RegexLeaf("LABEL", "([^|]+)?"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram3 diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {
		final String s = arg.get("COLOR", 0);
		final HColor color = s == null ? null
				: diagram.getSkinParam().getIHtmlColorSet().getColor(s);
		final String name = arg.get("SWIMLANE", 0);
		final Display label = Display.getWithNewlines(arg.get("LABEL", 0));
		return diagram.swimlane(name, color, label);
	}

}
