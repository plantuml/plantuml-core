package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.TitledDiagram;
import net.sourceforge.plantuml.emoji.SvgNanoParser;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandSpriteSvg extends SingleLineCommand2<TitledDiagram> {

	public static final CommandSpriteSvg ME = new CommandSpriteSvg();

	private CommandSpriteSvg() {
		super(getRegexConcat());
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandSpriteSvg.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("sprite"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("\\$?"), //
				new RegexLeaf("NAME", "([-%pLN_]+)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("SVG", "(\\<svg\\b.*\\</svg\\>)"), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(TitledDiagram system, LineLocation location, RegexResult arg) {
		final String svg = arg.get("SVG", 0);
		final SvgNanoParser nanoParser = new SvgNanoParser(svg, true);
		system.addSprite(arg.get("NAME", 0), nanoParser);

		return CommandExecutionResult.ok();
	}
}
