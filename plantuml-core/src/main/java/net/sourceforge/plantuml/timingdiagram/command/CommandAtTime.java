package net.sourceforge.plantuml.timingdiagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.timingdiagram.TimeTick;
import net.sourceforge.plantuml.timingdiagram.TimingDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandAtTime extends SingleLineCommand2<TimingDiagram> {

	public CommandAtTime() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandAtTime.class.getName(), RegexLeaf.start(), //
				TimeTickBuilder.expressionAtWithArobase("TIME"), //
				new RegexOptional(new RegexConcat( //
						RegexLeaf.spaceOneOrMore(), //
						new RegexLeaf("as"), //
						RegexLeaf.spaceOneOrMore(), //
						new RegexLeaf(":"), //
						new RegexLeaf("CODE", "([%pLN_.]+)") //
				)), //
				RegexLeaf.spaceZeroOrMore(), //
				RegexLeaf.end());
	}

	@Override
	final protected CommandExecutionResult executeArg(TimingDiagram diagram, LineLocation location, RegexResult arg) {
		final TimeTick timeTick = TimeTickBuilder.parseTimeTick("TIME", arg, diagram);
		if (timeTick == null)
			return CommandExecutionResult.error("What time?");

		final String code = arg.get("CODE", 0);
		diagram.addTime(timeTick, code);
		return CommandExecutionResult.ok();
	}

}
