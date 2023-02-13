package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.UmlDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandMinwidth extends SingleLineCommand2<UmlDiagram> {

	public static final CommandMinwidth ME = new CommandMinwidth();

	private CommandMinwidth() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandMinwidth.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("minwidth"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("VALUE", "(\\d+)"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(UmlDiagram diagram, LineLocation location, RegexResult arg) {
		final int minwidth = Integer.parseInt(arg.get("VALUE", 0));
		diagram.setMinwidth(minwidth);
		return CommandExecutionResult.ok();
	}

}
