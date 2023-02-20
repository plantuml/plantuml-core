package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandDivider extends SingleLineCommand2<SequenceDiagram> {

	public CommandDivider() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandDivider.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("=="), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("LABEL", "(.*)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("=="), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram diagram, LineLocation location, RegexResult arg) {
		final Display strings = Display.getWithNewlines(arg.get("LABEL", 0));
		diagram.divider(strings);
		return CommandExecutionResult.ok();
	}
}
