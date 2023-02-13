package net.sourceforge.plantuml.timingdiagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.timingdiagram.TimeAxisStategy;
import net.sourceforge.plantuml.timingdiagram.TimingDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandHideTimeAxis extends SingleLineCommand2<TimingDiagram> {

	public CommandHideTimeAxis() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandHideTimeAxis.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("COMMAND", "(hide|manual)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("time"), //
				new RegexLeaf(".?"), //
				new RegexLeaf("axis"), //
				RegexLeaf.end());
	}

	@Override
	final protected CommandExecutionResult executeArg(TimingDiagram diagram, LineLocation location, RegexResult arg) {
		final String cmd = arg.get("COMMAND", 0);
		if ("MANUAL".equalsIgnoreCase(cmd))
			return diagram.setTimeAxisStategy(TimeAxisStategy.MANUAL);
		return diagram.setTimeAxisStategy(TimeAxisStategy.HIDDEN);
	}

}
