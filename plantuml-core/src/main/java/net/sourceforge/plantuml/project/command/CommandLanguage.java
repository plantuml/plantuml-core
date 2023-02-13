package net.sourceforge.plantuml.project.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandLanguage extends SingleLineCommand2<GanttDiagram> {

	public CommandLanguage() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandLanguage.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("language"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("LANG", "(\\w+)"), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(GanttDiagram diagram, LineLocation location, RegexResult arg) {
		return diagram.changeLanguage(arg.get("LANG", 0));
	}

}
