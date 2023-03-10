// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.statediagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.statediagram.StateDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandConcurrentState extends SingleLineCommand2<StateDiagram> {

	public CommandConcurrentState() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandConcurrentState.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("TYPE", "(--+|\\|\\|+)"), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(StateDiagram diagram, LineLocation location, RegexResult arg) {
		if (diagram.concurrentState(arg.get("TYPE", 0).charAt(0)))
			return CommandExecutionResult.ok();

		return CommandExecutionResult.error("Error 42");
	}

}
