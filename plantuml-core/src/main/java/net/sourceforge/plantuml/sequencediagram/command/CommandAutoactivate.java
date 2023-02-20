package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandAutoactivate extends SingleLineCommand2<SequenceDiagram> {

	public CommandAutoactivate() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandAutoactivate.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("autoactivate"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("ON", "(off|on)"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram sequenceDiagram, LineLocation location,
			RegexResult arg) {
		sequenceDiagram.setAutoactivate("on".equalsIgnoreCase(arg.get("ON", 0)));
		return CommandExecutionResult.ok();
	}
}
