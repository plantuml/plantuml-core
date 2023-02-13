package net.sourceforge.plantuml.descdiagram.command;

import net.sourceforge.plantuml.classdiagram.AbstractEntityDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandTogether extends SingleLineCommand2<AbstractEntityDiagram> {

	public CommandTogether() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandTogether.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("together"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("\\{"), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(AbstractEntityDiagram diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {
		final CommandExecutionResult status = diagram.gotoTogether();
		return status;
	}
}
