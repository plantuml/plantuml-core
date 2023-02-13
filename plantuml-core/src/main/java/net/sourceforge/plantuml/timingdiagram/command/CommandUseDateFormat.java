package net.sourceforge.plantuml.timingdiagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.timingdiagram.TimingDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandUseDateFormat extends SingleLineCommand2<TimingDiagram> {

	public CommandUseDateFormat() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandUseDateFormat.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("use"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("date"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("format"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("FORMAT", "[%g]([^%g]+)[%g]"), //
				RegexLeaf.end());
	}

	@Override
	final protected CommandExecutionResult executeArg(TimingDiagram diagram, LineLocation location, RegexResult arg) {
		final String format = arg.get("FORMAT", 0);
		return diagram.useDateFormat(format);
	}

}
