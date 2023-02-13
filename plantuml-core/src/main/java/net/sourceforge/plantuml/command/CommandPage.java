package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.AbstractPSystem;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandPage extends SingleLineCommand2<AbstractPSystem> {

	public static final CommandPage ME = new CommandPage();

	private CommandPage() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandPage.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("page"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("NB1", "(\\d+)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("x*"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("NB2", "(\\d+)"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(AbstractPSystem system, LineLocation location, RegexResult arg) {

		final int horizontal = Integer.parseInt(arg.get("NB1", 0));
		final int vertical = Integer.parseInt(arg.get("NB2", 0));
		if (horizontal <= 0 || vertical <= 0)
			return CommandExecutionResult.error("Argument must be positive");

		system.setSplitPagesHorizontal(horizontal);
		system.setSplitPagesVertical(vertical);
		return CommandExecutionResult.ok();
	}

}
