package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandNewpage extends SingleLineCommand2<SequenceDiagram> {

	public CommandNewpage() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandNewpage.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("@?"), //
				new RegexLeaf("newpage"), //
				new RegexOptional( //
						new RegexConcat( //
								new RegexLeaf("(?:[%s]*:[%s]*|[%s]+)"), //
								new RegexLeaf("LABEL", "(.*[%pLN_.].*)") //
						)), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram sequenceDiagram, LineLocation location, RegexResult arg) {
		final String label = arg.get("LABEL", 0);
		final Display strings = label == null ? Display.NULL : Display.getWithNewlines(label);
		sequenceDiagram.newpage(strings);
		return CommandExecutionResult.ok();
	}
}
