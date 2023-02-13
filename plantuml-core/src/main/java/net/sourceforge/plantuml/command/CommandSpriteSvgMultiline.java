package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.TitledDiagram;
import net.sourceforge.plantuml.emoji.SvgNanoParser;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.utils.BlocLines;

public class CommandSpriteSvgMultiline extends CommandMultilines2<TitledDiagram> {

	public static final CommandSpriteSvgMultiline ME = new CommandSpriteSvgMultiline();

	private CommandSpriteSvgMultiline() {
		super(getRegexConcat(), MultilinesStrategy.KEEP_STARTING_QUOTE, Trim.BOTH);
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandSpriteSvgMultiline.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("sprite"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("\\$?"), //
				new RegexLeaf("NAME", "([-%pLN_]+)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("SVGSTART", "(\\<svg\\b.*)"), //
				RegexLeaf.end());
	}

	@Override
	public String getPatternEnd() {
		return "(.*\\</svg\\>)$";
	}

	@Override
	protected CommandExecutionResult executeNow(TitledDiagram system, BlocLines lines) throws NoSuchColorException {

		final RegexResult line0 = getStartingPattern().matcher(lines.getFirst().getTrimmed().getString());
		final String svgStart = line0.get("SVGSTART", 0);
		lines = lines.subExtract(1, 0);

		final StringBuilder svg = new StringBuilder(svgStart);
		for (StringLocated sl : lines)
			svg.append(sl.getString());

		final SvgNanoParser nanoParser = new SvgNanoParser(svg.toString(), true);
		system.addSprite(line0.get("NAME", 0), nanoParser);

		return CommandExecutionResult.ok();
	}
}
