package net.sourceforge.plantuml.nwdiag;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandLink extends SingleLineCommand2<NwDiagram> {

	public CommandLink() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandLink.class.getName(), RegexLeaf.start(), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("NAME1", "([%pLN_]+)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("--"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("NAME2", "([%pLN_]+)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf(";?"), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(NwDiagram diagram, LineLocation location, RegexResult arg) {
		final String name1 = arg.get("NAME1", 0);
		final String name2 = arg.get("NAME2", 0);
		return diagram.link(name1, name2);
	}

}
