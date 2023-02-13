package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandAutoNewpage extends SingleLineCommand2<SequenceDiagram> {

	public CommandAutoNewpage() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandAutoNewpage.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("autonewpage"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("VALUE", "(\\d+)"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram diagram, LineLocation location, RegexResult arg) {
		diagram.setAutonewpage(Integer.parseInt(arg.get("VALUE", 0)));
		return CommandExecutionResult.ok();
	}
}
