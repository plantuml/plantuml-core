package net.sourceforge.plantuml.activitydiagram3.command;

import net.sourceforge.plantuml.activitydiagram3.ActivityDiagram3;
import net.sourceforge.plantuml.activitydiagram3.LinkRendering;
import net.sourceforge.plantuml.activitydiagram3.ftile.BoxStyle;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.CommandMultilines3;
import net.sourceforge.plantuml.command.MultilinesStrategy;
import net.sourceforge.plantuml.command.Trim;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.BlocLines;

public class CommandBackwardLong3 extends CommandMultilines3<ActivityDiagram3> {

	public CommandBackwardLong3() {
		super(getRegexConcat(), MultilinesStrategy.REMOVE_STARTING_QUOTE, Trim.BOTH);
	}

	@Override
	public RegexConcat getPatternEnd2() {
		return new RegexConcat(//
				new RegexLeaf("TEXT", "(.*)"), //
				new RegexLeaf("END", CommandActivity3.endingGroup()), //
				RegexLeaf.end());
	}

//
//	@Override
//	public String getPatternEnd() {
//		return "^(.*)" + CommandActivity3.endingGroup() + "$";
//	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandBackwardLong3.class.getName(), RegexLeaf.start(), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("backward"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf(":"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("DATA", "(.*)"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeNow(ActivityDiagram3 diagram, BlocLines lines) throws NoSuchColorException {
		lines = lines.removeEmptyColumns();
		final RegexResult line0 = getStartingPattern().matcher(lines.getFirst().getTrimmed().getString());
		final RegexResult lineLast = getPatternEnd2().matcher(lines.getLast().getString());
		final String end = lineLast.get("END", 0);

		final BoxStyle style = BoxStyle.fromString(end);
		lines = lines.removeStartingAndEnding(line0.get("DATA", 0), end.length());

		final LinkRendering in = LinkRendering.none();
		final LinkRendering out = LinkRendering.none();

		return diagram.backward(lines.toDisplay(), style, in, out);
	}
}
