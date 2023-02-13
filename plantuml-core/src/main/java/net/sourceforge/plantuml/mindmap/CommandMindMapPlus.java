package net.sourceforge.plantuml.mindmap;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandMindMapPlus extends SingleLineCommand2<MindMapDiagram> {

	public CommandMindMapPlus() {
		super(false, getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandMindMapPlus.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("TYPE", "([+-]+)"), //
				new RegexOptional(new RegexLeaf("BACKCOLOR", "\\[(#\\w+)\\]")), //
				new RegexLeaf("SHAPE", "(_)?"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("LABEL", "([^%s].*)"), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(MindMapDiagram diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {
		final String type = arg.get("TYPE", 0);
		final String label = arg.get("LABEL", 0);
		final String stringColor = arg.get("BACKCOLOR", 0);
		HColor backColor = null;
		if (stringColor != null) {
			backColor = diagram.getSkinParam().getIHtmlColorSet().getColor(stringColor);
		}
		final boolean direction = type.contains("-") ? false : true;
		return diagram.addIdea(backColor, type.length() - 1, Display.getWithNewlines(label),
				IdeaShape.fromDesc(arg.get("SHAPE", 0)), direction);
	}

}
