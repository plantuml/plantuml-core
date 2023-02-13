package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.TitledDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandAssumeTransparent extends SingleLineCommand2<TitledDiagram> {

	public static final CommandAssumeTransparent ME = new CommandAssumeTransparent();

	private CommandAssumeTransparent() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandAssumeTransparent.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("!assume"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("transparent"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("TYPE", "(dark|light)"), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(TitledDiagram system, LineLocation location, RegexResult arg) {
		// final String type = arg.get("TYPE", 0).toUpperCase();
		// system.getSkinParam().assumeTransparent(ThemeStyle.valueOf(type));
		// This is ignored and will be suppressed in some future
		return CommandExecutionResult.ok();
	}

}
