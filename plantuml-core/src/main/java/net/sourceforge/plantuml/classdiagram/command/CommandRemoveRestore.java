package net.sourceforge.plantuml.classdiagram.command;

import net.sourceforge.plantuml.baraye.CucaDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandRemoveRestore extends SingleLineCommand2<CucaDiagram> {

	public CommandRemoveRestore() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandRemoveRestore.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("COMMAND", "(remove|restore)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("WHAT", "(.+)"), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(CucaDiagram diagram, LineLocation location, RegexResult arg) {

		final boolean show = arg.get("COMMAND", 0).equalsIgnoreCase("restore");
		final String what = arg.get("WHAT", 0).trim();
		diagram.removeOrRestore(what, show);
		return CommandExecutionResult.ok();
	}
}
