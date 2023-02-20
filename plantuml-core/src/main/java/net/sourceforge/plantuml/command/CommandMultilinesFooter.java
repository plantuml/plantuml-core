package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.TitledDiagram;
import net.sourceforge.plantuml.UmlDiagram;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.font.FontParam;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.utils.BlocLines;

public class CommandMultilinesFooter extends CommandMultilines<TitledDiagram> {

	public static final CommandMultilinesFooter ME = new CommandMultilinesFooter();

	private CommandMultilinesFooter() {
		super("^(?:(left|right|center)?[%s]*)footer$");
	}

	@Override
	public String getPatternEnd() {
		return "^end[%s]?footer$";
	}

	public CommandExecutionResult execute(final TitledDiagram diagram, BlocLines lines) throws NoSuchColorException {
		lines = lines.trim();
		final Matcher2 m = getStartingPattern().matcher(lines.getFirst().getTrimmed().getString());
		if (m.find() == false) {
			throw new IllegalStateException();
		}
		final String align = m.group(1);
		lines = lines.subExtract(1, 1);
		final Display strings = lines.toDisplay();
		if (strings.size() > 0) {
			HorizontalAlignment ha = HorizontalAlignment.fromString(align, HorizontalAlignment.CENTER);
			if (align == null)
				ha = FontParam.FOOTER.getStyleDefinition(null)
						.getMergedStyle(((UmlDiagram) diagram).getSkinParam().getCurrentStyleBuilder())
						.getHorizontalAlignment();

			diagram.getFooter().putDisplay(strings, ha);
			return CommandExecutionResult.ok();
		}
		return CommandExecutionResult.error("Empty footer");
	}

}
