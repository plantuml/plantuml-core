package net.sourceforge.plantuml.ebnf;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandComment extends SingleLineCommand2<PSystemEbnf> {

	public CommandComment() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandComment.class.getName(), //
				RegexLeaf.start(), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("\\(\\*"), //
				new RegexLeaf("COMMENT", "(.*[^%s].*)"), //
				new RegexLeaf("\\*\\)"), //
				RegexLeaf.spaceZeroOrMore(), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(PSystemEbnf diagram, LineLocation location, RegexResult arg) {
		final Display note = Display.getWithNewlines(arg.get("COMMENT", 0));
		return diagram.addNote(note, null);
	}

}
