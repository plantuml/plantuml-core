package net.sourceforge.plantuml.activitydiagram3.command;

import net.sourceforge.plantuml.activitydiagram3.ActivityDiagram3;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.decoration.Rainbow;
import net.sourceforge.plantuml.descdiagram.command.CommandLinkElement;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOr;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandArrow3 extends SingleLineCommand2<ActivityDiagram3> {

	public CommandArrow3() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandArrow3.class.getName(), RegexLeaf.start(), //
				new RegexOr(//
						new RegexLeaf("->"), //
						new RegexLeaf("COLOR", CommandLinkElement.STYLE_COLORS_MULTIPLES)), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexOr(//
						new RegexLeaf("LABEL", "(.*);"), //
						new RegexLeaf("")), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram3 diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {

		final String colorString = arg.get("COLOR", 0);
		if (colorString != null) {
			final Rainbow rainbow = Rainbow.build(diagram.getSkinParam(), colorString,
					diagram.getSkinParam().colorArrowSeparationSpace());
			diagram.setColorNextArrow(rainbow);
		}
		final String label = arg.get("LABEL", 0);
		if (label != null && label.length() > 0) {
			diagram.setLabelNextArrow(Display.getWithNewlines(label));
		}

		return CommandExecutionResult.ok();
	}

}
