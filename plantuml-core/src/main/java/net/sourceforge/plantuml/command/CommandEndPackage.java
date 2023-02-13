package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.classdiagram.AbstractEntityDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandEndPackage extends SingleLineCommand2<AbstractEntityDiagram> {

	public CommandEndPackage() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandEndPackage.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("\\}"), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(AbstractEntityDiagram diagram, LineLocation location, RegexResult arg) {
		final boolean result = diagram.endGroup();
		if (result == false) {
			return CommandExecutionResult.error("No package or namespace defined");
		}
		return CommandExecutionResult.ok();
	}

}
