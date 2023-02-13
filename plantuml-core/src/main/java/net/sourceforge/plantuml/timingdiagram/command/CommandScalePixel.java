package net.sourceforge.plantuml.timingdiagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.timingdiagram.TimingDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandScalePixel extends SingleLineCommand2<TimingDiagram> {

	public CommandScalePixel() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandScalePixel.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("scale"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("TICK", "(\\d+)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("as"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("PIXEL", "(\\d+)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("pixels?"), //
				RegexLeaf.spaceZeroOrMore(), RegexLeaf.end());
	}

	@Override
	final protected CommandExecutionResult executeArg(TimingDiagram diagram, LineLocation location, RegexResult arg) {
		final long tick = Long.parseLong(arg.get("TICK", 0));
		final long pixel = Long.parseLong(arg.get("PIXEL", 0));
		if (tick <= 0 || pixel <= 0)
			return CommandExecutionResult.error("Bad value");

		diagram.scaleInPixels(tick, pixel);
		return CommandExecutionResult.ok();
	}

}
