package net.sourceforge.plantuml.mindmap;

import java.util.List;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.CommandMultilines2;
import net.sourceforge.plantuml.command.MultilinesStrategy;
import net.sourceforge.plantuml.command.Trim;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.BlocLines;

public class CommandMindMapOrgmodeMultiline extends CommandMultilines2<MindMapDiagram> {

	public CommandMindMapOrgmodeMultiline() {
		super(getRegexConcat(), MultilinesStrategy.REMOVE_STARTING_QUOTE, Trim.BOTH);
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandMindMapOrgmodeMultiline.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("TYPE", "(\\*+)"), //
				new RegexOptional(new RegexLeaf("BACKCOLOR", "\\[(#\\w+)\\]")), //
				new RegexLeaf("SHAPE", "(_)?"), //
				new RegexLeaf(":"), //
				new RegexLeaf("DATA", "(.*)"), //
				RegexLeaf.end());
	}

	@Override
	public String getPatternEnd() {
		return "^(.*);(?:\\s*\\<\\<(.+)\\>\\>)?$";
	}

	@Override
	protected CommandExecutionResult executeNow(MindMapDiagram diagram, BlocLines lines) throws NoSuchColorException {
		final RegexResult line0 = getStartingPattern().matcher(lines.getFirst().getTrimmed().getString());

		final List<String> lineLast = StringUtils.getSplit(MyPattern.cmpile(getPatternEnd()),
				lines.getLast().getString());
		lines = lines.removeStartingAndEnding(line0.get("DATA", 0), 1);

		final String stereotype = lineLast.get(1);
		if (stereotype != null) {
			lines = lines.overrideLastLine(lineLast.get(0));
		}

		final String type = line0.get("TYPE", 0);
		final String stringColor = line0.get("BACKCOLOR", 0);
		HColor backColor = null;
		if (stringColor != null) {
			backColor = diagram.getSkinParam().getIHtmlColorSet().getColor(stringColor);
		}

		if (stereotype == null) {
			return diagram.addIdea(backColor, type.length() - 1, lines.toDisplay(),
					IdeaShape.fromDesc(line0.get("SHAPE", 0)));
		}
		return diagram.addIdea(stereotype, backColor, type.length() - 1, lines.toDisplay(),
				IdeaShape.fromDesc(line0.get("SHAPE", 0)));
	}

}
