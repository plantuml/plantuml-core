package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.TitledDiagram;
import net.sourceforge.plantuml.cucadiagram.DisplayPositioned;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.VerticalAlignment;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.BlocLines;

public class CommandMultilinesLegend extends CommandMultilines2<TitledDiagram> {

	public static final CommandMultilinesLegend ME = new CommandMultilinesLegend();

	private CommandMultilinesLegend() {
		super(getRegexConcat(), MultilinesStrategy.REMOVE_STARTING_QUOTE, Trim.BOTH);
	}

	private static IRegex getRegexConcat() {
		return RegexConcat.build(CommandMultilinesLegend.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("legend"), //
				new RegexOptional( //
						new RegexConcat( //
								RegexLeaf.spaceOneOrMore(), //
								new RegexLeaf("VALIGN", "(top|bottom)") //
						)), //
				new RegexOptional( //
						new RegexConcat( //
								RegexLeaf.spaceOneOrMore(), //
								new RegexLeaf("ALIGN", "(left|right|center)") //
						)), RegexLeaf.end());
	}

	@Override
	public String getPatternEnd() {
		return "^end[%s]?legend$";
	}

	@Override
	protected CommandExecutionResult executeNow(TitledDiagram diagram, BlocLines lines) throws NoSuchColorException {
		lines = lines.trimSmart(1);
		final RegexResult line0 = getStartingPattern().matcher(lines.getFirst().getTrimmed().getString());
		final String align = line0.get("ALIGN", 0);
		final String valign = line0.get("VALIGN", 0);
		lines = lines.subExtract(1, 1);
		lines = lines.removeEmptyColumns();
		final Display strings = lines.toDisplay();
		if (strings.size() > 0) {
			final VerticalAlignment valignment = VerticalAlignment.fromString(valign);
			HorizontalAlignment alignment = HorizontalAlignment.fromString(align);
			if (alignment == null) {
				alignment = HorizontalAlignment.CENTER;
			}
			diagram.setLegend(DisplayPositioned.single(strings.replaceBackslashT(), alignment, valignment));
			return CommandExecutionResult.ok();
		}
		return CommandExecutionResult.error("No legend defined");
	}
}
