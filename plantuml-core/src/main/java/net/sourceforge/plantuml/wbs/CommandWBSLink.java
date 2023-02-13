package net.sourceforge.plantuml.wbs;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.cucadiagram.Stereotype;
import net.sourceforge.plantuml.graphic.color.Colors;
import net.sourceforge.plantuml.klimt.color.ColorParser;
import net.sourceforge.plantuml.klimt.color.ColorType;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandWBSLink extends SingleLineCommand2<WBSDiagram> {

	public CommandWBSLink() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandWBSLink.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("CODE1", "([%pLN_]+)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("LINK", "[.-]+\\>"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("CODE2", "([%pLN_]+)"), //
				RegexLeaf.spaceZeroOrMore(), //
				color().getRegex(), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("STEREOTYPE", "(\\<\\<.*\\>\\>)?"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("LABEL_LINK", "(?::[%s]*(.+))?"), //
				RegexLeaf.end());
	}

	private static ColorParser color() {
		return ColorParser.simpleColor(ColorType.LINE);
	}

	@Override
	protected CommandExecutionResult executeArg(WBSDiagram diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {
		final String code1 = arg.get("CODE1", 0);
		final String code2 = arg.get("CODE2", 0);
//		final String message = arg.get("LABEL_LINK", 0);

		final Colors colors = color().getColor(arg, diagram.getSkinParam().getIHtmlColorSet());
//		link.setColors(color);
//		link.applyStyle(arg.getLazzy("ARROW_STYLE", 0));
		Stereotype stereotype = null;
		if (arg.get("STEREOTYPE", 0) != null) {
			stereotype = Stereotype.build(arg.get("STEREOTYPE", 0));
		}

		return diagram.link(code1, code2, colors, stereotype);
	}

}
