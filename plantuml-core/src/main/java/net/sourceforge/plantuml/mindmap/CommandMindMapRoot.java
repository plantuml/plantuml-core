package net.sourceforge.plantuml.mindmap;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandMindMapRoot extends SingleLineCommand2<MindMapDiagram> {

	public CommandMindMapRoot() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandMindMapRoot.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("TYPE", "(0)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("LABEL", "([^%s].*)"), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(MindMapDiagram diagram, LineLocation location, RegexResult arg) {
		final String label = arg.get("LABEL", 0);
		return diagram.addIdea(null, 0, Display.getWithNewlines(label), IdeaShape.BOX, true);
	}

}
