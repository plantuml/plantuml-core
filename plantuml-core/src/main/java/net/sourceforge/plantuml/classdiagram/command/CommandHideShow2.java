// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.classdiagram.command;

import net.atmp.CucaDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandHideShow2 extends SingleLineCommand2<CucaDiagram> {

	public CommandHideShow2() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandHideShow2.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("COMMAND", "(hide|hide-class|show|show-class)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("WHAT", "([^%s]+|\\<\\<.*\\>\\>)"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(CucaDiagram diagram, LineLocation location, RegexResult arg) {

		final char tmp = arg.get("COMMAND", 0).charAt(0);
		final boolean show = tmp == 's' || tmp == 'S';
		final String what = arg.get("WHAT", 0).trim();
		diagram.hideOrShow2(what, show);
		return CommandExecutionResult.ok();
	}
}
