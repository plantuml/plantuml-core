package net.sourceforge.plantuml.nwdiag;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandNetwork extends SingleLineCommand2<NwDiagram> {

	public CommandNetwork() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandNetwork.class.getName(), RegexLeaf.start(), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("network"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("NAME", "([-.%pLN_]+)?"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("\\{"), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(NwDiagram diagram, LineLocation location, RegexResult arg) {
		return diagram.openNetwork(arg.get("NAME", 0));
	}

}
