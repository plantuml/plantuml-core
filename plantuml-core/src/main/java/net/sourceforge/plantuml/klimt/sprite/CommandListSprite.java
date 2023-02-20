package net.sourceforge.plantuml.klimt.sprite;

import net.sourceforge.plantuml.UmlDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandListSprite extends SingleLineCommand2<UmlDiagram> {

	public CommandListSprite() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandListSprite.class.getName(), //
				RegexLeaf.start(), //
				new RegexLeaf("listsprites?"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(UmlDiagram system, LineLocation location, RegexResult arg) {
		return CommandExecutionResult.ok();
	}

}
