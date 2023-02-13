package net.sourceforge.plantuml.wbs;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.mindmap.IdeaShape;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.Direction;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandWBSItem extends SingleLineCommand2<WBSDiagram> {

	public CommandWBSItem(int mode) {
		super(false, getRegexConcat(mode));
	}

	static IRegex getRegexConcat(int mode) {
		if (mode == 0)
			return RegexConcat.build(CommandWBSItem.class.getName() + mode, RegexLeaf.start(), //
					new RegexLeaf("TYPE", "([ \t]*[*+-]+)"), //
					new RegexOptional(new RegexLeaf("BACKCOLOR", "\\[(#\\w+)\\]")), //
					new RegexOptional(new RegexLeaf("CODE", "\\(([%pLN_]+)\\)")), //
					new RegexLeaf("SHAPE", "(_)?"), //
					new RegexLeaf("DIRECTION", "([<>])?"), //
					RegexLeaf.spaceOneOrMore(), //
					new RegexLeaf("LABEL", "([^%s].*)"), //
					RegexLeaf.end());

		return RegexConcat.build(CommandWBSItem.class.getName() + mode, RegexLeaf.start(), //
				new RegexLeaf("TYPE", "([ \t]*[*+-]+)"), //
				new RegexOptional(new RegexLeaf("BACKCOLOR", "\\[(#\\w+)\\]")), //
				new RegexLeaf("SHAPE", "(_)?"), //
				new RegexLeaf("DIRECTION", "([<>])?"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("LABEL", "[%g](.*)[%g]"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("as"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("CODE", "([%pLN_]+)"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(WBSDiagram diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {
		final String type = arg.get("TYPE", 0);
		final String label = arg.get("LABEL", 0);
		final String code = arg.get("CODE", 0);
		final String stringColor = arg.get("BACKCOLOR", 0);
		HColor backColor = null;
		if (stringColor != null)
			backColor = diagram.getSkinParam().getIHtmlColorSet().getColor(stringColor);

		Direction dir = type.contains("-") ? Direction.LEFT : Direction.RIGHT;
		final String direction = arg.get("DIRECTION", 0);
		if ("<".equals(direction))
			dir = Direction.LEFT;
		else if (">".equals(direction))
			dir = Direction.RIGHT;

		return diagram.addIdea(code, backColor, diagram.getSmartLevel(type), label, dir,
				IdeaShape.fromDesc(arg.get("SHAPE", 0)));
	}

}
