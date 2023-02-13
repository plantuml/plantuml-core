package net.sourceforge.plantuml.nwdiag;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandComment extends SingleLineCommand2<NwDiagram> {

	public CommandComment() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandComment.class.getName(), //
				RegexLeaf.start(), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("//"), //
				new RegexLeaf(".*"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(NwDiagram diagram, LineLocation location, RegexResult arg) {
		return CommandExecutionResult.ok();
	}

}
