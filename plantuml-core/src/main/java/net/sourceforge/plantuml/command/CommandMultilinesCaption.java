package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.TitledDiagram;
import net.sourceforge.plantuml.cucadiagram.DisplayPositioned;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.VerticalAlignment;
import net.sourceforge.plantuml.utils.BlocLines;

public class CommandMultilinesCaption extends CommandMultilines<TitledDiagram> {

	public static final CommandMultilinesCaption ME = new CommandMultilinesCaption();

	private CommandMultilinesCaption() {
		super("^caption$");
	}

	@Override
	public String getPatternEnd() {
		return "^end[%s]?caption$";
	}

	public CommandExecutionResult execute(final TitledDiagram diagram, BlocLines lines) throws NoSuchColorException {
		lines = lines.subExtract(1, 1);
		lines = lines.removeEmptyColumns();
		final Display strings = lines.toDisplay();
		if (strings.size() > 0) {
			diagram.setCaption(DisplayPositioned.single(strings.replaceBackslashT(), HorizontalAlignment.CENTER,
					VerticalAlignment.BOTTOM));
			return CommandExecutionResult.ok();
		}
		return CommandExecutionResult.error("No caption defined");
	}

}
