package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandNope extends SingleLineCommand2<Diagram> {

	public static final CommandNope ME = new CommandNope();

	private CommandNope() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandNope.class.getName(), RegexLeaf.start(), //
				RegexLeaf.spaceZeroOrMore(), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(Diagram diagram, LineLocation location, RegexResult arg) {
		return CommandExecutionResult.ok();
	}

}
