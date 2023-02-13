package net.sourceforge.plantuml.ebnf;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.CommandMultilines2;
import net.sourceforge.plantuml.command.MultilinesStrategy;
import net.sourceforge.plantuml.command.Trim;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.color.Colors;
import net.sourceforge.plantuml.klimt.color.ColorParser;
import net.sourceforge.plantuml.klimt.color.ColorType;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.BlocLines;

public class CommandNoteMultilines extends CommandMultilines2<PSystemEbnf> {

	public CommandNoteMultilines() {
		super(getRegexConcat(), MultilinesStrategy.REMOVE_STARTING_QUOTE, Trim.BOTH);
	}

	private static ColorParser color() {
		return ColorParser.simpleColor(ColorType.BACK);
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandNoteMultilines.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("note"), //
				RegexLeaf.spaceZeroOrMore(), //
				color().getRegex(), //
				RegexLeaf.end());
	}

	@Override
	public String getPatternEnd() {
		return "^end[%s]?note$";
	}

	@Override
	protected CommandExecutionResult executeNow(PSystemEbnf diagram, BlocLines lines) throws NoSuchColorException {
		final RegexResult line0 = getStartingPattern().matcher(lines.getFirst().getTrimmed().getString());
		lines = lines.subExtract(1, 1);
		lines = lines.removeEmptyColumns();
		final Display note = lines.toDisplay();
		final Colors colors = color().getColor(line0, diagram.getSkinParam().getIHtmlColorSet());
		return diagram.addNote(note, colors);
	}

}
