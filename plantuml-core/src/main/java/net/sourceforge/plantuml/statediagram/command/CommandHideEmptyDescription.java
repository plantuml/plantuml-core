package net.sourceforge.plantuml.statediagram.command;

import net.sourceforge.plantuml.UmlDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandHideEmptyDescription extends SingleLineCommand2<UmlDiagram> {

	public static final CommandHideEmptyDescription ME = new CommandHideEmptyDescription();

	private CommandHideEmptyDescription() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandHideEmptyDescription.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("HIDE", "(hide|show)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("empty"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("description"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(UmlDiagram diagram, LineLocation location, RegexResult arg) {
		final boolean hide = arg.get("HIDE", 0).equalsIgnoreCase("hide");
		diagram.setHideEmptyDescription(hide);
		return CommandExecutionResult.ok();
	}

}
