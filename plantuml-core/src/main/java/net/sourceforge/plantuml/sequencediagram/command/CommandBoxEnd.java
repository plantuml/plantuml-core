package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandBoxEnd extends SingleLineCommand2<SequenceDiagram> {

	public CommandBoxEnd() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandBoxEnd.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("end"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("box"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram diagram, LineLocation location, RegexResult arg) {
		if (diagram.isBoxPending() == false)
			return CommandExecutionResult.error("Missing starting box");

		diagram.endBox();
		return CommandExecutionResult.ok();
	}

}
