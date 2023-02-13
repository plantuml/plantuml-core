package net.sourceforge.plantuml.activitydiagram3.command;

import net.sourceforge.plantuml.activitydiagram3.ActivityDiagram3;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandStart3 extends SingleLineCommand2<ActivityDiagram3> {

	public CommandStart3() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandStart3.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("start"), //
				new RegexLeaf(";?"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram3 diagram, LineLocation location, RegexResult arg) {
		// if (getSystem().getLastEntityConsulted() == null) {
		// return CommandExecutionResult.error("No if for this endif");
		// }
		diagram.start();

		return CommandExecutionResult.ok();
	}

}
