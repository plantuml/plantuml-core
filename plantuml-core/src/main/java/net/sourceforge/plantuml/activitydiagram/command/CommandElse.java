package net.sourceforge.plantuml.activitydiagram.command;

import net.sourceforge.plantuml.activitydiagram.ActivityDiagram;
import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandElse extends SingleLineCommand2<ActivityDiagram> {

	public CommandElse() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandElse.class.getName(), //
				RegexLeaf.start(), //
				new RegexLeaf("else"), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram system, LineLocation location, RegexResult arg) {
		if (system.getLastEntityConsulted() == null) {
			return CommandExecutionResult.error("No if for this else");
		}
		if (system.getCurrentContext() == null) {
			return CommandExecutionResult.error("No if for this else");
		}
		final Entity branch = system.getCurrentContext().getBranch();

		system.setLastEntityConsulted(branch);

		return CommandExecutionResult.ok();
	}

}
