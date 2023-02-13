package net.sourceforge.plantuml.classdiagram.command;

import net.sourceforge.plantuml.classdiagram.ClassDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandAllowMixing extends SingleLineCommand2<ClassDiagram> {

	public CommandAllowMixing() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {

		return RegexConcat.build(CommandAllowMixing.class.getName(), //
				RegexLeaf.start(), //
				new RegexLeaf("allow"), //
				new RegexLeaf("_?"), //
				new RegexLeaf("mixing"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(ClassDiagram diagram, LineLocation location, RegexResult arg) {
		diagram.setAllowMixing(true);
		return CommandExecutionResult.ok();
	}
}
