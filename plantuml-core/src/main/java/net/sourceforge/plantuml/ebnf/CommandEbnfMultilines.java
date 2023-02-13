package net.sourceforge.plantuml.ebnf;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.CommandMultilines2;
import net.sourceforge.plantuml.command.MultilinesStrategy;
import net.sourceforge.plantuml.command.Trim;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.utils.BlocLines;

public class CommandEbnfMultilines extends CommandMultilines2<PSystemEbnf> {

	public CommandEbnfMultilines() {
		super(getRegexConcat(), MultilinesStrategy.KEEP_STARTING_QUOTE, Trim.BOTH);
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandEbnfMultilines.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("LINE", "(\\w[-\\w]*[%s]*=.*)"), //
				RegexLeaf.end());
	}

	@Override
	public String getPatternEnd() {
		return "^(.*);$";
	}

	@Override
	protected CommandExecutionResult executeNow(PSystemEbnf diagram, BlocLines lines) throws NoSuchColorException {
		return diagram.addBlocLines(lines, null, null);
	}

}
