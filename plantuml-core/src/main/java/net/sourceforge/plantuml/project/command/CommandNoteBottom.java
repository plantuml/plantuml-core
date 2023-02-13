package net.sourceforge.plantuml.project.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.CommandMultilines;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.utils.BlocLines;

public class CommandNoteBottom extends CommandMultilines<GanttDiagram> {

	public CommandNoteBottom() {
		super("^note[%s]*bottom$");
	}

	@Override
	public String getPatternEnd() {
		return "^end[%s]*note$";
	}

	public CommandExecutionResult execute(GanttDiagram diagram, BlocLines lines) throws NoSuchColorException {
		lines = lines.subExtract(1, 1);
		lines = lines.removeEmptyColumns();
		final Display strings = lines.toDisplay();
		if (strings.size() > 0) {
			return diagram.addNote(strings);
		}
		return CommandExecutionResult.error("No note defined");
	}

}
