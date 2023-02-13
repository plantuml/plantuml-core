package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.UmlDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandFootboxIgnored extends SingleLineCommand2<UmlDiagram> {

	public CommandFootboxIgnored() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandFootboxIgnored.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("(hide|show)?"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("footbox"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(UmlDiagram diagram, LineLocation location, RegexResult arg) {
		return CommandExecutionResult.ok();
	}
}
