// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandDelay extends SingleLineCommand2<SequenceDiagram> {

	public CommandDelay() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandDelay.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("(?:\\.{3}|\u2026)"), //
				new RegexOptional(new RegexLeaf("LABEL", "(.*)(?:\\.{3}|\u2026)")), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram diagram, LineLocation location, RegexResult arg) {
		final Display strings = arg.get("LABEL", 0) == null ? Display.empty()
				: Display.getWithNewlines(arg.get("LABEL", 0));
		diagram.delay(strings);
		return CommandExecutionResult.ok();
	}
}
