package net.sourceforge.plantuml.timingdiagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.timingdiagram.TimingDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandClock extends SingleLineCommand2<TimingDiagram> {

	public CommandClock() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandClock.class.getName(), RegexLeaf.start(), //
				new RegexOptional( //
						new RegexConcat( //
								new RegexLeaf("COMPACT", "(compact)"), //
								RegexLeaf.spaceOneOrMore())), //
				new RegexLeaf("TYPE", "clock"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexOptional(new RegexConcat( //
						new RegexLeaf("FULL", "[%g]([^%g]+)[%g]"), //
						RegexLeaf.spaceOneOrMore(), //
						new RegexLeaf("as"), //
						RegexLeaf.spaceOneOrMore())), //
				new RegexLeaf("CODE", "([%pLN_.@]+)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("with"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("period"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("PERIOD", "([0-9]+)"), //
				new RegexOptional(new RegexConcat( //
						RegexLeaf.spaceOneOrMore(), //
						new RegexLeaf("pulse"), //
						RegexLeaf.spaceOneOrMore(), //
						new RegexLeaf("PULSE", "([0-9]+)") //
				)), //
				new RegexOptional(new RegexConcat( //
						RegexLeaf.spaceOneOrMore(), //
						new RegexLeaf("offset"), //
						RegexLeaf.spaceOneOrMore(), //
						new RegexLeaf("OFFSET", "([0-9]+)") //
				)), //
				RegexLeaf.end());
	}

	@Override
	final protected CommandExecutionResult executeArg(TimingDiagram diagram, LineLocation location, RegexResult arg) {
		final String compact = arg.get("COMPACT", 0);
		final String code = arg.get("CODE", 0);
		final int period = Integer.parseInt(arg.get("PERIOD", 0));
		final int pulse = getInt(arg.get("PULSE", 0));
		final int offset = getInt(arg.get("OFFSET", 0));
		String full = arg.get("FULL", 0);
		if (full == null)
			full = "";
		return diagram.createClock(code, full, period, pulse, offset, compact != null);
	}

	private int getInt(String value) {
		if (value == null)
			return 0;
		return Integer.parseInt(value);
	}

}
