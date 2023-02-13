package net.sourceforge.plantuml.activitydiagram3.command;

import net.sourceforge.plantuml.activitydiagram3.ActivityDiagram3;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandEndPartition3 extends SingleLineCommand2<ActivityDiagram3> {

	public CommandEndPartition3() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandEndPartition3.class.getName(), //
				RegexLeaf.start(), //
				new RegexLeaf("\\}"), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram3 diagram, LineLocation location, RegexResult arg) {
		// final IEntity currentPackage = diagram.getCurrentGroup();
		// if (currentPackage == null) {
		// return CommandExecutionResult.error("No partition defined");
		// }
		return diagram.endGroup();
	}

}
