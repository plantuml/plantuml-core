package net.sourceforge.plantuml.timingdiagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.stereo.Stereotype;
import net.sourceforge.plantuml.timingdiagram.TimingDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandBinary extends SingleLineCommand2<TimingDiagram> {

	public CommandBinary() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandBinary.class.getName(), RegexLeaf.start(), //
				new RegexOptional( //
						new RegexConcat( //
								new RegexLeaf("COMPACT", "(compact)"), //
								RegexLeaf.spaceOneOrMore())), //
				new RegexLeaf("binary"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("FULL", "[%g]([^%g]+)[%g]"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("STEREOTYPE", "(\\<\\<.*\\>\\>)?"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("as"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("CODE", "([%pLN_.@]+)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("STEREOTYPE2", "(\\<\\<.*\\>\\>)?"), //
				RegexLeaf.spaceZeroOrMore(), //
				RegexLeaf.end());
	}

	@Override
	final protected CommandExecutionResult executeArg(TimingDiagram diagram, LineLocation location, RegexResult arg) {
		final String compact = arg.get("COMPACT", 0);
		final String code = arg.get("CODE", 0);
		final String full = arg.get("FULL", 0);

		Stereotype stereotype = null;
		if (arg.get("STEREOTYPE", 0) != null)
			stereotype = Stereotype.build(arg.get("STEREOTYPE", 0));
		else if (arg.get("STEREOTYPE2", 0) != null)
			stereotype = Stereotype.build(arg.get("STEREOTYPE2", 0));

		return diagram.createBinary(code, full, compact != null, stereotype);
	}

}
